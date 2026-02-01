package me.combimagnetron.sunscreen.neo.loader;

public interface SystemComponent<C extends SystemComponent<C>> extends Component<C, SystemComponentLoaderContext> {
}
