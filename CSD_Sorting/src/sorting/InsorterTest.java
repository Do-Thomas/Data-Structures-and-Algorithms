/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author DELL
 */
public class InsorterTest {
    public static final int SELECTION =1, INSERTION=2, BUBBLE=3;
    public static final int QUICK=4, MERGE = 5, HEAP=6, RADIX=7;
    // Create a array of randomized integers
    public static void genArray(int a[], int n) {
        Random ran = new Random(System.currentTimeMillis());
        for(int i=0; i<n; i++)
            a[i]=ran.nextInt(1000);
    }
    //checking whether an array is ascending or not
    public static boolean checkAsc(int a[], int n) {
        for(int i=1; i<n; i++)
            if(a[i]<a[i-1])
                return false;
        return true;
    }
    // Measuring time cost of a sort algorithm
    public static long measure(int a[], int n, int method){
        long t1= System.currentTimeMillis();
        long t2;
        switch(method) {
            case SELECTION: 
                IntSorter.selectionSort(a, n);
                break;
            case INSERTION: 
                IntSorter.insertionSort(a, n);
                break;
            case BUBBLE: 
                IntSorter.bubbleSort(a, n);
                break;
            case QUICK:
                IntSorter.quickSort(a, n);
                break;
            case MERGE:
                IntSorter.mergeSort(a, n);
                break;
            case HEAP:
                IntSorter.heapSort(a, n);
                break;
            case RADIX:
                IntSorter.radixSort(a, n);
                break;
        }
        t2=System.currentTimeMillis();
        return t2-t1;
    }
    public static void main(String[] args) {
        int n =100000;
        int ar[]=new int[n];
        genArray(ar,n);  //create an array of randomized integers
        System.out.println("Waitinng for sorting "+n+" elements.\n");
        long t;
        //Seclection
        int a[]=Arrays.copyOf(ar, n);  //preverse initial array
        t = measure(a,n,SELECTION);
        System.out.print("Selection sort,   Time cost:"+t+" milisec.");
        System.out.println(" Ascending order? "+checkAsc(a,n));
        //Insertion
        a=Arrays.copyOf(ar, n);  
        t = measure(a,n,INSERTION);
        System.out.print("Insertion sort,   Time cost:"+t+" milisec.");
        System.out.println(" Ascending order? "+checkAsc(a,n));
        // Bubble
        a=Arrays.copyOf(ar, n);  
        t = measure(a,n,BUBBLE);
        System.out.print("Bubble sort,   Time cost:"+t+" milisec.");
        System.out.println(" Ascending order? "+checkAsc(a,n));
        //Quick
        a=Arrays.copyOf(ar, n);  
        t = measure(a,n,QUICK);
        System.out.print("Quick sort,   Time cost:"+t+" milisec.");
        System.out.println(" Ascending order? "+checkAsc(a,n));
        //Merge
        a=Arrays.copyOf(ar, n);  
        t = measure(a,n,MERGE);
        System.out.print("Merge sort,    Time cost:"+t+" milisec.");
        System.out.println(" Ascending order? "+checkAsc(a,n));
        //Heap
        a=Arrays.copyOf(ar, n);  
        t = measure(a,n,HEAP);
        System.out.print("Heap sort,     Time cost:"+t+" milisec.");
        System.out.println(" Ascending order? "+checkAsc(a,n));
        //Radix
        a=Arrays.copyOf(ar, n);  
        t = measure(a,n,RADIX);
        System.out.print("Radix sort,      Time cost:"+t+" milisec.");
        System.out.println(" Ascending order? "+checkAsc(a,n));
    }
    
}
