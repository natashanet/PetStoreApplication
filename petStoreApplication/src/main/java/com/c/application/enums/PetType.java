package com.c.application.enums;
import com.c.application.deserialize.PetTypeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = PetTypeDeserializer.class)
public enum PetType {
    CAT,
    DOG
}








