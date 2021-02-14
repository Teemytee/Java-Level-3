public class ClassWithTests {
    @BeforeSuite
    private void beforeSuite(){
        System.out.println("BeforeSuite метод");
    }

    @Test(priority = 1)
    private void testOne(){
        System.out.println("Тест 1, приоритет = 1");
    }

    @Test(priority = 2)
    private void testTwo(){
        System.out.println("Тест 2, приоритет = 2");
    }

    @Test(priority = 3)
    private void testThree(){
        System.out.println("Тест 3, приоритет = 3");
    }

    @Test(priority = 4)
    private void testFour(){
        System.out.println("Тест 4, приоритет = 4");
    }

    @AfterSuite
    private void afterSuite(){
        System.out.println("AfterSuite метод");
    }
}
