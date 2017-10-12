package advjava.Midterm;

import java.util.Random;

/**
 * @author Nathan Boehning
 * Date: 10/11/2017
 * Purpose: Create a generic bubble sort method following 
 *          instructions on the midterm
 */
public class QuestionOne {
    
    public static void main(String[] args) {
        Random rand = new Random();
        Integer intArr[] = new Integer[1000];
        String strArr[] = new String[1000];
        
        for(int i = 0; i < 1000; i++) {
            intArr[i] = rand.nextInt(100);
            strArr[i] = generateString();
        }
        
        System.out.println("Integer array before sorting: ");
        for(int i = 0; i < 100; i++) {
            System.out.print(intArr[i] + " ");
        }
        
        System.out.println();
        
        System.out.println("Integer array sorted ascending: ");
        bubbleSort(intArr, true);
        for(int i = 0; i < 100; i++) {
            System.out.print(intArr[i] + " ");
        }
        
        System.out.println();
        
        System.out.println("Integer array sorted descending: ");
        bubbleSort(intArr, false);
        for(int i = 0; i < 100; i++) {
            System.out.print(intArr[i] + " ");
        }
        
        System.out.println();
        
        System.out.println("String array before sorting: ");
        for(int i = 0; i < 100; i++) {
            System.out.print(strArr[i] + " ");
        }
        
        System.out.println();
        
        System.out.println("String array sorted ascending: ");
        bubbleSort(strArr, true);
        for(int i = 0; i < 100; i++) {
            System.out.print(strArr[i] + " ");
        }
        
        System.out.println();
        
        System.out.println("String array sorted descending: ");
        bubbleSort(strArr, false);
        for(int i = 0; i < 100; i++) {
            System.out.print(strArr[i] + " ");
        }
        
        System.out.println();
    }
    
    private static String generateString() {
        Random rand = new Random();
        
        int strLen = rand.nextInt(9) + 1;
        char[] strHolder = new char[strLen];
        for(int j = 0; j < strLen; j++) {
            strHolder[j] = (char)(rand.nextInt(26) + 97);
        }
        
        return new String(strHolder);
    }
    
    public static <E extends Comparable<E>> void bubbleSort(E[] list, boolean isAscending) {
        int n = list.length;
        while(n != 0) {
            int newn = 0;
            for(int i = 1; i < n; i++) {
                if(list[i-1].compareTo(list[i]) > 0 && isAscending) {
                    E temp = list[i-1];
                    list[i-1] = list[i];
                    list[i] = temp;
                    newn = i;
                } else if(list[i-1].compareTo(list[i]) < 0 && !isAscending) {
                    E temp = list[i-1];
                    list[i-1] = list[i];
                    list[i] = temp;
                    newn = i;
                }
            }
            n = newn;
        }
    }
}
