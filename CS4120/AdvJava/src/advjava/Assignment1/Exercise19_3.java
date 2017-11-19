/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advjava.Assignment1;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Nathan Boehning
 * Date: 9/29/2017
 * Purpose: Create a generic function to remove duplicate values.
 */
public class Exercise19_3 {
    public static void main(String args[]){
        ArrayList<Integer> ints = new ArrayList<>();
        ArrayList<Character> chars = new ArrayList<>();
        Random rand = new Random();
        
        for(int i = 0; i < 100; i++){
            ints.add(rand.nextInt(9));
            chars.add((char)(rand.nextInt(26)+97));
        }
        ints = removeDuplicates(ints);
        chars = removeDuplicates(chars);
        System.out.print("List of integers with duplicates removed: ");
        for(int i = 0; i < ints.size(); i++) {
            System.out.println(ints.get(i));
        }
        System.out.print("List of characters with duplicates removed: ");
        for(int i = 0; i < chars.size(); i++) {
            System.out.println(chars.get(i));
        }
        
        
        
    }
    
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        int count = list.size();
        
        for(int i = 0; i < count; i++)
            for(int j = i + 1; j < count; j++)
                if(list.get(i).equals(list.get(j))) {
                    list.remove(j--);
                    count--;
                }     
        return list;
    }
}
