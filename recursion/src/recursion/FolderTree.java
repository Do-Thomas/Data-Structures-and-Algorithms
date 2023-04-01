/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursion;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class FolderTree {
    // Compare 2 files. Folder has higher priority than normal file
    public static int compare(File f1, File f2) {
        int diff;
        if(f1.isDirectory() && f2.isFile())
            diff = -1;
        else if(f1.isFile() && f2.isDirectory())
            diff = 1;
        else {
            String f1Name = f1.getName().toUpperCase();
            String f2Name = f2.getName().toUpperCase();
            diff = f1Name.compareTo(f2Name);
        }
        return diff;
    }
    
    // Sort list of files- Using Bubble sort
    // Folders will be put to the beginning of the list
    public static void sort(File[] list) {
        int n = list.length;
        for(int i=0; i<n-1; i++) 
            for(int j=n-1; j>i; j--) {
                int d = compare(list[j], list[j-1]);
                if(d<0) {
                    File t = list[j];
                    list[j] = list[j-1];
                    list[j-1]=t;
                }
            }
    }
    
    // Print content of a folder
    public static void print(File f, int prefix) {
        if(prefix>0) {
            int steps = (prefix-3)/3;
            for(int i=1; i<=steps; i++)
                System.out.println("|  ");
            System.out.println("|__");
        }
        System.out.println(f.getName());  //print it's name
        // If it is a folder. recursive calls to it's children
        if(f.isDirectory()) {
            File[] children = f.listFiles();  // get children of this folder
            sort(children);  // folders are put in front of the list
            for(File child: children)
                print(child, prefix+3);
        }
    }
    
    public static void main(String[] args) {
        String path;
        int prefix=0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a path of a folder:");
        path = sc.nextLine();
        File f = new File(path);
        if(f.exists())
            print(f,prefix);
    }
}
