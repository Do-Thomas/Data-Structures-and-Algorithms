/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stk_que;

import java.util.Stack;

/**
 *
 * @author DELL
 */
public class Converter {
    public static String convert(int n, int base) {
        String result ="";
        Stack<Integer> stk = new Stack();
        do {
            stk.push(n%base);   // push remainders(so du) to stack
            n /= base;
        } while(n>0);
        while(!stk.empty()) {
            int value = stk.pop(); //pop values (lay gia tri) in stack
            result += Character.forDigit(value, base);  //add them to the result
        }
        return result;
    }
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int n = 258;
        System.out.println(convert(n,2)+"b");
        System.out.println(convert(n,8)+"q");
        System.out.println(convert(n,10)+"d");
        System.out.println(convert(n,16)+"h");
    }
    
}
