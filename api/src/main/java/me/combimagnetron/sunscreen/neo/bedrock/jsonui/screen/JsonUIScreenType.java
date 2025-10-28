package me.combimagnetron.sunscreen.neo.bedrock.jsonui.screen;

import me.combimagnetron.sunscreen.util.LazyReference;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface JsonUIScreenType {

    @NotNull Collection<@NotNull LazyReference<?>> propertyTypes();

}
