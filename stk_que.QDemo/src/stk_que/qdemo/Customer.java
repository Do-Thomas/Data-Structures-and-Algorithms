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
public class Customer extends Thread{
    // for randomize money number
    static Random rand = new Random(System.currentTimeMillis());
    long delay;  // milisec for delay after after purchase
    LinkedList<String> queque;  //customer know where the queque is
    Thread acc;  //acc must existed before a customer can buy products
    // Create a customer

    public Customer(String custName, long delay, Thread acc, LinkedList<String> queque) {
        super(custName);  //Customer is given a name
        this.delay = delay;
        this.acc = acc;
        this.queque = queque;
    }
    
    //Activity for  buying products
    @Override
    public void run() {
        while(acc.isAlive()) { //accountant is working
            try{
                //money amounr will be paid
                int price = 100+rand.nextInt(100);  //at least 100$
                // info. in an invoice
                String msg = this.getName() + ", "+price+"$";
                //Sending a request to the queque for paying
                queque.addLast(msg); //wait for invoice
                // If acc stopped working, customer stops buying
                if(!acc.isAlive())
                    this.yield(); // yield CPU
                // else customer delays a duration before next request
                else
                    this.sleep(delay);
            } catch(Exception e) {
                System.out.println(e);
            }
        }
    }
}
