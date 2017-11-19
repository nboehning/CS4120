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
public class StringCount {
    public static void main(String[] args)
    {
        Integer i = 16;
        Double d = 3.124;
        String s = "Hi";
        
        System.out.println(i);
        System.out.println(d);
        System.out.println(s);
        System.out.println(toStringCount(i));
        System.out.println(toStringCount(d));
        System.out.println(toStringCount(s));
    }
    
    public static <E> int toStringCount(E obj)
    {
        return obj.toString().length();
    }
}
