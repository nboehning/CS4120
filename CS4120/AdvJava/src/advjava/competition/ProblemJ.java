package advjava.Competition;

import java.util.Scanner;

/**
 * @author Nathan Boehning
 * Date: 
 * Purpose: 
 */
public class ProblemJ {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int numInputs = scan.nextInt();
        String[] solutions = new String[numInputs];
        for(int i = 0; i < numInputs; i++) {
            String toDo = scan.next();
            switch(toDo) {
                case "e":
                    String toEncrypt = new String(scan.nextLine().substring(1));
                    String encrypted = encrypt(toEncrypt);
                    System.out.println(encrypted);
                    //solutions[i] = encrypted;
                    break;
                case "d":
                    String toDecrypt = new String(scan.nextLine().substring(1));
                    String decrypted = decrypt(toDecrypt);
                    System.out.println(decrypted);
                    //solutions[i] = decrypted;
                    break;
            }
        }
        
       // for(int i = 0; i < numInputs; i++) {
       //     System.out.println(solutions[i]);
       // }
    }
    
    public static String encrypt(String input) {
        char[] newString = new char[input.length()];
        int[] converter = new int[input.length()];
        for(int i = 0; i < input.length(); i++) {
            switch(input.charAt(i)) {
                case ' ':
                    converter[i] = 0;
                    break;
                default:
                    converter[i] = input.charAt(i) - 'a' + 1;
                    break;
            }
        }
        
        for(int i = 1; i < converter.length; i++) {
            converter[i] = converter[i] + converter[i-1];
        }
        
        for(int i = 1; i < converter.length; i++) {
            converter[i] = converter[i] % 27;
        }
                
        for(int i = 0; i < converter.length; i++) {
            switch(converter[i]) {
                case 0:
                    newString[i] = ' ';
                    break;
                default:
                    newString[i] = (char) ((char) converter[i] + 'a' - 1);
                    break;
            }
        }
        
        return String.valueOf(newString);
    }
    
    public static String decrypt(String input) {
        //System.out.println(input);
        char[] newString = new char[input.length()];
        int[] converter = new int[input.length()];
        int[] newConverter = new int[input.length()];
        boolean isSpace = false;
        int multi = 1;
        
        for(int i = 0; i < input.length(); i++) {
            switch(input.charAt(i)) {
                case ' ':
                    converter[i] = 0;
                    break;
                default:
                    converter[i] = input.charAt(i) - 'a' + 1;
                    break;
            }
        }
        
        newConverter[0] = converter[0];
        for(int i = 1; i < converter.length; i++) {
            if(((converter[i] + (27 * multi)) < (converter[i-1] + (27 * multi))) && i > 1) {
                multi++;
            }
            if(isSpace) {
                isSpace = false;
            }
            else if(i < converter.length - 1) {
                if(converter[i] == converter[i+1]) {
                    //Next one is a space
                    newConverter[i+1] = converter[i] + (27 * multi);
                    newConverter[i] = converter[i] + (27 * multi);
                    isSpace = true;
                }
                else {
                    newConverter[i] = converter[i] + (27 * multi);
                }
            }           
            else {
                newConverter[i] =  converter[i] + (27 * multi);
            }
        }
        
        //for(int i = 0; i < newConverter.length; i++)
         //   System.out.print(newConverter[i] + " ");
        
        for(int i = converter.length-1; i > 0; i--) {
            newConverter[i] = newConverter[i] - newConverter[i-1];
        }
        
        for(int i = 0; i < newConverter.length; i++) {
            switch(newConverter[i]) {
                case 0:
                    newString[i] = ' ';
                    break;
                default:
                    newString[i] = (char) ((char) newConverter[i] + 'a' - 1);
                    break;
            }
        }
        
        return String.valueOf(newString);
    }
}
