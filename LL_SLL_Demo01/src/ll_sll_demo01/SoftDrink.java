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
public class SoftDrink {
    String productLine;  
    String company = null;
    int volume=0;
    int price=0;
    
    //Constructors

    public SoftDrink(String productLine) {
        this.productLine = productLine;
    }
    public SoftDrink(String pLine, String company, int volume, int price) {
        this.productLine = pLine;
        this.company = company;
        this.volume = volume;
        this.price = price;
    }
    
    //Tool for search operation-Based on their productLine
    @Override
    public boolean equals(Object obj) {
        SoftDrink aSD = (SoftDrink)obj;
        return this.productLine.equals(aSD.productLine);
    }
    
    //Tool for ascending sort by soft drink's price then by product line
    public int compareTo(SoftDrink o) {
        int priceDif = this.price - o.price;
        if(priceDif < 0) 
            return -1;
        if(priceDif > 0)
            return 1;
        return this.productLine.compareTo(o.productLine);
    }
    
    //Convert data of the soft drink to String
    @Override
    public String toString() {
        return  productLine + ", " + company + ", " + volume + ", " + price ;
    }    
}
