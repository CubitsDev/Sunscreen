package me.combimagnetron.sunscreen.resourcepack.feature.font;

import me.combimagnetron.sunscreen.resourcepack.FeatureWriter;
import me.combimagnetron.sunscreen.resourcepack.PackSection;
import me.combimagnetron.sunscreen.resourcepack.ResourcePackFeature;
import me.combimagnetron.passport.util.data.Identifier;

import java.util.Collection;
import java.util.List;

public class FontFeature implements ResourcePackFeature<FontFeature, Font> {

    @Override
    public Class<Font> assetClass() {
        return null;
    }

    @Override
    public ResourcePackFeature<FontFeature, Font> asset(Font asset) {
        return null;
    }

    @Override
    public Collection<Font> assets() {
        return List.of();
    }

    @Override
    public FeatureWriter<FontFeature> writer() {
        return null;
    }

    @Override
    public PackSection write() {
        return null;
    }

}
