package advjava.Assignment2;

import java.util.concurrent.locks.*;

/**
 * @author Nathan Boehning
 * Date: 10/3/2017
 * Purpose: Creating a deadlock program
 */
public class Exercise30_11 {
    
    private static BankAccount acct = new BankAccount();
    
    public static void main(String[] args) {
        Task task1 = new Task();
        
        Thread thread1 = new Thread(task1);
        
        thread1.start();
    }
    
    public static class Task implements Runnable {
    
        @Override
        public void run() {
            System.out.println("Depositing first");
            acct.deposit(100);
            
            System.out.println("Withdrawing second");
            acct.withdraw(100);
        }
    } 
    
    public static class BankAccount {
        private static Lock lock = new ReentrantLock();
        public double balance;
        
        public double getBalance() {
            return balance;
        }
        
        public void deposit(double amount) {
            lock.lock();
            
            try {
                double newBalance = balance + amount;
                
                System.out.println("Depositing, waiting to withdraw");
                Thread.sleep(50);
                
                acct.withdraw(100);
                
                balance = newBalance;
            }
            catch(InterruptedException ex) {
                
            }
            finally {
                lock.unlock();
            }
        }
        
        public void withdraw(double amount) {
            lock.lock();
            
            try {
                double newBalance = balance - amount == 0 ? balance-amount : 0;
                
                System.out.println("Withdrawing, waiting to deposit");
                Thread.sleep(100);
                
                acct.deposit(100);
                
                balance = newBalance;
            }
            catch(InterruptedException ex) {
                
            }
            finally {
                lock.unlock();
            }
        }
    }
} // End of class Exercise30_11
