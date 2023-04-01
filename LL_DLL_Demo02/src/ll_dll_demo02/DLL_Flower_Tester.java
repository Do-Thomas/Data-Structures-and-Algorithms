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
public class DLL_Flower_Tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DLL_FlowerList list = new DLL_FlowerList();
        // Adds
        list.addFirst("Hung. Rose", "Hungary", 20);
        list.addFirst("Dalat Rose", "VietNam", 2);
        list.addLast("White Tulip", "Hungary", 10);
        list.addLast("Orchid", "French", 30);
        list.addLast("Gladious", "VietNam", 1);
        
        // Traversals
        System.out.println("\nFlowers in the list:");
        list.printALl();
        System.out.println("\nFlowers in the list, Reverse order:");
        list.printAll_reverse();
        System.out.println("\nFlower from Hungary:");
        list.printAll_Org("Hungary");
        System.out.println();
        
        // Search operation
        String name = "Tigon";
        DLL_Node result = list.search(name);
        if(result==null)
            System.out.println("Search "+name+": Not found!");
        else
            System.out.println("Search: "+result.flower);
        name="Orchid";
        result = list.search(name);
        if(result==null)
            System.out.println("Search "+name+": Not found!");
        else
            System.out.println("Search: "+result.flower);
        System.out.println();
        
        // Remove operations
        System.out.println("\nRemove first operation");
        list.removeFirst();
        System.out.println("\nFlowers in the list:");
        list.printALl();
        System.out.println("\nRemove last operation");
        list.removeLast();
        System.out.println("\nFlowers in the list:");
        list.printALl();
        System.out.println("\nSearch and remove:");
        name="Tigon";
        Flower f = list.remove(name);
        if(f==null)
            System.out.println("Remove failed!"+name);
        else
            System.out.println("Remove successfully" + f);
        
        //Print all elements
        System.out.println("\nFlowers in the list:");
        list.printALl();
    }
    
}
