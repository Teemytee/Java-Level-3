import java.util.ArrayList;
import java.util.Arrays;

public class TaskTwo<T> {
    public ArrayList<T> doTaskTwo(T[] array){
        ArrayList<T> arrayList = new ArrayList<T>(Arrays.asList(array));
        return arrayList;
    }
}
