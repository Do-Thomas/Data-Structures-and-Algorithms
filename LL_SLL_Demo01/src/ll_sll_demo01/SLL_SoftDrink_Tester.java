
package ll_sll_demo01;

public class SLL_SoftDrink_Tester {

 
    public static void main(String[] args) throws Exception {
        String srcFname = "Source.txt"; //data source file
        String binFname = "results_bin.txt";  //Result binary file
        String txtFname = "results_text.txt";  //Result text file
        SLL_SoftDrink list = new SLL_SoftDrink();
        // Load data from data source file to the list
        list.loadFromFile(srcFname);
        //Traversals
        System.out.println("Soft drinks in the list: ");
        list.printAll();
        System.out.println();
        System.out.println("Soft drinks of Coca: ");
        list.printCompanyBased("Coca");
        System.out.println();
        //Reverse the list
        list.reverse();
        System.out.println("Reverse the list: ");
        list.printAll();
        System.out.println();
        
        //Sorting
        list.ascSort_Price_then_pLine();
        System.out.println("Ascending sort by price then pLine:");
        list.printAll();
        System.out.println();
        //Search operation
        String type = "Beer 33";
        SoftDrink result = list.search(type);
        if(result == null)
            System.out.println("Search "+type+": Not found!");
        else
            System.out.println("Search: "+result);
        type = "Miranda";
        result = list.search(type);
        if(result == null)
            System.out.println("Search "+type+": Not found!");
        else
            System.out.println("Search: "+result);
        System.out.println();
        //Removing operation
        type = "Natural";
        result = list.remove(type);
        if(result == null) 
            System.out.println("Remove failed! "+type);
        else
            System.out.println("Remove successfully: "+result);
        type = "Natural orange1";
        result = list.remove(type);
        if(result == null)
            System.out.println("Remove failed! "+type);
        else
            System.out.println("Remove successfully: "+result);
        //Print all elements after remove operation
        System.out.println("\nAfter removing: ");
        list.printAll();
        // Write list to file
        list.printAll_binF(binFname);
        list.printAll_txtF(txtFname);
    }
}

    