/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author DELL
 */
public class Clock implements Comparable<Clock> {
String ID; // mẫu Cxxx, x là số, ID là duy nhất không cập nhật
int guarantee = 0;  // số tháng bảo hành: 0..6
double price = 0;   // giá >=0

//Constructor 3 tham số để tạo 1 Clock

    public Clock(String ID, int guarantee, double price) {
        this.ID = ID;
        this.guarantee = guarantee;
        this.price = price;
    }
    
    // Cac lớp quản lý nhóm của Java dùng tham số OBject trong vụ
    // tìm kiếm và gọi hành vi equals để kiểm tra bằng nhau
    // Constructor 1 tham số ID phục vụ khả năng tìm kiếm

    public Clock(String ID) {
        this.ID = ID;
    }
     // Kiểm tra băng nhau phục vụ chức năng tìm kiếm
    @Override
    public boolean equals(Object obj) {
        Clock clk = (Clock)obj;
        return this.ID.equals(clk.ID);
    }
    
    // hành vi so sánh phục vụ chức năng sắp xếp theo giá + ID
    @Override
    public int compareTo(Clock o) {
        double d = this.price - o.price;
        return d>0? 1: d<0? -1 : ID.compareTo(o.ID);
    }
    
    // Lấy data dạng chuỗi
    @Override
    public String toString() {
        return ID + ", "+guarantee+", "+price;
    }
    
    // các hành vi set nên có điều kiện kiểm tra trị tham số
    public void setGuarantee(int guarantee) {
        if(guarantee>-0)
            this.guarantee = guarantee;
    }
    public void setPrice(double price) {
        if(price>=0)
            this.price = price;
    }
}
