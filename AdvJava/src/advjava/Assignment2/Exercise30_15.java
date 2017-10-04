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
        double[] nums = new double[9000000];
        for(int i = 0; i < 9000000; i++)
            nums[i] = Math.random() * 5000;
        
        System.out.println("Gets to the parallel sums");
        long startTime = System.currentTimeMillis();
        System.out.println("\nThe sum is: " + parallelSum(nums));
        long endTime = System.currentTimeMillis();
        System.out.println("The number of processors is "+ Runtime.getRuntime()
                .availableProcessors() + " completed in " + (endTime - startTime) 
                + " milliseconds"); 
        
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
                }
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
