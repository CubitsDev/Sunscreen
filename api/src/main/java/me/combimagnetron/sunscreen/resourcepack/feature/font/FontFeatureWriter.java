package me.combimagnetron.sunscreen.resourcepack.feature.font;

import me.combimagnetron.sunscreen.resourcepack.FeatureWriter;
import me.combimagnetron.sunscreen.resourcepack.PackSection;
import me.combimagnetron.sunscreen.resourcepack.ResourcePack;
import me.combimagnetron.sunscreen.resourcepack.meta.PackVersion;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FontFeatureWriter implements FeatureWriter<FontFeature> {

    @Override
    public PackSection write(FontFeature feature, ResourcePack pack, @Nullable PackVersion version) {

        return PackSection.of(List.of(), null);
    }

}
