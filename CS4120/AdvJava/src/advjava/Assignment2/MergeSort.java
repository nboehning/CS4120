package advjava.Assignment2;

/**
 * @author Nathan Boehning
 * Date: 10/4/2017
 * Purpose: Create the merge sort to implement in the parallel merge
 */
public class MergeSort {
    public static <E extends Comparable<E>> void mergeSort(E[] lists) {
        if(lists.length > 1) {
           E[] firstHalf = (E[]) new Comparable[lists.length / 2];
           System.arraycopy(lists, 0, firstHalf, 0, lists.length / 2);
           mergeSort(firstHalf);
           
           int secondHalfLength = lists.length - lists.length / 2;
           E[] secondHalf = (E[]) new Comparable[secondHalfLength];
           System.arraycopy(lists, lists.length/2, secondHalf, 0, secondHalfLength);
           mergeSort(secondHalf);
           
           merge(firstHalf, secondHalf, lists);           
        }
    }
    
    public static <E extends Comparable<E>> void merge(E[] list1, E[] list2, E[] temp) {
        int current1 = 0;
        int current2 = 0;
        int current3 = 0;
        
        while(current1 < list1.length && current2 < list2.length) {
            if(list1[current1].compareTo(list2[current2]) < 0) {//list1[current1] < list2[current2]) {
                temp[current3++] = list1[current1++];
            } else {
                temp[current3++] = list2[current2++];
            }
        }
        
        while(current1 < list1.length)
            temp[current3++] = list1[current1++];
        while(current2 < list2.length)
            temp[current3++] = list2[current2++];
    }
} // End of class MergeSort
