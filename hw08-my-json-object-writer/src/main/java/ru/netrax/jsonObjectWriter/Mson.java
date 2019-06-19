package ru.netrax.jsonObjectWriter;

import javax.json.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;

public class Mson {
    public String toJson(Object obj) throws IllegalAccessException {
        JsonObjectBuilder builder = getAround(obj);
        JsonObject build1 = builder.build();
//        System.out.println("build1 " + build1);
        return build1.toString();
    }

    private static void addValue(JsonObjectBuilder obj, String key, Object value) {
        if (value instanceof Integer) {
            obj.add(key, (Integer) value);
        } else if (value instanceof String) {
            obj.add(key, (String) value);
        } else if (value instanceof Float) {
            obj.add(key, (Float) value);
        } else if (value instanceof Double) {
            obj.add(key, (Double) value);
        } else if (value instanceof Boolean) {
            obj.add(key, (Boolean) value);
        } else if (value instanceof Character) {
            obj.add(key, (String.valueOf(value)));
        } else if (value instanceof JsonArrayBuilder) {
            obj.add(key, (JsonArrayBuilder) value);
        } else if (value instanceof JsonObjectBuilder) {
            obj.add(key, (JsonObjectBuilder) value);
        } else if (value instanceof JsonValue) {
            JsonValue val = (JsonValue) value;
            obj.add(key, val);
        } /*else if (value instanceof Map){
            for (Object s1 : ((Map) value).entrySet()) {
                System.out.println("map.keys = " + s1);
                System.out.println("map.values = " + ((Map) value).values());
                JsonObjectBuilder builderInner = Json.createObjectBuilder();
                builderInner.add(s1, (Map) value).values());
                obj.add(key, s1.toString());
            }
        }*/
    }

    private static void addValueArray(JsonArrayBuilder obj, Object value) throws IllegalAccessException {
        if (value instanceof Integer) {
            obj.add((Integer) value);
        } else if (value instanceof String) {
            obj.add((String) value);
        } else if (value instanceof Float) {
            obj.add((Float) value);
        } else if (value instanceof Double) {
            obj.add((Double) value);
        } else if (value instanceof Boolean) {
            obj.add((Boolean) value);
        } else if (value instanceof Character) {
            obj.add((String.valueOf(value)));
        } else if (value instanceof JsonArrayBuilder) {
            obj.add((JsonArrayBuilder) value);
        } else if (value instanceof JsonValue) {
            JsonValue val = (JsonValue) value;
            obj.add(val);
        } else {
            JsonObjectBuilder builderInner = getAround(value);
            obj.add(builderInner);
        }
    }

    private static JsonObjectBuilder getAround(Object obj) throws IllegalAccessException {
        JsonObjectBuilder builder = Json.createObjectBuilder();

        if (obj.getClass().equals(Boolean.class)) {
            addValue(builder, obj.getClass().getSimpleName(), obj);
        } else if (obj.getClass().equals(Double.class)) {
            addValue(builder, obj.getClass().getSimpleName(), obj);
        } else if (obj.getClass().equals(Float.class)) {
            addValue(builder, obj.getClass().getSimpleName(), obj);
        } else if (obj.getClass().equals(Character.class)) {
            addValue(builder, obj.getClass().getSimpleName(), obj);
        } else if (obj.getClass().equals(Integer.class)) {
            addValue(builder, obj.getClass().getSimpleName(), obj);
        } else if (obj.getClass().equals(Long.class)) {
            addValue(builder, obj.getClass().getSimpleName(), obj);
        } else if (obj.getClass().equals(Byte.class)) {
            addValue(builder, obj.getClass().getSimpleName(), obj);
        } else if (obj.getClass().equals(Short.class)) {
            addValue(builder, obj.getClass().getSimpleName(), obj);
        } else
            for (Field field : obj.getClass().getDeclaredFields()) {
                System.out.println("field name: " + field.getName());
                Class fieldType = field.getType();
                field.setAccessible(true);


                if (fieldType.isPrimitive()) {
                    System.out.println("PRIMITIVE");
                    addValue(builder, field.getName(), field.get(obj));
                    System.out.println(field.get(obj).toString());
                    continue;
                }
                if (fieldType.isArray()) {
                    System.out.println("ARRAY");
                    JsonArrayBuilder arr = Json.createArrayBuilder();
                    Object array = field.get(obj);
                    int length = Array.getLength(array);
                    for (int i = 0; i < length; i++)
                        addValueArray(arr, Array.get(array, i));

                    addValue(builder, field.getName(), arr);
                    System.out.println(field.get(obj).toString());
                    System.out.println();
                    continue;
                }

                if (fieldType.getSimpleName().equals("String")) {
                    System.out.println("STRING");
                    addValue(builder, field.getName(), field.get(obj));
                    System.out.println(field.get(obj).toString());
                    System.out.println();
                    continue;
                }
                if (field.get(obj) instanceof Collection) {
                    System.out.println(fieldType.getSimpleName());
                    System.out.println("COLLECTION");

                    JsonArrayBuilder arr = Json.createArrayBuilder((Collection)field.get(obj));

                    addValue(builder, field.getName(), arr);
                    System.out.println(field.get(obj).toString());
                    System.out.println();
                    continue;
                }
//                if (field.get(obj) instanceof Map) {
//                    System.out.println(fieldType.getSimpleName());
//                    System.out.println("SPECIAL");
//                    addValue(builder, field.getName(), field.get(obj));
//                    System.out.println(field.get(obj).toString());
//                    continue;
//                }

                System.out.println("OBJECT");
                JsonObjectBuilder builderInner = getAround(field.get(obj));
                addValue(builder, field.getName(), builderInner);
                System.out.println();
            }
        return builder;
    }
}





























