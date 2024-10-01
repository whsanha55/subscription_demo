package com.jongha.demo.global.converter;

import jakarta.persistence.AttributeConverter;
import java.nio.charset.StandardCharsets;
import org.apache.commons.codec.binary.Base64;

public class PhoneNumberConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return Base64.encodeBase64String(attribute.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return new String(Base64.decodeBase64(dbData), StandardCharsets.UTF_8);
    }

}
