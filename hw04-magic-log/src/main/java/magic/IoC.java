package magic;

import Annotations.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class IoC {


    static TestLoggingInterface makeMagic() {
        InvocationHandler handler = new MagicInvocHandler(new TestLogging());

        return (TestLoggingInterface) Proxy.newProxyInstance(IoC.class.getClassLoader(), new Class<?>[]{TestLoggingInterface.class}, handler);
    }


    static class MagicInvocHandler implements InvocationHandler {
        private final TestLoggingInterface magicClass;

        MagicInvocHandler(TestLoggingInterface myClass) {
            this.magicClass = myClass;
        }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.isAnnotationPresent(Log.class)) {
                Log lg = method.getAnnotation(Log.class);
                if (lg.mark().equals("true"))
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
