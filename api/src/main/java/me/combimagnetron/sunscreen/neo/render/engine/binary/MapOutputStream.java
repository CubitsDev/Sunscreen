package me.combimagnetron.sunscreen.neo.render.engine.binary;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public final class MapOutputStream implements AutoCloseable {
    private static final int PAYLOAD_BITS = 7;
    private static final int OFFSET = 4;
    private static final int TARGET_SIZE = 16384;

    private final ByteArrayOutputStream target;
    private int bitBuffer = 0;
    private int bitCount = 0;
    private boolean closed = false;

    private MapOutputStream(ByteArrayOutputStream target) {
        this.target = target;
    }

    public static MapOutputStream create(ByteArrayOutputStream out) {
        return new MapOutputStream(out);
    }

    public int size() {
        return target.size();
    }

    public void writeBits(int numBits, int value) throws IOException {
        if (closed) throw new IOException("Stream already closed");
        if (numBits < 0 || numBits > 32) throw new IllegalArgumentException("numBits must be 0–32");
        if (numBits == 0) return;

        long mask = (1L << numBits) - 1;
        long longValue = value & mask;

        for (int i = numBits - 1; i >= 0; i--) {
            int bit = (int) ((longValue >> i) & 1);
            bitBuffer = (bitBuffer << 1) | bit;
            bitCount++;
            if (bitCount == PAYLOAD_BITS) {
                target.write(bitBuffer + OFFSET);
                bitBuffer = 0;
                bitCount = 0;
            }
        }
    }

    @Override
    public void close() throws IOException {
        if (closed) return;
        closed = true;

        if (bitCount > 0) {
            int lastByte = bitBuffer << (PAYLOAD_BITS - bitCount);
            target.write(lastByte + OFFSET);
        }

        byte[] encodedBytes = target.toByteArray();
        if (encodedBytes.length > TARGET_SIZE) {
            throw new IOException("Encoded data size (" + encodedBytes.length + ") exceeds target size (" + TARGET_SIZE + ")");
        }

        byte[] paddedEncoded = new byte[TARGET_SIZE];
        System.arraycopy(encodedBytes, 0, paddedEncoded, 0, encodedBytes.length);

        if (encodedBytes.length < TARGET_SIZE) {
            Arrays.fill(paddedEncoded, encodedBytes.length, TARGET_SIZE, (byte) OFFSET);
        }

        target.reset();
        target.write(paddedEncoded);
    }

}