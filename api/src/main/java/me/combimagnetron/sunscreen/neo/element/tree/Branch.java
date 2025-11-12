package me.combimagnetron.sunscreen.neo.element.tree;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public record Branch(@NotNull Collection<Branch> branches, @NotNull ProcessedElementMetadata metadata) {
}
