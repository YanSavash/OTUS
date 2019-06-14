package ru.netrax.testObject;

import java.util.*;

public class Experimental {

    private final int number;
    private final char symbol;
    private final int[] numbers;
    private final char[] symbols;
//    private final Object object;
//    private final Object[] objects;
//    private final List<String> list = new ArrayList<>(){{
//        add("Hello world");
//        add("Nice world");
//        add("Beatiful world");
//    }};;
//    private final Set<Integer> set = new HashSet<>(){{
//        add(5);
//        add(6);
//        add(7);
//    }};;
//    private final Map<List,Set> map = new HashMap<>(){{
//        put(list,set);
//    }};

    public Experimental(int number, char symbol, Object object) {
        this.number = number;
        this.symbol = symbol;
        this.numbers = new int[]{1,2,3,4,5,6,7,8,9,10};
        this.symbols = new char[]{'t','r','s','a','z'};
//        this.object = object;
//        this.objects = new Object[]{object,object,object};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experimental that = (Experimental) o;
        return number == that.number &&
                symbol == that.symbol &&
                Arrays.equals(numbers, that.numbers) &&
                Arrays.equals(symbols, that.symbols);// &&
//                Objects.equals(object, that.object) &&
//                Arrays.equals(objects, that.objects); //&&
//                Objects.equals(list, that.list) &&
//                Objects.equals(set, that.set) &&
//                Objects.equals(map, that.map);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(number, symbol);//, object); //, list, set, map);
        result = 31 * result + Arrays.hashCode(numbers);
        result = 31 * result + Arrays.hashCode(symbols);
  //      result = 31 * result + Arrays.hashCode(objects);
        return result;
    }
}
