package advjava.Assignment2;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author Nathan Boehning
 * Date: 10/4/2017
 * Purpose: Implement a parallel sum program, modified from the parallel max example
 *          given in the textbook.
 */
public class Exercise30_15 {
    public static void main(String[] args) {
        double[] nums = new double[7000000];
        for(int i = 0; i < 7000000; i++) {
            nums[i] = Math.random() * 5000;
        }
        
        System.out.println("Gets to the parallel sums");
        long startTime = System.currentTimeMillis();
        System.out.println("\nThe sum is: " + parallelSum(nums));
        long endTime = System.currentTimeMillis();
        System.out.println("The number of processors is "+ Runtime.getRuntime()
                .availableProcessors() + " completed in " + (endTime - startTime) 
                + " milliseconds"); 
        
        startTime = System.currentTimeMillis();
        System.out.print("The sum is " + sequentialSum(nums));
        endTime = System.currentTimeMillis();
        System.out.println(" completed in " + (endTime - startTime)
                + " milliseconds"); 
    }
    
    public static double sequentialSum(double[] nums) {
        double sum = nums[0];
        for(int i = 1; i < nums.length; i++)
            sum += nums[i];
        
        return sum;
    }   
    
    public static double parallelSum(double[] list) {
        RecursiveTask<Double> mainTask = new SumTask(list, 0, list.length);
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(mainTask);
    }
    
    private static class SumTask extends RecursiveTask<Double> {
        private double[] list;
        private final int THRESHOLD = 500;
        private int low;
        private int high;
        int count;
        
        SumTask(double[] list, int low, int high) {
            this.list = list;
            this.low = low;
            this.high = high;
        }
        
        @Override
        public Double compute() {
            if(high-low < THRESHOLD) {
                double sum = 0.0;
                for(int i = low; i < high; i++) {
                    sum += list[i];
                    count++;
                }
                
                System.out.println("Number summed in parallel: " + count);
                return sum;
            }
            else {
                int mid = (low + high) / 2;
                RecursiveTask<Double> left = new SumTask(list, low, mid);
                RecursiveTask<Double> right = new SumTask(list, mid, high);
                
                right.fork();
                left.fork();
                return left.join() + right.join();
            }
        }
    }
} // End of class Exercise30_15
