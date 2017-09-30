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
public class GenericBox<E> {
    private E val;
    
    public static void main(String[] args){
        GenericBox<Integer> box1 = new GenericBox<>();
        GenericBox<Double> box2 = new GenericBox<>();
        GenericBox<String> box3 = new GenericBox<>();
        
        box1.fill(1);
        box2.fill(1.1);
        box3.fill("Some String");
        System.out.println(box1.look());
        System.out.println(box2.look());
        System.out.println(box3.look());
        System.out.println(box1);
        System.out.println(box2);
        System.out.println(box3);
    }
    
    public void fill(E newVal){
        val = newVal;
    }
    
    public E look(){
        return val;
    }
    
    public void empty(){
        val = null;
    }
    
    @Override
    public String toString(){
        return "Box: " + val.toString();
    }
}
