import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        testTaskOne();
        testTaskTwo();
        testTaskThree();
    }

    public static void testTaskOne(){
        System.out.println("-- Задача 1 --");
        TaskOne<String> testingTaskOne = new TaskOne<String>();
        System.out.println("Изначальный массив:");
        String[] strings = {"Hello", "world", "fine"};
        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
        }
        testingTaskOne.doTaskOne(strings, 1, 2);
        System.out.println("Измененный массив:");
        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
        }

    }

    public static void testTaskTwo(){
        TaskTwo<Integer> testingTaskTwo = new TaskTwo<Integer>();
        Integer[] array = {1, 3, 5};
        ArrayList<Integer> newArray = testingTaskTwo.doTaskTwo(array);

    }

    public static void testTaskThree(){
        System.out.println("-- Задача 3 --");
        // Яблоки и их коробка, а также добавление фруктов в коробку
        Apple apple1 = new Apple();
        Apple apple2 = new Apple();
        Apple apple3 = new Apple();
        Box<Apple> appleBox = new Box<>();
        appleBox.addFruit(apple1);
        appleBox.addFruit(apple2);
        appleBox.addFruit(apple3);
        // Апельсины и их коробка, а также добавление фруктов в коробку
        Orange orange1 = new Orange();
        Orange orange2 = new Orange();
        Orange orange3 = new Orange();
        Box<Orange> orangeBox = new Box<>();
        orangeBox.addFruit(orange1);
        orangeBox.addFruit(orange2);
        orangeBox.addFruit(orange3);
        // Сравнение весов коробок
        System.out.println("Равен ли вес коробок: " + appleBox.Compare(orangeBox));
        // Пересыпаем яблоки из одной коробки
        // в другую новую коробку  для яблок
        Box<Apple> anotherAppleBox = new Box<>();
        System.out.println("До пересыпания");
        System.out.println("Первая коробка " + appleBox);
        System.out.println("Вторая коробка " + anotherAppleBox);
        appleBox.Pour(anotherAppleBox);
        System.out.println("После пересыпания");
        System.out.println("Первая коробка " + appleBox);
        System.out.println("Вторая коробка " + anotherAppleBox);



    }
}
