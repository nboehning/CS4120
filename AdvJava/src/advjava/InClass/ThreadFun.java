/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advjava.InClass;

/**
 *
 * @author Nathan Boehning
 */
public class ThreadFun {
    public static void main(String[] args){
        Task task1 = new Task("Nathan", 100);
        Task task2 = new Task("Says", 100);
        Task task3 = new Task("Hello", 100);
        Task task4 = new Task("Hello1", 100);
        Task task5 = new Task("Hello2", 100);
        Task task6 = new Task("Hello3", 100);
        Task task7 = new Task("Hello4", 100);
        Task task8 = new Task("Hello5", 100);
        
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        Thread thread3 = new Thread(task3);
        Thread thread4 = new Thread(task4);
        Thread thread5 = new Thread(task5);
        Thread thread6 = new Thread(task6);
        Thread thread7 = new Thread(task7);
        Thread thread8 = new Thread(task8);

        thread1.setPriority(0);
        thread2.setPriority(5);
        thread3.setPriority(10);
        
        
        thread1.start();
        thread2.start();
        thread3.start();
        System.out.println("MAIN THREAD ENDED");
    }
}

class Task implements Runnable{
    private String word;
    private int number;
      
    public Task(String word, int number){
        this.word = word;
        this.number = number;
    }
        
    @Override
    public void run(){
        for(int i = 0; i < number; i++){
            System.out.print(word + "\n");
        }
            
        System.out.println();
        System.out.println("THREAD HAS FINISHED");
    }
}
