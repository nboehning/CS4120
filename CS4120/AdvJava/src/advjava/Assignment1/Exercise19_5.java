/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advjava.Assignment1;

import java.util.Random;

/**
 * @author Nathan Boehning
 * Date: 9/29/2017
 * Purpose: Create a generic method to return the maximum value
 *          from an array
 */
public class Exercise19_5 {
   public static void main(String args[]) {
        Integer[] ints = new Integer[100];
        Double[] doubles = new Double[100];
        Random rand = new Random();
        for(int i = 0; i < 100; i++) {
            ints[i] = rand.nextInt(10000);
            doubles[i] = rand.nextDouble();
        }
        
        int max = max(ints);
        System.out.println("Max Integer Value: " + max);
        
        double max2 = max(doubles);
        System.out.println("Max Double Value: " + max2);
   } 
   
   public static <E extends Comparable<E>> E max(E[] arr) {
       E maxVal = arr[0];
       
       for(int i = 1; i < arr.length; i++) {
           if(maxVal.equals(arr[i]))
               maxVal = arr[i];
       }
       
       return maxVal;
   }
}
