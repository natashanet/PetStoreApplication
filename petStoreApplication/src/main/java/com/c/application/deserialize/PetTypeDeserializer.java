package com.c.application.deserialize;

import com.c.application.enums.PetType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class PetTypeDeserializer extends StdDeserializer<PetType> {

    public PetTypeDeserializer() {
        this(null);
    }

    protected PetTypeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public PetType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String type = node.asText();
        if ("Cat".equalsIgnoreCase(type)) {
            return PetType.CAT;
        } else if ("Dog".equalsIgnoreCase(type)) {
            return PetType.DOG;
        }
        throw new IllegalArgumentException("The type (cat/dog) is not in valid format " + type);
    }
}















