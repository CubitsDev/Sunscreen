package me.combimagnetron.sunscreen.neo.render.engine.binary;

import java.io.*;

/**
 * Write bits-at-a-time where the number of bits is between 1 and 32.
 * Client programs must call <code>flush</code> or
 * <code>close</code> when finished writing or not all bits will be written.
 * This class is intended to be used with <code>BitInputStream</code> to
 * facilitate reading and writing data in a bits-at-a-time manner.
 * <P>
 * Updated for version 2.0 to extend java.io.OutputStream
 * <P>
 * Any exceptions generated are rethrown as <code>RuntimeException</code> objects
 * so client code does not have to catch or rethrow them.
 * <P>
 * Modified to write in 7-bit units instead of 8-bit bytes.
 * Each output "byte" will only contain 7 meaningful bits (0–127).
 * <P>
 * @author Owen Astrachan
 * @version 1.0, July 2000
 * @version 2.0, October 2004
 * @version 2.1, March 2010, Fixed >> to >>> bug in writeBits
 * @version 3.0, Aug 2025, Modified to 7-bit units
 */
public class BitOutputStream extends OutputStream {

    private OutputStream  myOutput;
    private int           myBuffer;
    private int           myBitsToGo;

    private static final int[] bmask = {
            0x00, 0x01, 0x03, 0x07, 0x0f, 0x1f, 0x3f, 0x7f, 0xff,
            0x1ff,0x3ff,0x7ff,0xfff,0x1fff,0x3fff,0x7fff,0xffff,
            0x1ffff,0x3ffff,0x7ffff,0xfffff,0x1fffff,0x3fffff,
            0x7fffff,0xffffff,0x1ffffff,0x3ffffff,0x7ffffff,
            0xfffffff,0x1fffffff,0x3fffffff,0x7fffffff,0xffffffff
    };

    // instead of 8 bits per byte, we now use 7 bits per output unit
    private static final int BITS_PER_UNIT = 7;

    /**
     * Required by OutputStream subclasses, write the low
     * 8-bits to the underlying outputstream
     */
    public void write(int b) throws IOException {
        myOutput.write(b);
    }

    /**
     * Create a stream that writes-through to the <code>OutputStream</code> object
     * passed as a parameter.
     * @param out is the output stream to which bits are written
     */
    public BitOutputStream(OutputStream out){
        myOutput = out;
        initialize();
    }

    private void initialize(){
        myBuffer = 0;
        myBitsToGo = BITS_PER_UNIT;
    }

    /**
     * Construct a bit-at-a-time output stream with specified file
     * name.
     * @param filename is the name of the file being written
     * @throws RuntimeException if opening file fails for either FileNotFound
     * or for Security exceptions
     */
    public BitOutputStream(String filename) {
        try {
            myOutput = new BufferedOutputStream(new FileOutputStream(filename));
        } catch (FileNotFoundException fnf) {
            throw new RuntimeException("could not create " + filename + " " + fnf);
        } catch(SecurityException se) {
            throw new RuntimeException("security exception on write " + se);
        }
        initialize();
    }

    /**
     * Flushes bits not yet written, must be called by client
     * programs if <code>close</code> isn't called.
     * @throws RuntimeException if there's a problem writing bits
     */
    public void flush() {
        if (myBitsToGo != BITS_PER_UNIT) {
            try {
                // Shift by 4 to avoid values 0-3 that don't exist in the client
                write((myBuffer << myBitsToGo) + 4); // pad remaining bits on the right and shift by 4
            } catch (IOException ioe) {
                throw new RuntimeException("error writing bits on flush " + ioe);
            }
            myBuffer = 0;
            myBitsToGo = BITS_PER_UNIT;
        }

        try {
            myOutput.flush();
        } catch (IOException ioe) {
            throw new RuntimeException("error on flush " + ioe);
        }
    }

    /**
     * Releases system resources associated with file and
     * flushes bits not yet written. Either this function
     * or flush must be called or not all bits will be written
     * @throws RuntimeException if close fails
     */
    public void close() {
        flush();
        try {
            myOutput.close();
        } catch (IOException ioe) {
            throw new RuntimeException("error closing BitOutputStream " + ioe);
        }
    }

    /**
     * Write specified number of bits from value to a file.
     * @param howManyBits is number of bits to write (1-32)
     * @param value is source of bits, rightmost bits are written
     * @throws RuntimeException if there's an I/O problem writing bits
     */
    public void writeBits(int howManyBits, int value) {
        value &= bmask[howManyBits];  // only rightmost bits valid

        while (howManyBits >= myBitsToGo) {
            myBuffer = (myBuffer << myBitsToGo) |
                    (value >>> (howManyBits - myBitsToGo));
            try {
                write(myBuffer + 4);   // now each written unit only contains 7 bits (0–127)
            } catch (IOException ioe) {
                throw new RuntimeException("error writing bits " + ioe);
            }

            value &= bmask[howManyBits - myBitsToGo];
            howManyBits -= myBitsToGo;
            myBitsToGo = BITS_PER_UNIT;
            myBuffer = 0;
        }

        if (howManyBits > 0) {
            myBuffer = (myBuffer << howManyBits) | value;
            myBitsToGo -= howManyBits;
        }
    }
}