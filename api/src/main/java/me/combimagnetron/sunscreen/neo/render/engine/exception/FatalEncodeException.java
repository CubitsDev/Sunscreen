package me.combimagnetron.sunscreen.neo.render.engine.exception;

public class FatalEncodeException extends RuntimeException {
    private static final String PREFIX = "Fatal encode error; see and report details below\n%s";

    public FatalEncodeException(String message) {
        super(String.format(PREFIX, message));
    }
}
