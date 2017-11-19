package advjava.Midterm;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author Nathan Boehning
 * Date: 10/11/2017
 * Purpose: Complete second question in midterm
 */
public class QuestionTwo {
    
    private static final int loopTo = 1000000;
    
    public static void main(String[] args) {      
        
        long startTime = System.currentTimeMillis();
        double sequentialSum = sequentialAdd();
        long endTime = System.currentTimeMillis();
        
        System.out.println("Sum gotten with sequential add: " + sequentialSum + ", completed in " + (endTime - startTime) + " milliseconds");
        
        startTime = System.currentTimeMillis();
        double parallelSum = sequentialAdd();
        endTime = System.currentTimeMillis();
        
        System.out.println("Sum gotten with parallel add: " + parallelSum + ", completed in " + (endTime - startTime) + " milliseconds");
    }
    
    public static double sequentialAdd() {
        double sum = 0.0;
        
        for(double i = 0.0; i < loopTo; i += 0.0001) {
            sum += 0.0001;
        }
        
        return sum;
    }
    
    public static double ParallelAdd() {
        RecursiveTask<Double> task = new AddTask(0, loopTo);
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(task);
    }
    
    private static class AddTask extends RecursiveTask<Double> {
        private final int THRESHOLD = loopTo / Runtime.getRuntime().availableProcessors();
        private final int high;
        private final int low;
        
        AddTask(int low, int high) {
            this.low = low;
            this.high = high;
        }
                
        @Override
        public Double compute() {
            if(high-low < THRESHOLD) {
                double sum = 0.0;
                for(double i = low; i < high; i+= 0.0001) {
                    sum += 0.0001;
                }
                return sum;               
            }
            else {
                int mid = (low + high) / 2;
                RecursiveTask<Double> left = new AddTask(low, mid);
                RecursiveTask<Double> right = new AddTask(mid, high);
                
                right.fork();
                left.fork();
                
                return left.join() + right.join();
            }
        }
    }
}
