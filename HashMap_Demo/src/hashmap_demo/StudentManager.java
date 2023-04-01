/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashmap_demo;

/**
 *
 * @author DELL
 */
public class StudentManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final String filename ="students.txt";
        Menu mnu = new Menu(); //setup menu
        mnu.add("Add new student"); mnu.add("Search student");
        mnu.add("Remove a student"); mnu.add("Update a student");
        mnu.add("Print the list");  mnu.add("Save the list to file");
        mnu.add("Quit");
        int choice = 0;
        //setup initial studnet list
        StudentList L = new StudentList();
        L.loadFromFile(filename); //load initila data from file;
        do {
            System.out.println("\nSTUDENT MANAGER");
            choice = mnu.getUserChoice();
            switch(choice) {
                case 1: L.addStudent(); break;
                case 2: L.searchStudent(); break;
                case 3: L.removeStudent(); break;
                case 4: L.updateStudent(); break;
                case 5: L.print(); break;
                case 6: L.saveToFile(filename); break; 
            }
        }while(choice>=0 && choice<7);
    }
    
}
