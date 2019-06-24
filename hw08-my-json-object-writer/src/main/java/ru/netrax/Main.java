package ru.netrax;

import com.google.gson.Gson;
import ru.netrax.jsonObjectWriter.Mson;
import ru.netrax.testObject.Experimental;
import ru.netrax.testObject.MyObject;

import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Mson mson = new Mson();
        Experimental obj = new Experimental(22, 't', new MyObject(1, "2"));
        System.out.println("obj " + obj);
        String json = mson.toJson(obj);
        System.out.println(json);

        Gson gson = new Gson();
        Experimental obj2 = gson.fromJson(json, Experimental.class);

        System.out.println();
        System.out.println("obj2 " + obj2);
        System.out.println("obj  " + obj);
        System.out.println(obj.equals(obj2));

        System.out.println(mson.toJson(null));
        System.out.println(gson.toJson(null));

        System.out.println(mson.toJson((byte)1));
        System.out.println(gson.toJson((byte)1));

        System.out.println(mson.toJson((short)1f));
        System.out.println(gson.toJson((short)1f));

        System.out.println(mson.toJson(1));
        System.out.println(gson.toJson(1));

        System.out.println(mson.toJson(1L));
        System.out.println(gson.toJson(1L));

        System.out.println(mson.toJson(1f));
        System.out.println(gson.toJson(1f));

        System.out.println(mson.toJson(1d));
        System.out.println(gson.toJson(1d));

        System.out.println(mson.toJson("aaa"));
        System.out.println(gson.toJson("aaa"));

        System.out.println(mson.toJson('a'));
        System.out.println(gson.toJson('a'));

        System.out.println(mson.toJson(new int[] {1, 2, 3}));
        System.out.println(gson.toJson(new int[] {1, 2, 3}));

        System.out.println(mson.toJson(List.of(1, 2 ,3)));
        System.out.println(gson.toJson(List.of(1, 2 ,3)));

        System.out.println(mson.toJson(Collections.singletonList(1)));
        System.out.println(gson.toJson(Collections.singletonList(1)));
    }
}
