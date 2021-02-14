import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ClassWithTests classWithTests = new ClassWithTests();
        ClassForTests classForTests = new ClassForTests();
        classForTests.start(classWithTests.getClass());
    }
}
