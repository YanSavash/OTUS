package ru.netrax;

import com.google.gson.Gson;
import ru.netrax.jsonObjectWriter.Mson;
import ru.netrax.testObject.Experimental;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Mson mson = new Mson();
        Experimental obj = new Experimental(22, 't', new Object());

        String json = mson.toJson(obj);

        //System.out.println(json);

        Gson gson = new Gson();
        String rightJson = gson.toJson(obj);
        System.out.println(rightJson);
        Experimental obj2 = gson.fromJson(json, Experimental.class);
        System.out.println();
        System.out.println(obj.equals(obj2));
    }
}
