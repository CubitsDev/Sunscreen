package me.combimagnetron.sunscreen.neo.protocol.type;

public record EntityReference<T>(int id, T platformedType) {
}
