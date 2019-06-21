package ru.netrax.jsonObjectWriter;

import javax.json.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;

public class Mson {
    public String toJson(Object obj) throws IllegalAccessException {
        System.out.println();
        if (obj != null) {
            if (obj.getClass().equals(Integer.class)) {
                return ((Integer) obj).toString();
            } else if (obj.getClass().equals(Boolean.class)) {
                return ((Boolean) obj).toString();
            } else if (obj.getClass().equals(Double.class)) {
                return ((Double) obj).toString();
            } else if (obj.getClass().equals(Float.class)) {
                return ((Float) obj).toString();
            } else if (obj.getClass().equals(String.class)) {
                return obj.toString();
            } else if (obj.getClass().equals(Character.class)) {
                return String.valueOf(obj);
            } else if (obj.getClass().equals(Long.class)) {
                return ((Long) obj).toString();
            } else if (obj.getClass().equals(Byte.class)) {
                return ((Byte) obj).toString();
            } else if (obj.getClass().equals(Short.class)) {
                return ((Short) obj).toString();
            } else if (obj.getClass().isArray()) {
                JsonArrayBuilder arr = Json.createArrayBuilder();
                int length = Array.getLength(obj);
                for (int i = 0; i < length; i++)
                    arr.add(toJsonValue(Array.get(obj, i)));
                JsonArray jsonArray = arr.build();
                return jsonArray.toString();
            } else if (obj instanceof Collection) {
                JsonArrayBuilder arr = Json.createArrayBuilder((Collection) obj);
                JsonArray jsonArray = arr.build();
                return jsonArray.toString();
            }
            JsonObjectBuilder builder = getAround(obj);
            JsonObject buildObject = builder.build();
            return buildObject.toString();
        } else return null;
    }

    private static JsonValue toJsonValue(Object value) throws IllegalAccessException {
        if (value instanceof Integer) {
            JsonNumber jsonNumber = Json.createValue((Integer) value);
            return (JsonValue) jsonNumber;
        } else if (value instanceof String) {
            JsonString jsonString = Json.createValue((String) value);
            return (JsonValue) jsonString;
        } else if (value instanceof Long) {
            JsonNumber jsonNumber = Json.createValue((Long) value);
            return (JsonValue) jsonNumber;
        } else if (value instanceof Float) {
            JsonNumber jsonNumber = Json.createValue((Float) value);
            return (JsonValue) jsonNumber;
        } else if (value instanceof Double) {
            JsonNumber jsonNumber = Json.createValue((Double) value);
            return (JsonValue) jsonNumber;
        } else if (value instanceof Boolean) {
            return (Boolean) value ? JsonValue.TRUE : JsonValue.FALSE;
        } else if (value instanceof Character) {
            JsonString jsonString = Json.createValue(String.valueOf(value));
            return (JsonValue) jsonString;
        } else if (value instanceof Byte) {
            JsonNumber jsonNumber = Json.createValue((Byte) value);
            return (JsonValue) jsonNumber;
        } else if (value instanceof Short) {
            JsonNumber jsonNumber = Json.createValue((Short) value);
            return (JsonValue) jsonNumber;
        } else if (value instanceof JsonArrayBuilder) {
            JsonArray jsonArray = ((JsonArrayBuilder) value).build();
            return (JsonValue) jsonArray;
        } else if (value instanceof JsonObjectBuilder) {
            JsonObject jsonObject = ((JsonObjectBuilder) value).build();
            return (JsonValue) jsonObject;
        } else if (value instanceof JsonValue) {
            return (JsonValue) value;
        }
        JsonObjectBuilder builderInnerObject = getAround(value);
        JsonObject buildObject = builderInnerObject.build();
        return (JsonValue) buildObject;
    }

    private static JsonObjectBuilder getAround(Object obj) throws IllegalAccessException {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        for (Field field : obj.getClass().getDeclaredFields()) {
            Class fieldType = field.getType();
            field.setAccessible(true);
            if (fieldType.isPrimitive()) {
                builder.add(field.getName(), toJsonValue(field.get(obj)));
                continue;
            }
            if (fieldType.isArray()) {
                JsonArrayBuilder arr = Json.createArrayBuilder();
                Object array = field.get(obj);
                int length = Array.getLength(array);
                for (int i = 0; i < length; i++)
                    arr.add(toJsonValue(Array.get(array, i)));
                builder.add(field.getName(), toJsonValue(arr));
                continue;
            }
            if (fieldType.getSimpleName().equals("String")) {
                builder.add(field.getName(), toJsonValue(field.get(obj)));
                continue;
            }
            if (field.get(obj) instanceof Collection) {
                JsonArrayBuilder arr = Json.createArrayBuilder((Collection) field.get(obj));
                builder.add(field.getName(), toJsonValue(arr));
                continue;
            }
            JsonObjectBuilder builderInner = getAround(field.get(obj));
            builder.add(field.getName(), toJsonValue(builderInner));
        }
        return builder;
    }
}





























