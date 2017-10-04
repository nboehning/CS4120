package advjava.Assignment2;

import java.util.Random;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

/**
 * @author Nathan Boehning
 * Date: 10/4/2017
 * Purpose: Create and implement a parallel merge sort on an array of random 
 *          integers and strings.
 */

public class Exercise30_13 {
    
    public static void main(String[] args) {
        final int SIZE = 700000;
        Integer[] list1 = new Integer[SIZE];
        Integer[] list2 = new Integer[SIZE];
        String[] list3 = new String[SIZE];
        String[] list4 = new String[SIZE];
        
        
        
        for(int i=0; i < list1.length; i++) {
            list1[i] = (int)(Math.random() * 1000000);
            list2[i] = (int)(Math.random() * 1000000);
            list3[i] = generateString();
            list4[i] = generateString();
        }
        long startTime = System.currentTimeMillis();
        parallelMergeSort(list1);
        long endTime = System.currentTimeMillis();
        System.out.println("\nInteger Parallel time with " + Runtime.getRuntime()
                .availableProcessors() + " processors is " + (endTime - startTime)
                + " milliseconds");
        
        startTime = System.currentTimeMillis();
        MergeSort.mergeSort(list2);
        endTime = System.currentTimeMillis();
        System.out.println("Integer Sequential time with " + (endTime - startTime) 
                + " milliseconds"); 
        
        
        startTime = System.currentTimeMillis();
        parallelMergeSort(list3);
        endTime = System.currentTimeMillis();
        System.out.println("String Parallel time with " + Runtime.getRuntime()
                .availableProcessors() + " processors is " + (endTime - startTime)
                + " milliseconds");
        
        startTime = System.currentTimeMillis();
        MergeSort.mergeSort(list4);
        endTime = System.currentTimeMillis();
        System.out.println("String Sequential time with " + (endTime - startTime)
                + " milliseconds"); 
    }
    
    private static String generateString() {
        Random rand = new Random();
        
        int strLen = rand.nextInt(11 - 3) + 3;
        char[] strHolder = new char[strLen];
        for(int j = 0; j < strLen; j++) {
            if(j == 0)
                strHolder[j] = (char)(rand.nextInt(26) + 65);
            strHolder[j] = (j == 0) ? (char)(rand.nextInt(26) + 65) : (char)(rand.nextInt(26) + 97);
        }
        
        return new String(strHolder);
    }
    
    public static <E extends Comparable<E>> void parallelMergeSort(E[] list) {
        RecursiveAction mainTask = new SortTask(list);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(mainTask);
    }
    
    private static class SortTask<E extends Comparable<E>> extends RecursiveAction {
        private final int THRESHOLD = 500;
        private E[] list;
        
        SortTask(E[] list) {
            this.list = list;
        }
        
        @Override
        protected void compute() {
            if(list.length < THRESHOLD)
                java.util.Arrays.sort(list);
            else {
                E[] firstHalf = (E[]) new Comparable[list.length/2];               
                System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
                int secondHalfLength = list.length - list.length / 2;
                E[] secondHalf = (E[]) new Comparable[secondHalfLength];
                System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);
                
                invokeAll(new SortTask(firstHalf), new SortTask(secondHalf));
                
                MergeSort.merge(firstHalf, secondHalf, list);
            }
        }
    }
} // End of class Exercise30_13
