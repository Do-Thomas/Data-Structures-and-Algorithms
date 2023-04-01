/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stk_que.qdemo;

import java.util.LinkedList;

/**
 *
 * @author DELL
 */
public class Accountant implements Runnable{
    LinkedList<String> queque;  //queque of paying requests
    int duty;  // number invoices per working session
    int count = 0;  // number of processed invoices
    
    //Create an accountant with a duty and pre-defined queque
    
    public Accountant(int duty, LinkedList<String> queque) { 
        this.duty = duty;
        this.queque = queque;
    }

    @Override
    public void run() {
        while(count<duty) {
            try{
                if(!queque.isEmpty()) { //there are still invoices
                    count++;  //one more invoice must be processed
                    // customer info + money
                    String invoiceInfo = "Invoice "+count+"/"+duty+": "+queque.removeFirst(); 
                    System.out.println(invoiceInfo);  //printing 1 invoice
                }
                // The duty completed, accountant stops working
                // current thread is the thread which is using this method
                if(count==duty)
                    Thread.currentThread().yield();   //yeild: nhường lại luồng hiện tại
                else
                    Thread.currentThread().sleep(500);  //pause 0.5 sec
            } catch(Exception e) {
                System.out.println(e);
            }
        }
    }
    
}
