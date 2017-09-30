/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advjava.InClass;

import java.util.concurrent.*;

/**
 *
 * @author nboeh
 */
public class AddingThreadsWithPool {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(4);
        
        for(int i = 0; i < 1000; i++) {
            int n = (int)(Math.random() * 10000000) + 1;
            SumTask task = new SumTask(i, n);
            
            threadPool.execute(task);
        }
    }
}