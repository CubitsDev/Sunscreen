package me.combimagnetron.sunscreen.neo.bedrock.jsonui.property;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.JsonObject;
import me.combimagnetron.sunscreen.util.LazyReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.WeakHashMap;

@JsonSerialize(using = JsonUIProperty.JsonUIPropertySerializer.class)
public class JsonUIProperty {
    final Map<PropertyId, JsonUIPropertyType<?>> propertyTypeMap = new WeakHashMap<>();

    protected JsonUIProperty(JsonUIPropertyType<?>... jsonUIPropertyTypes) {
        for (JsonUIPropertyType<?> jsonUIPropertyType : jsonUIPropertyTypes) {
            propertyTypeMap.put(new PropertyId(jsonUIPropertyType.type(), jsonUIPropertyType.name()), jsonUIPropertyType);
        }
    }

    public @NotNull Collection<JsonUIPropertyType<?>> properties() {
        return propertyTypeMap.values();
    }

    public @Nullable <T> JsonUIPropertyType<T> find(LazyReference<T> type) {
        return (JsonUIPropertyType<T>) propertyTypeMap.get(type);
    }

    public static @NotNull JsonUIProperty of(JsonUIPropertyType<?>... types) {
        return new JsonUIProperty(types);
    }

    public @NotNull JsonNode json() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.valueToTree(this);
    }

    record PropertyId(@NotNull Class<?> type, @NotNull String name) {

    }

    static class JsonUIPropertySerializer extends JsonSerializer<JsonUIProperty> {

        @Override
        public void serialize(JsonUIProperty jsonUIProperty, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            for (Map.Entry<PropertyId, JsonUIPropertyType<?>> entry : jsonUIProperty.propertyTypeMap.entrySet()) {
                jsonGenerator.writeObjectField(entry.getKey().name, entry.getValue().value());
            }
        }

    }

}
