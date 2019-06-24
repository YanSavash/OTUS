package ru.netrax.testObject;

import java.util.*;

public class Experimental {

    private final int number;
    private final char symbol;
    private final int[] numbers;
    private final char[] symbols;
    private MyObject myObject;
    private MyObject[] myObjects;
    private String nullString = null;

    @Override
    public String toString() {
        return "Experimental{" +
                "number=" + number +
                ", symbol=" + symbol +
                ", numbers=" + Arrays.toString(numbers) +
                ", symbols=" + Arrays.toString(symbols) +
                ", myObject=" + myObject +
                ", myObjects=" + Arrays.toString(myObjects) +
                ", nullString='" + nullString + '\'' +
                ", list=" + list +
                ", set=" + set +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experimental that = (Experimental) o;
        return number == that.number &&
                symbol == that.symbol &&
                Arrays.equals(numbers, that.numbers) &&
                Arrays.equals(symbols, that.symbols) &&
                Objects.equals(myObject, that.myObject) &&
                Arrays.equals(myObjects, that.myObjects) &&
                Objects.equals(nullString, that.nullString) &&
                Objects.equals(list, that.list) &&
                Objects.equals(set, that.set);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(number, symbol, myObject, nullString, list, set);
        result = 31 * result + Arrays.hashCode(numbers);
        result = 31 * result + Arrays.hashCode(symbols);
        result = 31 * result + Arrays.hashCode(myObjects);
        return result;
    }

    private final List<String> list = new ArrayList<>() {{
        add("Hello world");
        add("Nice world");
        add("Beatiful world");
    }};

    private final Set<Integer> set = new HashSet<>() {{
        add(5);
        add(6);
        add(7);
    }};

    public Experimental(int number, char symbol, MyObject object) {
        this.number = number;
        this.symbol = symbol;
        this.numbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        this.symbols = new char[]{'t', 'r', 's', 'a', 'z'};
        this.myObject = object;
        this.myObjects = new MyObject[]{object, object, object};
    }

}
