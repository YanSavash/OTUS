package magic;

import Annotations.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;

public class IoC {


    static TestLoggingInterface makeMagic() {
        InvocationHandler handler = new MagicInvocHandler(new TestLogging());

        return (TestLoggingInterface) Proxy.newProxyInstance(IoC.class.getClassLoader(), new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    private static Set<String> logMethodsStrings = new HashSet<>();

    static class MagicInvocHandler implements InvocationHandler {
        private final TestLoggingInterface magicClass;

        MagicInvocHandler(TestLoggingInterface myClass) {
            this.magicClass = myClass;
            for (Method m: magicClass.getClass().getDeclaredMethods())
                if (m.isAnnotationPresent(Log.class)) {
                    Log lg = m.getAnnotation(Log.class); //я знаю, что данная проверка необязательна - я для себя, практикую возможности аннотаций))
                    if (lg.mark().equals("true"))
                        logMethodsStrings.add(m.getName());
                }
        }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (logMethodsStrings.contains(method.getName())) {
                System.out.println("executed method:" + method.getName() + " param: " + args[0]);
            }
            return method.invoke(magicClass, args);
        }

        @Override
        public String toString() {
            return "InvocHandler{" + "magicClass=" + magicClass + '}';
        }
    }

}
