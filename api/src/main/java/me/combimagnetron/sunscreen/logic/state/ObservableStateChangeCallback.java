package me.combimagnetron.sunscreen.logic.state;

@FunctionalInterface
public interface ObservableStateChangeCallback<T> {

    void changed(T old, T current);

}
