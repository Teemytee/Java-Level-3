import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ClassForTests {
    private static boolean isBeforeAfterOnlyOnes(Class aClass){
        int afterAnnCount = 0;
        int beforeAnnCount = 0;
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getAnnotation(AfterSuite.class) != null) {
                afterAnnCount++;
            }
            if (method.getAnnotation(BeforeSuite.class) != null) {
                beforeAnnCount++;
            }
        }
        return afterAnnCount == 1 && beforeAnnCount == 1;
    }

    public static void start(Class<?> aClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if(!isBeforeAfterOnlyOnes(aClass)){
            throw new RuntimeException();
        }
        final int min_priority = 1;
        final int max_priority = 10;
        Map<Integer, Method> map = new HashMap<>();
        for (Method method : aClass.getDeclaredMethods()) {
            if (method.getAnnotation(BeforeSuite.class) != null) {
                map.put(min_priority - 1, method);
            }
            if (method.getAnnotation(AfterSuite.class) != null) {
                map.put(max_priority + 1, method);
            }
            if (method.getAnnotation(Test.class) != null) {
                Test test = method.getAnnotation(Test.class);
                map.put(test.priority(), method);
            }
        }
        Object testCase = aClass.getDeclaredConstructor().newInstance();
        for (Integer key : map.keySet()) {
            map.get(key).setAccessible(true);
            map.get(key).invoke(testCase);
            map.get(key).setAccessible(false);
        }
    }
}
