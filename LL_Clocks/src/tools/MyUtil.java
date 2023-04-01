/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class MyUtil {
    // Lấy đối tượng bàn phím
    public static Scanner sc = new Scanner(System.in);
    // Tiện ích nhập số nguyên trong khoảng [min,max]
    public static int readInt(String message, int min, int max) {
        int t;
        if(min>max) {
            t = min;
            min = max;
            max = t;
        }
        boolean OK;
        do {
            System.out.println(message + ": ");
            t = Integer.parseInt(sc.nextLine());
            OK = (t>=min && t<=max);
        }
        while (!OK);
        return t;
    }
    
    // Tiện ích nhập số nguyên t>=min
    public static int readInt(String message, int min) {
        return readInt(message, min, Integer.MAX_VALUE);
    }
    
    // Tiện ích nhập số thực trong khoảng [min, max]
    public static double readDouble(String message, double min, double max) {
        double t;
        if (min > max) {
            t = min;
            min = max;
            max = t;
        }
        boolean OK = false;
        do {
            System.out.println(message + ": ");
            t = Double.parseDouble(sc.nextLine());
            OK = (t>=min && t<=max);
        } while(!OK);
        return t;
    }
    
    // Tiện ích nhập số thực >= min
    public static double readDouble(String message, double min) {
        return readDouble(message, min, Double.MAX_VALUE);
    }
    
    // Tiện ích nhập chuỗi khác trống
    public static String readNOnBlankStr(String message) {
        String S;
        boolean OK = true;
        do {
            System.out.println(message+": ");
            S=sc.nextLine().trim();
            if(S==null || S.isEmpty())
                OK = false;
        }
        while (!OK);
        return S;
    }
    
    // Tiện ích nhập chuỗi theo một mẫu (sđt, email...)
    // sử dụng kỹ thuật regular expression- mẫu thông dụng
    public static String readPattern(String message, String regex) {
        String S;
        boolean OK;
        do {
            System.err.println(message+": ");
            S=sc.nextLine().trim();
            OK = S.matches(regex);
            if (!OK)
                System.err.println("Invalid input!");
        } while (!OK);
        return S;
    }
    
    // Tiện ích nhập boolean
    public static boolean readBool (String message) {
        System.out.println(message+ "[T/F, Y/N, 1/0: ");
        String S = sc.nextLine().trim().toUpperCase();
        char c = S.charAt(0);
        return (c=='T' || c=='Y' || c=='1');
    }
}
