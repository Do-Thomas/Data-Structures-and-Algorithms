/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll_dll_demo02;

/**
 *
 * @author DELL
 */
public class Flower {
    String name;
    String original = null;
    int price = 0;

    public Flower(String name) {
        this.name = name;
    }

    public Flower(String name, String original, int price) {
        this.name = name;
        this.original = original;
        this.price = price;
    }

   

    // 2 flowers are equals if they have the same name
    @Override
    public boolean equals(Object obj) {
        Flower f = (Flower)obj;
        return this.name.equals(f.name);
    }
    
    //Convert data of a flower to String
    @Override
    public String toString() {
        return name+", "+original+", "+price;
    }
    
}
