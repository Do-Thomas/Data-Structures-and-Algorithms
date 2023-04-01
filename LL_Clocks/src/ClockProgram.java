
import data.ClockList;
import tools.Menu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author DELL
 */
public class ClockProgram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Tạo menu
        Menu mnu = new Menu();
        mnu.add("Add new clock");
        mnu.add("Search a clock");
        mnu.add("Remove a clock");
        mnu.add("Update a clock");
        mnu.add("Print all clocks");
        mnu.add("View clocks in a range of prices");
        // Tạo mới ds đòng hồ
        ClockList list = new ClockList();
        // biến quản lí tác vụ
        int choice;
        do {
            choice = mnu.getChoice();
            switch(choice) {
                case 1: list.addClock(); break;
                case 2: list.searchClock(); break;
                case 3: list.remove();  break;
                case 4: list.updateClock(); break;
                case 5: list.printAll(); break;
                case 6: list.printPriceRange(); break;
                default: System.out.println("Bye!");
            }
        } while (choice >0 && choice <= mnu.size());
    }
    
}
