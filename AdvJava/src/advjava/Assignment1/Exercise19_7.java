/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advjava.Assignment1;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Nathan Boehning
 * Date: 9/29/2017
 * Purpose: Create an employee class and implement a generic binary search
 *          using that employee class, as well as a randomly generated integer
 *          array and string array
 */
public class Exercise19_7 {
    public static void main(String args[]) {
        // Initialize scanner and random value generator
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        
        // Array of integers
        Integer ints[] = new Integer[10];
        
        // Generate the integer array
        for(int i = 0; i < 10; i++)
            ints[i] = rand.nextInt(99);
        
        // Sort the integer array for binary search
        Arrays.sort(ints);
        
        // Print the integer array
        System.out.println("Integer Array:");
        for(int i = 0; i < 5; i++)
           System.out.println(ints[i]);
        
        System.out.println();
        
        System.out.print("Please enter an integer to find: ");
        int input1 = scan.nextInt();
        
        // Get the index and output the location if any of the integer
        int index = binarySearch(ints, input1);
        if(index >= 0){
            System.out.println("The integer was located at index: " + index);
        }
        else {
            System.out.println("The integer you entered was not located in the integer array.");
        }
        
        // Create the string array
        String[] strArr = new String[10];
        
        // Generate 10 strings of random length between 1 and 10
        for(int i = 0; i < 10; i++) {
            int strLen = rand.nextInt(11 - 1) + 1;
            char[] strHolder = new char[strLen];
            for(int j = 0; j < strLen; j++) {
                strHolder[j] = (char)(rand.nextInt(26) + 97);
            }
            strArr[i] = new String(strHolder);
        }
        
        // Sort the array for binary search
        Arrays.sort(strArr);
        
        System.out.println("String Array:");
        for(int i = 0; i < 10; i++)
            System.out.println(strArr[i]);
        
        System.out.print("Please enter a string to find: ");
        String input2 = scan.next();
        
        // Binary search the string array and return the index of the string, if any
        index = binarySearch(strArr, input2);
        
        // Output result
        if(index >= 0)
            System.out.println("The string was located at index: " + index);
        else
            System.out.println("The string you entered was not in the string array.");
        
        // Create an employee array
        Employee[] empArr = new Employee[10];
        
        System.out.println("Employee Array: ");
        
        // Initialize (to generate names and salaries) the array of employees
        for(int i = 0; i < 10; i++) {
            empArr[i] = new Employee();
        }
        
        // Sort array for binary search
        Arrays.sort(empArr);
        
        // Print out initialized employees for user to see
        for(int i = 0; i < 10; i++)
            System.out.println(empArr[i]);
        
        // Get the name to find from the user
        System.out.print("\nEnter the name of the employee you wish to find: ");
        String first = scan.next();
        
        System.out.print("Enter the last name of the employee you wish to find: ");
        String last = scan.next();
        System.out.println();
        // Generate a dummy employee to compare to
        Employee tempEmp = new Employee(first, last, 0);
        
        index = binarySearch(empArr, tempEmp);
        
        // Output result
        if(index >= 0)
            System.out.println("The employee was located at index: " + index);
        else
            System.out.println("The employee name you entered was not in the employee array.");
        
    }
    
    public static <E extends Comparable<E>> int binarySearch(E[] arr, E valToFind) {
        int low = 0;
        int high = arr.length - 1;
        
        while(high >= low) {
            int mid = (low + high) / 2;
            if(valToFind.compareTo(arr[mid]) < 0)
                high = mid-1;
            else if(valToFind.compareTo(arr[mid]) == 0)
                return mid;
            else
                low = mid + 1;
        }
        
        return -low - 1;
    }
}

class Employee implements Comparable<Employee>{
    public double salary;
    public String firstName;
    public String lastName;
    
    Employee(String first, String last, double sal) {
        firstName = first;
        lastName = last;
        salary = sal;
    }
    
    Employee() {
        generateFirstName();
        generateLastName();
        generateSalary();
    }
    
    private void generateFirstName() {
        Random rand = new Random();
        
        int strLen = rand.nextInt(11 - 3) + 3;
        char[] strHolder = new char[strLen];
        for(int j = 0; j < strLen; j++) {
            if(j == 0)
                strHolder[j] = (char)(rand.nextInt(26) + 65);
            strHolder[j] = (j == 0) ? (char)(rand.nextInt(26) + 65) : (char)(rand.nextInt(26) + 97);
        }
        
        firstName = new String(strHolder);
    }
    
    private void generateLastName() {
        Random rand = new Random();
        
        int strLen = rand.nextInt(15 - 5) + 5;
        char[] strHolder = new char[strLen];
        for(int j = 0; j < strLen; j++) {
            if(j == 0)
                strHolder[j] = (char)(rand.nextInt(26) + 65);
            strHolder[j] = (j == 0) ? (char)(rand.nextInt(26) + 65) : (char)(rand.nextInt(26) + 97);
        }
        
        lastName = new String(strHolder);
    }
    
    private void generateSalary() {
        Random rand = new Random();
        
        salary = rand.nextInt(100000 - 32000) + 32000;
    }
    
    @Override
    public int compareTo(Employee other) {
        String thisFullName = this.firstName.toLowerCase() + this.lastName.toLowerCase();
        String otherFullName = other.firstName.toLowerCase() + other.lastName.toLowerCase();
        return thisFullName.compareTo(otherFullName);
    }

    @Override
    public String toString() {
        return "First Name: " + firstName + " Last Name: " + lastName + " Salary: " + salary;
    }
}

