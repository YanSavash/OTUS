package ru.netrax.jsonObjectWriter;

import javax.json.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class Mson {
    public String toJson(Object obj) throws IllegalAccessException {

        JsonObjectBuilder jsonCreated = Json.createObjectBuilder();
        if (obj.getClass().getDeclaredFields().length == 0) {
            jsonCreated.add("", "");
            jsonCreated.build();
        }

        for (Field field : obj.getClass().getDeclaredFields()) {
            Class fieldType = field.getType();
            field.setAccessible(true);


            if (fieldType.isPrimitive()) {
                System.out.println("PRIMITIVE");
                jsonCreated.add(field.getName(), field.get(obj).toString());
                System.out.println(field.get(obj).toString());
                continue;
            }
            if (fieldType.isArray()) {
                System.out.println("ARRAY");
                JsonArrayBuilder arr = Json.createArrayBuilder();
                Object array = field.get(obj);
                int length = Array.getLength(array);
                for (int i = 0; i < length; i++) {
                    if (Array.get(array, i).getClass().getSuperclass() == null) {
                        arr.add(toJson(Array.get(array, i)));
                        continue;
                    }
                    arr.add(Array.get(array, i).toString());
                }
                jsonCreated.add(field.getName(), arr);
                System.out.println(field.get(obj).toString());
                continue;
            }
            if (!fieldType.isPrimitive() && !fieldType.isArray()) {
                System.out.println("OBJECT");
                Class field1 =field.get(obj).getClass().getSuperclass();
                if (field1 == null)
                    jsonCreated.add(field.getName(),Json.createObjectBuilder().build());
                else
                    jsonCreated.add(field.getName(), toJson(field.get(obj)));
                continue;
            }

        }
        JsonObject jsonObject = jsonCreated.build();
        System.out.println(jsonObject);
        return jsonObject.toString();

    }

//    private static String primitive(Object object) throws IllegalAccessException {
//
//        JsonObjectBuilder jsonCreated = Json.createObjectBuilder();
//        if (object.getClass().getDeclaredFields().length == 0) {
//            jsonCreated.add("", "");
//        }
//
//        for (Field field : object.getClass().getDeclaredFields()) {
//            Class fieldType = field.getType();
//            field.setAccessible(true);
//            if (fieldType.isPrimitive()) {
//                System.out.println("PRIMITIVE");
//                jsonCreated.add(field.getName(), field.get(object).toString());
//                System.out.println(field.get(object).toString());
//            }
//
//        }
//        return jsonCreated.toString();
//    }
}
