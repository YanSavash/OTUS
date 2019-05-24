package magic;

public class Demo {
    public void action() {
        System.out.println("Вызов магии");
        new TestLogging().calculation(6);
    }

    public static void main(String[] args) {
        TestLoggingInterface magicClass = IoC.makeMagic();
        magicClass.calculation(6);
    }
}