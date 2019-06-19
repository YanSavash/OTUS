package ru.netrax;

import com.google.gson.Gson;
import ru.netrax.jsonObjectWriter.Mson;
import ru.netrax.testObject.Experimental;
import ru.netrax.testObject.MyObject;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Mson mson = new Mson();
        Experimental obj = new Experimental(22, 't', new MyObject(1,"2"));
        System.out.println("obj " + obj);
        String json = mson.toJson(obj);


        Gson gson = new Gson();
//        String rightJson = gson.toJson(obj);
//        System.out.println("right  " + rightJson);
        Experimental obj2 = gson.fromJson(json, Experimental.class);

        System.out.println();
        System.out.println("obj2 " + obj2);
        System.out.println("obj  " + obj);
        System.out.println(obj.equals(obj2));
    }
}
