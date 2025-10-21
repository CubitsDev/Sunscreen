package me.combimagnetron.sunscreen.util;

import java.util.function.Supplier;

public interface Try<R> {

    boolean failed();

    default boolean succes() {
        return !failed();
    }

    <T extends Throwable> T thrown();

    R result();

    static <R> Try<R> of(Supplier<R> supplier) {
        try {
            return new SucceededTry<>(supplier.get());
        } catch (Throwable throwable) {
            return new FailedTry<>(throwable);
        }
    }

    record SucceededTry<R>(R result) implements Try<R> {

        @Override
        public boolean failed() {
            return false;
        }

        @Override
        public <T extends Throwable> T thrown() {
            return null;
        }

        @Override
        public R result() {
            return result;
        }
    }

    record FailedTry<R, T extends Throwable>(T thrown) implements Try<R> {

        @Override
        public boolean failed() {
            return true;
        }

        @Override
        public R result() {
            return null;
        }

    }

}
