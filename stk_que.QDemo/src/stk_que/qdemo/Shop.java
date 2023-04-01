/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stk_que.qdemo;

import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author DELL
 */
public class Shop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LinkedList<String> queque = new LinkedList();  //setup a queque
        int duty = 30;  //duty of an accountant
        Accountant acc = new Accountant(duty, queque); //acc is not a thread
        Thread accThread = new Thread(acc);  // create a thread from acc
        accThread.start();  // starting the thread from acc
        int N = 10;  // The shop opens, 10 customers enter the shop
        Customer[] custList = new Customer[N];  //10 threads
        String custName;
        Random rand = Customer.rand;
        int delay;
        for(int i=0; i<N; i++) { //create a thread for each customer
            custName = "cust "+(i+1);  // customer's name
            delay = 200 + rand.nextInt(500); //customer's delay
            custList[i] = new Customer(custName, delay, accThread, queque);
        }
        // Customers purchase products. 10 threads
        for(int i=0; i<N; i++)
            custList[i].start();
    }
}
