/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.util.*;
import java.io.*;

public class MyList {

    Node head, tail;

    MyList() {
        head = tail = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = tail = null;
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void ftraverse(RandomAccessFile f) throws Exception {
        Node p = head;
        while (p != null) {
            fvisit(p, f); // You will use this statement to write information of the node p to the file
            p = p.next;
        }
        f.writeBytes("\r\n");
    }

    void loadData(int k) { //do not edit this function
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            addLast(a[i], b[i], c[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
/* 
   Khong su dung tieng Viet co dau de viet ghi chu.
   Neu dung khi chay truc tiep se bao loi va nhan 0 diem
     */
    void addLast(String xForest, int xRate, int xSound) {
        //You should write here appropriate statements to complete this function.
        if (xForest.charAt(0) == 'B') {
            return;
        }
        Boo boo = new Boo(xForest, xRate, xSound);
        Node node = new Node(boo);
        if (isEmpty()) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }

    //You do not need to edit this function. Your task is to complete the addLast function above only.
    void f1() throws Exception {
        clear();
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        f.close();
    }

//==================================================================
    void f2() throws Exception {
        clear();
        loadData(5);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        Boo x, y;
        x = new Boo("X", 1, 2);
        y = new Boo("Y", 3, 4);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
//        SP22-CSD2021
//      (C,9,8)(D,6,3)(E,8,5)(I,4,9)
// output: (C,9,8)(X,1,2)(Y,3,4)(D,6,3)(E,8,5)(I,4,9)
        Node nodeX = new Node(x, head.next);
        Node nodeY = new Node(y, nodeX);
        head.next = nodeY;
        if (head == tail) {
            tail = nodeX;
            tail.next = nodeY;
        } 
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

    void removeFirst() {
        if (isEmpty()) {
            return;
        }
        head = head.next;
        if (head == null) {
            tail = null;
        }
    }

    void remove(Node q) {
        if (isEmpty() || q == null) {
            return;
        }

        if (q == head) {
            removeFirst();
            return;
        }

        Node f = head;

        while (f != null && f.next != q) {
            f = f.next;
        }

        if (f == null) {
            return; // q is not in the list
        }
        Node q1 = q.next;
        f.next = q1;
        if (f.next == null) {
            tail = f;
        }
    }
//==================================================================

    void f3() throws Exception {
        clear();
        loadData(9);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
//        void f3() - Delete first, third
//(C,8,6)(D,3,5)(E,9,2)(F,5,8)(G,9,7)(H,6,8)(I,7,3)
//OUTPUT: (D,3,5)(F,5,8)(G,9,7)(H,6,8)(I,7,3)

//        Node FourthEle = head.next.next;
//        head = FourthEle;
//        if (FourthEle == null) {
//            tail = head = null;
//        } 
        remove(head);
        remove(head.next);
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

//==================================================================
//void f4() - Sort at least 4 elements
//    (C,7,11)(D,10,7)(E,6,5)(F,5,6)(I,4,4)(J,3,2)(K,2,1)
//    OUTPUT: (F,5,6)(E,6,5)(C,7,11)(D,10,7)(I,4,4)(J,3,2)(K,2,1)
    void f4() throws Exception {
        clear();
        loadData(13);
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        if (isEmpty()) {
            return;
        }
        Node i = head;
        Node j = null;
        Node stop = head.next.next.next.next;
        Boo tmp;
        while (i != stop) {
            j = i.next;
            while (j != stop) {
                if (j.info.rate < i.info.rate) {
                    tmp = i.info;
                    i.info = j.info;
                    j.info = tmp;
                }
                j = j.next;
            }
            i = i.next;
        }
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

}
