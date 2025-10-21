package me.combimagnetron.sunscreen.resourcepack;

import me.combimagnetron.passport.util.data.Identifier;

public interface Asset {

    Identifier identifier();

    ResourcePackPath path();

}
