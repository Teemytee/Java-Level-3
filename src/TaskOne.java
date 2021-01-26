public class TaskOne<T> {
    public T[] doTaskOne(T[] array, int indexOne, int indexTwo){
        T tmp = array[indexOne];
        array[indexOne] = array[indexTwo];
        array[indexTwo] = tmp;
        return array;
    }
}
