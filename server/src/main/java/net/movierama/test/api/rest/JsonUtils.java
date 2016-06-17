package net.movierama.test.api.rest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.movierama.test.exception.JsonValidationException;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public final class JsonUtils {

    private JsonUtils() {
    }

    private final static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> List<T> deserializeAsList(final String json, final Class<T> clazz) {
        List<T> list = Collections.emptyList();
        try {
            if (json != null) {
                list = objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
            }
        } catch (IOException e) {
            throw new JsonValidationException("[json-serialization-failed]: json: " + json, e);
        }
        return list;
    }

    public static String getFieldFromJson(final String json, final String field) {
        try {
            final JsonNode jsonNode = objectMapper.readTree(json);
            String value = null;
            if (!jsonNode.get(field).isNull()) {
                value = jsonNode.get(field).toString();
            }
            return value;
        } catch (IOException e) {
            throw new JsonValidationException("[json-not-valid]: json: " + json, e);
        }
    }

}