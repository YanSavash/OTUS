package magic;

public class Demo {

    public static void main(String[] args) {
        TestLoggingInterface magicClass = IoC.makeMagic();
        magicClass.calculation(6);
    }
}