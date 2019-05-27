package magic;

import Annotations.Log;

public class TestLogging implements TestLoggingInterface {

    @Log(mark = "true")
    public void calculation(int param) {
        System.out.println("Внимание! Работает калькулятор! Параметры: "+ param);
    };
}
