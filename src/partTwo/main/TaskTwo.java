package partTwo.main;

public class TaskTwo {
    public static int[] checkArrayWithFour(int[] array){
        int catchUp = 0;
        int lastFourIndex = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 4){
                catchUp = 1;
                lastFourIndex = i;
            }
        }
        int[] result = new int[array.length - lastFourIndex - 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = array[i + lastFourIndex + 1];
        }
        try{
            catchUp = 1 / catchUp;
        } catch(ArithmeticException e){
            throw new RuntimeException();
        }
        return result;
    }

    public static boolean checkArrayOneAndFour(int[] array){
        boolean checker = false;
        for (int i = 0; i < array.length; i++) {
            if(array[i] == 4 || array[i] == 1){
                checker = true;
            }
        }
        return checker;
    }
}
