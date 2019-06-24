package ru.netrax.testObject;

import java.util.Objects;

public class MyObject {

    public int a;
    public String b;

    public MyObject(int a, String b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "MyObject{" +
                "a=" + a +
                ", b='" + b + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyObject myObject = (MyObject) o;
        return a == myObject.a &&
                b.equals(myObject.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
