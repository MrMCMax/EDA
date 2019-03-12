package ejemplos.tema2;


/**
 * Write a description of class NaturalSort here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NaturalSort
{
    Integer[] array;
    public static <E extends Comparable<E>> void insertionSort(E[] a) {
        for (int i = 1; i < a.length; i++) {
            E elem = a[i];
            int j = i;
            while (j > 0 && a[j - 1].compareTo(elem) > 0) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = elem;
        }
    }
    
    public NaturalSort(Integer[] array) {
        this.array = array;
        insertionSort(array);
    }
}
