package me.combimagnetron.sunscreen.neo.editor.widget;

import org.jetbrains.annotations.NotNull;

public @interface WidgetContext {

    @NotNull WidgetPosition position() default WidgetPosition.TOP_RIGHT;

}
