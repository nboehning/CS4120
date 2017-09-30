/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advjava.InClass;

/**
 *
 * @author Nathan Boehning
 * Date: 9/30/2017
 * Purpose: Experiment with multi-threading and thread pools
 */
public class AddingThreads {
    public static void main(String[] args) {
        for(int i = 0; i < 1000; i++) {
            int n = (int)(Math.random() * 10000000) + 1;
            SumTask task = new SumTask(i, n);
            Thread thread = new Thread(task);
            
            thread.start();
        }
    }
}

class SumTask implements Runnable {
    private long sum = 0;
    private int id;
    private int n;
    
    public SumTask(int id, int n) {
        this.id = id;
        this.n = n;
    }
    
    @Override
    public void run() {
        for(int i = 1; i <= n; i++)
            sum += i;
        
        System.out.println("Thread " + id + 
                " added up from 0 to " + n + " and got: " + sum);
    }
}
