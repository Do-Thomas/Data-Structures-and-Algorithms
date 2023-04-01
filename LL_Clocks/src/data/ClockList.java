/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.LinkedList;
import java.util.Scanner;
import tools.MyUtil;

/**
 *
 * @author DELL
 */
public class ClockList extends LinkedList<Clock> {
    static Scanner sc = MyUtil.sc;
    //Constructor: tạo ds trống
    public ClockList() {
    }
    
    // Thêm đồng hồ
    public void addClock() {
        // Nhập thông tin về 1 dồng hồ: ID, guarantee, price
        int pos;  // kiểm tra trùng ID
        String ID;
        do {
            // Nhập theo mẫu C000
            ID = MyUtil.readPattern("Enter ID Cxxx", "C[\\d]{3}");
            
            // Kiểm tra trùng ID. Hành vi indexOf(Object) của lớp
            // LinkedList trả về chỉ số tương tự như ArrayList
            pos=this.indexOf(new Clock(ID));
            if(pos>=0)
                System.out.println("ID Dupplicated!");
        } while (pos>=0);
        //Đồng hồ được bảo hành tối đa 6 tháng
        int guarantee = MyUtil.readInt("Guarantee", 0, 6);
        // Đồng hố có giá >0
        double price = MyUtil.readDouble("Clock's price", 0);
        // Tạo mới 1 đồng hồ
        Clock clk = new Clock(ID, guarantee, price);
        // Thêm vào cuối danh sách
        this.addLast(clk);
        System.out.println("A new clock is added");
    }
    // Tìm đòng hồ theo ID
    public void searchClock() {
        String ID = MyUtil.readPattern("Enter ID Cxxx", "C[\\d]{3}");
        int pos = this.indexOf(new Clock(ID));
        if(pos<0)
            System.out.println("Not found!");
        else
            System.out.println("Found: "+this.get(pos));
    }
    // Xóa đồng hồ theo ID
    public void removeClock() {
        // Nhập ID
        String ID = MyUtil.readPattern("Enetr ID Cxxx", "C[\\d]{3}");
        int pos = this.indexOf(new Clock(ID));
        if(pos<0)
            System.out.println("Not found!");
        else {
            boolean res = MyUtil.readBool("Remove the clock. Really? ");
            if (res==true) {
                this.remove(pos);
                System.out.println("Removed.");
            }
        }
    }
    
    // Cập nhật đồng hồ theo ID
    public void updateClock() {
        String ID = MyUtil.readPattern("Enter ID Cxxx", "C[\\d]{3}");
        int pos = this.indexOf(new Clock(ID));
        if(pos<0)
            System.out.println("Not found!");
        else {
            Clock clk = this.get(pos);
            int oldGuarantee = clk.guarantee;
            double oldPrice = clk.price;
            // Nhập chuooic trống: KHÔNG CẬP NHẬT
            String input;
            System.out.println("Update guarantee:");
            System.out.println("Old guarantee:"+oldGuarantee);
            System.out.println("New guarante[Enter for bypassing]: ");
            input = sc.nextLine().trim();
            if(input.length()>0) {
                clk.setGuarantee(Integer.parseInt(input));
            }
            System.out.println("Old price:"+ oldPrice);
            System.out.println("New price[Enter for bypassing]: ");
            input = sc.nextLine().trim();
            if(input.length()>0) {
                clk.setPrice(Double.parseDouble(input));
            }
            System.out.println("Updated.");
        }    
    }
    
    // Duyệt xuất toàn bộ đồng hồ
    public void printAll() {
        if(this.isEmpty())
            System.out.println("Empty list!");
        else {
            System.out.println("Clock list:");
            // duyệt xuất tất cả
            for (Clock clk: this)
                System.out.println(clk);
            System.out.println(this.size()+" clock(s).");
        }
    }
    
    // Duyệt xuất đồng bộ trong 1 khoảng giá
    public void printPriceRange() {
        if(this.isEmpty()) {
            System.out.println("Empty list!");
            return;
        }
        //Nhập khoảng giá
        double p1, p2;
        p1= MyUtil.readDouble("Low price: ", 0);
        p2= MyUtil.readDouble("High price: ", 0);
        if(p1>p2) {
            double t =p1;
            p1=p2;
            p2=t;
        }
        //duyệt xuất cod điều kiện
        for(Clock clk: this) {
            double price = clk.price;
            if(price>=1 && price <=p2)
                System.out.println(clk);
        }
    }
}
