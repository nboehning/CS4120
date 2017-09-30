/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

import static assignment2.Assignment2.printArray;

/**
 *
 * @author arawashdeh
 * Modified by: Nathan Boehning
 */
public class Assignment2 {

    public static void main(String[] args) {
        // TODO code application logic here
        int[] arr1 =  new int[]{ 8, 3, 10, 2, 1, 4};
        System.out.print("Pre bubble sort: ");
        printArray(arr1);
        Assignment2 ob1 = new Assignment2();
        ob1.bubbleSort(arr1);
        System.out.print("Post bubble sort: ");
        printArray(arr1);
        int[] arr2 = new int[] {8, 3, 10, 2, 1, 4};
        System.out.print("Pre merge sort: ");
        printArray(arr2);
        System.out.print("Post merge sort: ");
        ob1.MergeSort(arr2,0, arr2.length-1);
        printArray(arr2);
    }
    
    int[] bubbleSort (int a[])
    {
        // Following pseudocode from: https://en.wikipedia.org/wiki/Bubble_sort
        int n = a.length;
        do
        {
            int newn = 0;
            for(int i=1; i <= n-1; i++)
            {
                if (a[i-1] > a[i])
                {
                    int temp = a[i];
                    a[i] = a[i-1];
                    a[i-1] = temp;
                    newn = i;
                }
            }
            n = newn;
        }while(n != 0);
        return a;
    }
    
    static void printArray(int a[])
    {
        for (int i =0 ; i < a.length; i++)
            System.out.print(a[i] + " ");
        System.out.println();
    }
    
    void MergeSort(int arr2[], int Low, int High)
    {
        if (Low < High)
        {
            int mid = (Low + High)/ 2;
            MergeSort(arr2, Low, mid);
            MergeSort(arr2, mid+1, High);
            Merge(arr2, Low, mid, High);
        }
    }
    
    void Merge(int arr2[], int l, int M, int H)
    {
        int num1 = M - l + 1;
        int num2 = H - M;
        
        int L[] = new int [num1];
        int R[] = new int [num2];
        
        for(int i=0; i < num1; ++i)
            L[i] = arr2[l+i];
        for(int j=0; j<num2; ++j)
            R[j] = arr2[M+1+j];
        
        int i = 0,j = 0;
        
        int k=l;
        while (i < num1 && j < num2)
        {
            if (L[i] < R[j])
            {
                arr2[k] = L[i];
                i++;
            }
            else
            {
                arr2[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < num1)
        {
            arr2[k] = L[i];
            i++;
            k++;
        }
        while (j < num2)
        {
            arr2[k] = R[j];
            j++;
            k++;
        }
    }
}
