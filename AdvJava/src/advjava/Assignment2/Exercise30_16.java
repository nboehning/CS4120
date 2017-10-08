package advjava.Assignment2;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

/**
 * @author Nathan Boehning
 * Date: 10/4/2017
 * Purpose: Performing arithmetic operations on matrices for both sequential and parallel
 *          methods
 */
public class Exercise30_16 {
    private static int M_LEN = 2000;
    public static void main(String[] args) {
        double[][] matrix1 = new double[M_LEN][M_LEN];
        double[][] matrix2 = new double[M_LEN][M_LEN];
        
        for(int i = 0; i < M_LEN; i++) {
            for(int j = 0; j < M_LEN; j++) {
                matrix1[i][j] = Math.random() * 10000;
                matrix2[i][j] = Math.random() * 10000;
            }
        }
        
        long startTime = System.currentTimeMillis();
        System.out.println("\nThe Parallel sum is: " + parallelAddMatrix(matrix1, matrix2));
        long endTime = System.currentTimeMillis();
        System.out.println("The number of processors is "+ Runtime.getRuntime()
                .availableProcessors() + " completed in " + (endTime - startTime) 
                + " milliseconds"); 
        
                startTime = System.currentTimeMillis();
        System.out.print("The Sequential sum is " + sequentialAddMatrix(matrix1, matrix2));
        endTime = System.currentTimeMillis();
        System.out.println(" completed in " + (endTime - startTime)
                + " milliseconds"); 
    }
    
    public static double sequentialAddMatrix(double[][] a, double[][] b) {
        double sum = 0.0;
        int count = 0;
        for(int i = 0; i < M_LEN; i++)
            for(int j = 0; j < M_LEN; j++) {
                sum += a[i][j] + b[i][j];
                count++;
            }
        
        System.out.println("Sequential Numbers added: " + count);
        return sum;
    }
    
    public static double parallelAddMatrix(double[][] a, double[][] b) {
        RecursiveTask<Double> task = new MatrixAddTask(a, b, 0, M_LEN);
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(task);
    }
    
    private static class MatrixAddTask extends RecursiveTask<Double> {
        private double[][] oprand1;
        private double[][] oprand2;
        private final int THRESHOLD = 5000;
        private int count;
        private int low;
        private int high;
        
        MatrixAddTask(double[][] m1, double[][] m2, int low, int high) {
            this.oprand1 = m1;
            this.oprand2 = m2;
            this.low = low;
            this.high = high;
        }
                
        @Override
        public Double compute() {
            if(high-low < THRESHOLD) {
                double sum = 0.0;
                for(int i = low; i < high; i++) {
                    for(int j = 0; j < M_LEN; j++) {
                        count++;
                        sum += oprand1[i][j] + oprand2[i][j];
                    }
                }
                
                System.out.println("Number visited parallel: " + count);
                return sum;               
            }
            else {
                int mid = (low + high) / 2;
                RecursiveTask<Double> left = new MatrixAddTask(oprand1, oprand2, low, mid);
                RecursiveTask<Double> right = new MatrixAddTask(oprand1, oprand2, mid, high);
                
                right.fork();
                left.fork();
                
                return left.join() + right.join();
            }
        }
    }
} // End of class Exercise30_16
