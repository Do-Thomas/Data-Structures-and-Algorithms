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
public class PrefixEvaluator {
    
    // Check if it is operator(phep tinh) or not
    public static boolean isOperator(String S) {
        return (S.equals("+") || S.equals("-") || 
                S.equals("*") || S.equals("/"));
    }
    
    // Evaluate a simple expression + - * /
    public static double evaluate(String op, double n1, double n2) {
        if(op.equals("+"))
            return n1 + n2;
        if(op.equals("-"))
            return n1 - n2;
        if(op.equals("*"))
            return n1 * n2;
        if(op.equals("/")) {
            if(n2==0) throw new RuntimeException("Divide by 0!");
            return n1/n2;
        }
    throw new RuntimeException("Operation is not supported!");
    }
    
    public static double evaluate(String exp) {
        double result=0;
        // Split expression to sub-strings
        Stack<Double> stack = new Stack();
        String op[] = exp.split(" ");
        double n1 , n2 ;
        
            //Checking S with operators
            for(int i=op.length - 1; i>=0; i--) {
                 String expression = op[i];
            if(!isOperator(expression)){
                stack.push(Double.parseDouble(expression));
            }
            else{  // part is an operator
                   // pop 2 values from stack
                   n1 = stack.pop();
                   n2 = stack.pop();
                   // evaluate sub- expression: n1 part n2
                   result = evaluate(expression,n1, n2);
                   stack.push(result);  //push temporary result to stack
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        String exp = "* 3 / + 6 4 5" ;
        System.out.println(evaluate(exp));
    }
}

