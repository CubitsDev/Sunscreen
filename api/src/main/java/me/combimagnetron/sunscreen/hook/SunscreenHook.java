package me.combimagnetron.sunscreen.hook;

import me.combimagnetron.sunscreen.neo.ModernMenu;
import me.combimagnetron.sunscreen.user.SunscreenUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface SunscreenHook {
    Collection<SunscreenHook> HOOKS = new ArrayList<>(List.of(
            //new LabyModSunscreenHook(),
            //new LunarClientSunscreenHook()
    ));

    boolean canRun();

    void enable();

    void disable();

    void onMenuEnter(SunscreenUser<?> user, ModernMenu menu);

    void onMenuLeave(SunscreenUser<?> user, ModernMenu menu);

}
