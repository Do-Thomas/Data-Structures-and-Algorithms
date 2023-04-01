/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balancestree_dsw_demo;

/**
 *
 * @author DELL
 */
public class DSW_Tester {
    public static Integer[] createIntArr(int max) {
        Integer[] a = new Integer[max];
        for(int i=0; i<max; i++) 
            a[i] = max-i;
        return a;
    }
    public static void main(String[] args) {
        BST tree = new BST();
        int max = 14; // You can change this number for testing more
        Integer a[] = createIntArr(max);
        // Create a BST of integers which is full left degraded
        tree.add_Array(a);
        System.out.println("\nInitial tree:\n");
        tree.printAligned();
        tree.DSW_Balance();
        System.out.println("\nBalanced tree:\n");
        tree.printAligned();
    }
    
}
