/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursion;



/**
 *
 * @author DELL
 */
public class RecurDemo {
    // Write recursive method here
    // Demo01: Computing n!
    public static double factorial(int n) {
        if(n<2)
            return 1;
        return n* factorial(n-1);
    }
    
    // Demo02: Test whether x is in the Fibonacci sequence?
    //Calculate the nth Fibonacci number
    public static int fibo(int n) {
        if(n<2)
            return 1;
        return fibo(n-2) + fibo(n-1);
    }
    // Testing whether x is in the Fibonacci sequence or not
    public static boolean testFibo(int x) {
        if(x<1)
            return false;
        int aFibo=1;
        int n=2;
        while(aFibo<x)   // run from aFibo= 1 to x
            aFibo = fibo(n++);
        return x==aFibo;
    }

    
    // Demo 3: Computing the nth item of an arithmetic progression
    // having the first item a and common difference d
    // 1.5   3.5   5.5   7.5   9.5   11.5
     public static double ap(int n, double a, double d) {
        if(n==1) 
            return a;
        return ap(n-1,a,d)+d;
    }
     
     // Demo 4: Compute the nth item of a geometric progression 
    // having the first item a and common multiplier q
    // 1.5  3   6   12   24   48
    public static double gp(int n, double a, double q) {
        if (n==1) 
            return a;
        return gp(n-1,a,q)*q;
    }
    
    // Demo 5: Calculate sum of integral array having n elements
    public static double sum(double[]a, int n) {
        if(n==0)
            return 0;
        else
            return sum(a,n-1) + a[n-1];
    }
    
    // Demo 6: Calculate the maximum value in an integral array having n elements
    public static double max(int[]a, int n) {
        if(n==1)             // if array has 1 element then
            return a[0];    // return max value = that element
        double m = max(a, n-1);   //10
        if(m>a[n-1])
            return m;
        return a[n-1];
    }
    
    // Demo 7: Calculate the minumum value in an integral array having n elements
    public static double min(int[]a, int n) {
         if(n==1)             // if array has 1 element then
            return a[0];    // return min value = that element
        double m = min(a, n-1);   //10
        if(m<a[n-1])
            return m;
        return a[n-1];
    }
            
    // Demo 8: Convert a decimal integer to a b- base numeric string
    public static String convert(int n, int base) {
        if(n==0)
            return "0";
        return convert(n/base, base) + Character.forDigit(n%base, base);
    }
    
    public static void main(String[] args) {
        // Test demo 3-  factorial
        System.out.println("\nDemo1- Computing n!");
        System.out.println("5! = "+ factorial(5));
        // test demo 4- Fibonacci:  1 1 2 3 5 8 13 21 34 55 89 144
        System.out.println("\nDemo2- Test whether x is in the Fibonacci sequence?");
        System.out.println(testFibo(55));
        System.out.println(testFibo(144));
        System.out.println(testFibo(120));
        System.out.println("\nDemo 3- Compute the n(th) item of arithmetic progression: ");
        System.out.println(ap(6, 1.5, 2));
        System.out.println("\nDemo 4- Compute the n(th) item of geometric progression: ");
        System.out.println(gp(6,1.5,2));
         // Test demo 5- sum of integral array: 
        double a[] = {1.5, 2, 4, 5, 2, 6.5};
        System.out.println("\nDemo5- Sum= "+sum(a,6));
        // Test demo 6- maximum value in an integral array
        int b[]= {1,5,9,7,2,19,10};
        System.out.println("\nDemo6- Maximum value= "+ max(b,7));
        // Test demo 7- minimum value in an integral array
        int c[] = {1,5,9,7,2,19,10};
        System.out.println("\nDemo7- Minimum value= "+ min(b,7));
        // Test demo 8- convert a decimal integer to b- base numeric string
        System.out.println("\nDemo8- Convert");
        System.out.println("Binary: "+ convert(266,2));
        System.out.println("Decimal: "+ convert(266,10));
        System.out.println("Octal: "+convert(266,8));
        System.out.println("Hexadecimal: "+convert(266,16));
    }
    }
    

