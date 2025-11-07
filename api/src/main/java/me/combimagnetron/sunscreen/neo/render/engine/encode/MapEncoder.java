package me.combimagnetron.sunscreen.neo.render.engine.encode;

import it.unimi.dsi.fastutil.ints.*;
import me.combimagnetron.sunscreen.neo.graphic.GraphicLike;
import me.combimagnetron.sunscreen.neo.render.engine.binary.BinaryMasks;
import me.combimagnetron.sunscreen.neo.render.engine.binary.MapOutputStream;
import me.combimagnetron.sunscreen.neo.render.engine.exception.FatalEncodeException;
import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class MapEncoder {
    private static final int MAGIC_ID = 0x53554E53;
    private static final Int2IntOpenHashMap TEMP_MAP = new Int2IntOpenHashMap();
    private static final IntOpenHashSet TEMP_SET = new IntOpenHashSet();
    private static final IntArrayList TEMP_LIST = new IntArrayList();

    private final ByteArrayOutputStream buffer = new ByteArrayOutputStream(16384);
    private final Object lock = new Object();
    private final AtomicInteger paletteId = new AtomicInteger(0);
    private final ThreadLocal<LocalPalette[]> localPalettes = ThreadLocal.withInitial(() -> new LocalPalette[47]);
    private final ThreadLocal<ImageTile[]> imageTiles = ThreadLocal.withInitial(() -> new ImageTile[4096]);
    private final ThreadLocal<BitTile[]> bitTiles = ThreadLocal.withInitial(() -> new BitTile[4096]);
    private final BufferedImage image;

    public MapEncoder(@NotNull GraphicLike<?> graphicLike) {
        if (graphicLike.image().getWidth() != 128 || graphicLike.image().getHeight() != 128) {
            throw new FatalEncodeException("Buffered image inside sliced GraphicLike is not exactly 128x128.");
        }
        this.image = graphicLike.image();
        formTiles();
        packPalettesAndTiles();
        write();
    }

    private void formTiles() {
        final int tilesX = image.getWidth() / 2;
        final int tilesY = image.getHeight() / 2;
        int index = 0;
        ImageTile[] tiles = imageTiles.get();

        for (int ty = 0; ty < tilesY; ty++) {
            for (int tx = 0; tx < tilesX; tx++) {
                int c1 = image.getRGB(tx * 2, ty * 2);
                int c2 = image.getRGB(tx * 2 + 1, ty * 2);
                int c3 = image.getRGB(tx * 2, ty * 2 + 1);
                int c4 = image.getRGB(tx * 2 + 1, ty * 2 + 1);
                tiles[index++] = new ImageTile(new IntArrayList(new int[]{c1, c2, c3, c4}));
            }
        }
    }

    private static IntArrayList containsAll(IntArrayList colors, IntArrayList subset) {
        TEMP_MAP.clear();
        int[] arr = colors.elements();
        int size = colors.size();
        for (int i = 0; i < size; i++) TEMP_MAP.put(arr[i], i);
        TEMP_LIST.clear();
        int[] sub = subset.elements();
        int subSize = subset.size();
        for (int i = 0; i < subSize; i++) {
            int c = sub[i];
            int idx = TEMP_MAP.getOrDefault(c, -1);
            if (idx == -1) return null;
            TEMP_LIST.add(idx);
        }
        return new IntArrayList(TEMP_LIST);
    }

    private static IntArrayList uniqueColorsPalette(ImageTile tile, LocalPalette palette) {
        TEMP_SET.clear();
        int[] tileColors = tile.colors().elements();
        int tSize = tile.colors().size();
        int[] palColors = palette.colors().elements();
        int pSize = palette.colors().size();

        for (int i = 0; i < tSize; i++) {
            int c = tileColors[i];
            boolean found = false;
            for (int j = 0; j < pSize; j++) {
                if (palColors[j] == c) {
                    found = true;
                    break;
                }
            }
            if (!found) TEMP_SET.add(c);
        }
        return new IntArrayList(TEMP_SET);
    }

    private static int allUniqueColorAmount(ImageTile[] imageTiles) {
        TEMP_SET.clear();
        for (ImageTile imageTile : imageTiles) {
            int[] arr = imageTile.colors().elements();
            int len = imageTile.colors().size();
            for (int i = 0; i < len; i++) TEMP_SET.add(arr[i]);
        }
        return TEMP_SET.size();
    }

    private void packPalettesAndTiles() {
        ImageTile[] tiles = imageTiles.get();
        if (allUniqueColorAmount(tiles) > 752)
            throw new FatalEncodeException("We did not implement anything beyond this, make simpler art.");

        LocalPalette[] palettes = localPalettes.get();
        for (int i = 0; i < 47; i++) palettes[i] = new LocalPalette();

        IntArrayList indices;
        for (int i = 0; i < tiles.length; i++) {
            ImageTile tile = tiles[i];
            LocalPalette match = null;
            indices = null;

            for (int p = 0; p < 47; p++) {
                LocalPalette lp = palettes[p];
                indices = containsAll(lp.colors(), tile.colors());
                if (indices != null) {
                    match = lp;
                    break;
                }

                IntArrayList unique = uniqueColorsPalette(tile, lp);
                int newSize = lp.colors().size() + unique.size();

                if (newSize <= 16) {
                    lp.colors().addAll(unique);
                    TEMP_LIST.clear();

                    int[] colorsArr = lp.colors().elements();
                    int cSize = lp.colors().size();
                    int[] tColors = tile.colors().elements();
                    int tSize = tile.colors().size();

                    for (int k = 0; k < tSize; k++) {
                        int c = tColors[k];
                        for (int j = 0; j < cSize; j++) {
                            if (colorsArr[j] == c) {
                                TEMP_LIST.add(j);
                                break;
                            }
                        }
                    }
                    indices = new IntArrayList(TEMP_LIST);
                    match = lp;
                    break;
                }
            }

            if (indices == null || indices.isEmpty())
                throw new FatalEncodeException("We did not implement anything beyond this, make simpler art.");

            bitTiles.get()[i] = new BitTile(indices, match);
        }

        LocalPalette[] arr = localPalettes.get();
        for (LocalPalette lp : arr) {
            int missing = 16 - lp.colors().size();
            for (int m = 0; m < missing; m++) lp.colors().add(6);
        }
    }

    public ByteArrayOutputStream bytes() {
        return buffer;
    }

    private void write() {
        synchronized (lock) {
            try (MapOutputStream out = MapOutputStream.create(buffer)) {
                out.writeBits(32, MAGIC_ID);
                for (LocalPalette lp : localPalettes.get()) {
                    int[] arr = lp.colors().elements();
                    int size = lp.colors().size();
                    for (int i = 0; i < size; i++) {
                        int color = arr[i];
                        out.writeBits(8, color & 0xFF);
                        out.writeBits(8, (color >>> 8) & 0xFF);
                        out.writeBits(8, (color >>> 16) & 0xFF);
                        out.writeBits(8, 255);
                    }
                }
                for (BitTile bitTile : bitTiles.get()) {
                    out.writeBits(6, bitTile.palette().serializedId() & BinaryMasks.SIX_BIT_MASK);
                    int[] idxArr = bitTile.indices().elements();
                    int size = bitTile.indices().size();
                    for (int i = 0; i < size; i++)
                        out.writeBits(4, idxArr[i] & BinaryMasks.FOUR_BIT_MASK);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    record ImageTile(IntArrayList colors) {}
    record BitTile(IntArrayList indices, LocalPalette palette) {}

    public final class LocalPalette {
        private final IntArrayList colors = new IntArrayList();
        private final int id;

        public LocalPalette(int id) {
            this.id = id;
        }

        public LocalPalette() {
            this(paletteId.getAndIncrement());
        }

        public byte serializedId() {
            return (byte) (id & BinaryMasks.SIX_BIT_MASK);
        }

        public IntArrayList colors() {
            return colors;
        }

    }

}
