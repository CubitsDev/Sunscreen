package me.combimagnetron.sunscreen.neo.file;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface XmlEncodable {

    @NotNull Element xml(@NotNull Document document);

}
