import static com.google.common.base.Objects.equal;


public class HelloOtus {

    public static void main(String args[ ])
    {
        System.out.println();
        String a = ("Строка а - Hello Otus");
        String b = ("Строка b - Привет Отус");
        System.out.println(a);
        System.out.println(b);
        System.out.println("equal(a, null) = " + equal(a, null));
        System.out.println("equal(null, null) = " + equal(null, null));
        System.out.println("equal(a, b) = " + equal(a, b));
    }
}
