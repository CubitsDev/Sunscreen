package me.combimagnetron.sunscreen.ui.render.engine.encode;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import me.combimagnetron.sunscreen.ui.graphic.GraphicLike;
import me.combimagnetron.sunscreen.ui.render.engine.binary.BinaryMasks;
import me.combimagnetron.sunscreen.ui.render.engine.binary.BitOutputStream;
import me.combimagnetron.sunscreen.ui.render.engine.exception.FatalEncodeException;
import me.combimagnetron.sunscreen.ui.render.engine.map.SendableMapMenu;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class MapEncoder {
    private final static int MAGIC_ID = 0x1234567;
    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(16384);
    private final Object lock = new Object();
    private final AtomicInteger paletteId = new AtomicInteger(0);
    private final ThreadLocal<BitOutputStream> bytes = ThreadLocal.withInitial(() -> new BitOutputStream(byteArrayOutputStream));
    private final ThreadLocal<LocalPalette[]> localPalettes = ThreadLocal.withInitial(() -> new LocalPalette[47]);
    private final ThreadLocal<ImageTile[]> imageTiles = ThreadLocal.withInitial(() -> new ImageTile[4096]);
    private final ThreadLocal<BitTile[]> bitTiles = ThreadLocal.withInitial(() -> new BitTile[4096]);
    private final BufferedImage image;

    public MapEncoder(@NotNull GraphicLike<?> graphicLike) {
        if (graphicLike.image().getWidth() != 128 || graphicLike.image().getWidth() != 128) {
            throw new FatalEncodeException("Buffered image inside sliced GraphicLike exceeds size limit of 128x128.");
        }
        this.image = graphicLike.image();

        formTiles();
        packPalettesAndTiles();
        write();
    }

    public static SendableMapMenu encode(@NotNull GraphicLike<? extends GraphicLike<?>> graphicLike) {
        new MapEncoder(graphicLike);
        return SendableMapMenu.of();
    }

    private void formTiles() {
        final int tilesX = image.getWidth() / 2;
        final int tilesY = image.getHeight() / 2;
        int index = 0;

        for (int ty = 0; ty < tilesY; ty++) {
            for (int tx = 0; tx < tilesX; tx++) {
                imageTiles.get()[index++] = new ImageTile(IntArrayList.of(
                        image.getRGB(tx * 2,ty * 2),
                        image.getRGB(tx * 2 + 1, ty * 2),
                        image.getRGB(tx * 2, ty * 2 + 1),
                        image.getRGB(tx * 2 + 1, ty * 2 + 1)
                ));
            }
        }
    }

    private static IntArrayList uniqueColorsPalette(ImageTile imageTile, LocalPalette localPalette) {
        IntOpenHashSet openHashSet = new IntOpenHashSet();
        for (int color : imageTile.colors()) {
            if (!localPalette.colors().contains(color)) openHashSet.add(color);
        }
        return new IntArrayList(openHashSet);
    }

    private static IntOpenHashSet uniqueColors(ImageTile imageTile) {
        return new IntOpenHashSet(imageTile.colors());
    }

    private static int allUniqueColorAmount(ImageTile[] imageTiles) {
        IntOpenHashSet intOpenHashSet = new IntOpenHashSet();
        for (ImageTile imageTile : imageTiles) {
            intOpenHashSet.addAll(uniqueColors(imageTile));
        }
        return intOpenHashSet.size();
    }

    private void packPalettesAndTiles() {
        ImageTile[] tiles = imageTiles.get();
        if (allUniqueColorAmount(tiles) > 752) {
            //TODO add more algorithms
            throw new FatalEncodeException("We did not implement anything beyond this, make simpler art.");
        }
        LocalPalette[] palettes = localPalettes.get();
        for (int i = 0; i < 47; i++) {
            palettes[i] = new LocalPalette();
        }
        for (int i = 0; i < tiles.length; i++) {
            ImageTile imageTile = tiles[i];
            IntArrayList indices = null;
            LocalPalette match = null;

            for (LocalPalette localPalette : palettes) {
                //Check for palette that has all the colors;
                indices = containsAll(localPalette.colors(), imageTile.colors());
                if (indices != null) {
                    match = localPalette;
                    break;
                }
                indices = IntArrayList.of();

                //Look if there's enough space to fill with the tile's colors.
                IntArrayList uniqueColors = uniqueColorsPalette(imageTile, localPalette);
                if (localPalette.colors().size() + uniqueColors.size() <= 16) {
                    localPalette.colors().addAll(uniqueColors);
                    for (int color : imageTile.colors()) {
                        indices.add(localPalette.colors().indexOf(color));
                    }
                    match = localPalette;
                    break;
                }
            }
            if (indices.isEmpty()) throw new FatalEncodeException("We did not implement anything beyond this, make simpler art.");

            bitTiles.get()[i] = new BitTile(indices, match);
        }
        for (LocalPalette localPalette : localPalettes.get()) {
            if (localPalette.colors().size() != 16) {
                int missing = 16 - localPalette.colors.size();
                for (int i1 = 0; i1 < missing; i1++) {
                    localPalette.colors().add(6);
                }
            }
        }
    }

    private IntArrayList containsAll(IntArrayList colors, IntArrayList subset) {
        IntArrayList indices = new IntArrayList(subset.size());
        int i = 0, j = 0;
        while (i < colors.size() && j < subset.size()) {
            int a = colors.getInt(i);
            int b = subset.getInt(j);

            if (a == b) {
                indices.add(i);
                j++;
            }
            i++;
        }
        return (j == subset.size()) ? indices : null;
    }

    public ByteArrayOutputStream bytes() {
        return byteArrayOutputStream;
    }

    public void write(File file) throws IOException {
        int index = 0;
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (byte b : byteArrayOutputStream.toByteArray()) {
                fileWriter.append(String.valueOf(index++)).append(" ").append(String.valueOf(b)).append("\n");
            }
        }
    }

    private void write() {
        synchronized (lock) {
            BitOutputStream bitOutputStream = bytes.get();
            bitOutputStream.writeBits(28, MAGIC_ID);
            System.out.println(new Color(localPalettes.get()[0].colors.getInt(0)));
            bitOutputStream.writeBits(32, 69420);
            for (LocalPalette localPalette : localPalettes.get()) {
                //bitOutputStream.writeBits(6, localPalette.serializedId());
                for (int color : localPalette.colors) {
                    bitOutputStream.writeBits(32, color);
                }
            }
            for (BitTile bitTile : bitTiles.get()) {
                bitOutputStream.writeBits(6, bitTile.palette().serializedId());
                for (int index : bitTile.indices) {
                    bitOutputStream.writeBits(4, index);
                }
            }
            bitOutputStream.flush();
            bitOutputStream.close();
            int size = byteArrayOutputStream.size();
            System.out.println(size);
            if (!(size < 16384)) return;
            try {
                byteArrayOutputStream.write(new byte[16384 - size]);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(size);
        }
    }

    record ImageTile(IntArrayList colors) {}

    record BitTile(IntArrayList indices, LocalPalette palette) {}

    public final class LocalPalette {
        //private final int[] colors = new int[16];
        private final IntArrayList colors = IntArrayList.of();
        private final int id;

        public LocalPalette(int id) {
            this.id = id;
        }

        public LocalPalette() {
            this(paletteId.getAndIncrement());
        }

        public byte serializedId() {
            return (byte) (id & BinaryMasks.FIVE_BIT_MASK);
        }

        public IntArrayList colors() {
            return colors;
        }

        public int id() {
            return id;
        }

    }

}
