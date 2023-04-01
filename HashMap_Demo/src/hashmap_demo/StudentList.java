/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashmap_demo;

import java.io.BufferedReader;  // for reading lines in a text file
import java.io.FileInputStream;  //for redaing binary file
import java.io.FileOutputStream;  //for writing binary file
import java.io.InputStreamReader;  //For reading UTF-8 char in a file
import java.io.OutputStreamWriter; //For writing UTF-8 char to a file
import java.io.PrintWriter;  //for writing lines in a text file
import java.util.ArrayList;  //for getting studnet list then sort the list
import java.util.Collections;  //for sorting based on codes
import java.util.Hashtable;
import java.util.Iterator;   //for traversing the list
import java.util.Scanner;  //for inputting data

/**
 *
 * @author DELL
 */
public class StudentList extends Hashtable<String, Student>{
    Scanner sc = new Scanner(System.in);

    public StudentList() {
        super();
    }
    
    // Load initial students from unicode text file
    public boolean loadFromFile(String filename) {
        try{   // using intermediate class between text and binary streams
            FileInputStream fi = new FileInputStream(filename);
            InputStreamReader isr = new InputStreamReader(fi,"UTF8");
            BufferedReader bf = new BufferedReader(isr); //read unicode line
            String line;  //SE12345, Hoàng Anh Tuấn, 5
            while((line=bf.readLine())!=null) {
                line = line.trim();
                String[] ar = line.split("[,]");
                if(ar.length==3) {
                    Student st = new Student(ar[0], ar[1], Integer.parseInt(ar[2]));
                    this.put(st.code, st); // Key = studnet code
                }
            }
            bf.close();
            isr.close();
            fi.close();
            
        } catch(Exception e) {
            e.printStackTrace();  // printing called methods in stack
        }
        return false;
    }
    
    // This method will save Unicode text to file
    public boolean saveToFile(String filename) {
        try{ //using intermediate class between text and binary stream
            FileOutputStream fo = new FileOutputStream(filename);
            OutputStreamWriter osw = new OutputStreamWriter(fo,"UTF8");
            PrintWriter pw = new PrintWriter(osw);  //write unicode line
            //get keyset
            Iterator<String> it = this.keySet().iterator();
            while(it.hasNext()) {
                String key = it.next();
                Student st = this.get(key);
                pw.println(st);  //write a student to file
            }
            pw.close();
            osw.close();
            fo.close();
            return true;
        } catch(Exception e) {
            e.printStackTrace(); //printing called methods in stack
        }
        return false;
    }
    
    public Student search(String code) {
        return this.get(code);
    }
    public void addStudent() {
        String code, name;
        int mark;
        System.out.println("Enter data of new student:");
        boolean cont = true;
        do {
            System.out.println("Code:");
            code = sc.nextLine().toUpperCase().trim();
            cont = search(code)!=null;
            if(cont)
                System.out.println("Code is dupplicated!");
        } while(cont);
        System.out.println("Name:");
        name=sc.nextLine().toUpperCase().trim();
        System.out.println("Mark:");
        mark= Integer.parseInt(sc.nextLine());
        this.put(code, new Student(code, name, mark));
        System.out.println("New student "+code+" has been added!");
    }
    public void searchStudent() {
        String code;
        if(this.isEmpty()) {
            System.out.println("Empty list!");
            return;
        }
        System.out.println("Enter student code for searching:");
        code=sc.nextLine().toUpperCase();
        Student st = this.search(code);
        if(st==null)
            System.out.println("This code does not exist!");
        else
            System.out.println(st);
    }
    public void removeStudent() {
        String code;
        if(this.isEmpty()) {
            System.out.println("Empty list!");
            return;
        }
        System.out.println("Enter student code for removing:");
        code=sc.nextLine().toUpperCase();
        Student st = this.search(code);
        if(st==null)
            System.out.println("This code does not exist!");
        else {
            remove(code);
            System.out.println("The student "+code+" has been removed!");
        }
    }
    public void updateStudent() {
        String code;
        if(this.isEmpty()) {
            System.out.println("Empty list!");
            return;
        }
        System.out.println("Enter student code for removing:");
        code=sc.nextLine().toUpperCase();
        Student st = this.search(code);
        if(st==null)
            System.out.println("This code does not exist!");
        else 
            st.update();
    }
    public void print() {
        String code;
        if(this.isEmpty()) {
            System.out.println("Empty list!");
            return;
        }
        ArrayList<Student> list = new ArrayList();
        list.addAll(this.values());
        Collections.sort(list);
        for(Student st : list)
            System.out.println(st);
    }   
}
