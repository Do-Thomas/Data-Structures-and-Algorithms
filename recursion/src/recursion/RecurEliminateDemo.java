/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursion;

import java.util.Stack;

/**
 * Recursion Eliminate Demonstrate. Using loop and stack
 * Problem: Convert int n to base- system digit string
 * @author DELL
 */
public class RecurEliminateDemo {
    // Recursive version
    public static String rec_convert(int n, int base) {
        if(n==0)
            return "0";
        return rec_convert(n/base, base) + Character.forDigit(n%base, base);
    }
    
    // Loop version: Using a string as a stack
    public static String convert2(int n, int base) {
        String result = "";
        do {   // add n%base to the beginning of the result string
            result = Character.forDigit(n%base, base) + result;
            n /= base;
        } while(n>0);
        return result;
    }
    
    public static String convert3(int n, int base) {
        Stack<Character> stk = new Stack();
        do { // put n%base to stack
            stk.push(Character.forDigit(n%base, base));
            n /= base;
        } while(n>0);
        // pop characters from stack to the result string
        String result ="";
        while(!stk.empty())
            result += stk.pop();
        return result;
    }
    
    // Test program
    public static void main(String[] args) {
        System.out.println("10- base: "+ rec_convert(122,10));
        System.out.println("2- base: "+ rec_convert(122,2));
        System.out.println("8- base: "+ rec_convert(122,8));
        System.out.println("16- base: "+ rec_convert(122,16));
        String S1 = rec_convert(122,16);
        String S2 = convert2(122,16);
        String S3 = convert3(122,16);
        System.out.println(S1+", "+S2+", "+S3);
    }
}
