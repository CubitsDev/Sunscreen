package me.combimagnetron.sunscreen.hook;

import me.combimagnetron.sunscreen.resourcepack.ResourcePack;

public interface ResourcePackProviderHook extends SunscreenHook {

    Object font(char ch);

    boolean merge(ResourcePack resourcePack);

}
