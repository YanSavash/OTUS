package hw03;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Runner {
    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, InvocationTargetException {
        Class<?> testClass = Framework.class;

        runOn(testClass);
    }
    private static void runOn(Class<?> testClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException {
        for (Method i : testClass.getDeclaredMethods()) {
            if (i.isAnnotationPresent(Test.class))
                try {
                    Constructor<?> constructor = testClass.getConstructor();
                    Object obj = constructor.newInstance();
                    for (Method before : testClass.getDeclaredMethods())
                        if (before.isAnnotationPresent(BeforeAll.class))
                            before.invoke(obj);
                    for (Method before : testClass.getDeclaredMethods())
                        if (before.isAnnotationPresent(BeforeEach.class))
                            before.invoke(obj);
                    i.invoke(obj);
                    for (Method after : testClass.getDeclaredMethods())
                        if (after.isAnnotationPresent(AfterEach.class))
                            after.invoke(obj);
                    for (Method before : testClass.getDeclaredMethods())
                        if (before.isAnnotationPresent(AfterAll.class))
                            before.invoke(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
        }

    }
}
