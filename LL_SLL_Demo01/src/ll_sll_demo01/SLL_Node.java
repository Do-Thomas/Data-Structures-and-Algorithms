/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll_sll_demo01;

/**
 *
 * @author DELL
 */
public class SLL_Node {
    SoftDrink data;  //a soft drink
    SLL_Node next;   //reference to the next node in a single linked list
    public SLL_Node(SoftDrink aSD) {
        this.data = aSD;
        next = null;
    }
    
    public SLL_Node(String productLine, String company, int volume, int price) {
        SoftDrink aSD = new SoftDrink(productLine, company, volume, price);
        this.data = aSD;
        next = null;
    }
}
