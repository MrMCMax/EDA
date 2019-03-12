package ejemplos.tema2;

import java.util.concurrent.ThreadLocalRandom;
/**
 * Write a description of class TestMerges here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestMerges
{
    public static void testMerges(String[] args) {
        Integer[] array = randomArray(1000000);
        Integer[] array2 = randomArray(1000000);
        long t1 = System.nanoTime();
        long t2;
        MergeSort.mergeSort(array);
        t2 = System.nanoTime();
        long normalTime = t2 - t1;
        t1 = System.nanoTime();
        MergeSort.mergeThreads(array2);
        t2 = System.nanoTime();
        long threadTime = t2 - t1;
        System.out.println("Normal Time: " + normalTime);
        System.out.println("Thread Time: " + threadTime);
    }
    
    public static void test() {
        Integer[] array = new Integer[100];
        for (int i = 0; i < 100; i++) {
            array[i] = new Integer(ThreadLocalRandom.current().nextInt(0, 1001));
        }
        MergeSort.mergeThreads(array);
        for (int i = 0; i < 20; i+=5) {
            System.out.println(array[i] + ", " + array[i + 1] + ", " + array[i + 2] + 
                ", " + array[i + 3] + ", " + array[i + 4]);
        }
    }
    
    public static Integer[] randomArray(int numb) {
        Integer[] array = new Integer[numb];
        for (int i = 0; i < numb; i++) {
            array[i] = new Integer(ThreadLocalRandom.current().nextInt(0, 1001));
        }
        return array;
    }
    
    NaturalSort sort;
    
    public TestMerges(String[] args) {
       sort = new NaturalSort(randomArray(15));
    }
}
