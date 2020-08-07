package lesson7;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        start(MyClass.class);
    }

    public static void start(Class<?> className) throws Exception {

        int beforeMethod = 0;
        int afterMethod = 0;

        Method beforeMethodIn = null;
        Method afterMethodIn = null;

        ArrayList<Method> al = new ArrayList<>();

        for (Method method : className.getDeclaredMethods()) {

            if (method.isAnnotationPresent(BeforeSuite.class)) {
                beforeMethod++;

                if (beforeMethod == 1) {
                    beforeMethodIn = method;
                } else {
                    throw new RuntimeException("Больше одного метода с аннотацией BeforeSuite");
                }
            }

            if (method.isAnnotationPresent(AfterSuite.class)) {
                afterMethod++;

                if (afterMethod == 1) {
                   afterMethodIn = method;
                } else {
                    throw new RuntimeException("Больше одного метода с аннотацией AfterSuite");
                }
            }

            if (method.isAnnotationPresent(Test.class)) {
                al.add(method);
            }
        }

        al.sort((o1, o2) -> o2.getAnnotation(Test.class).priority() - o1.getAnnotation(Test.class).priority());

        if (beforeMethodIn != null){
            beforeMethodIn.invoke( null);
        }
        for (Method o : al) {
            o.invoke(null);
        }
        if (afterMethodIn != null) {
            afterMethodIn.invoke(null);
        }

    }
}
