package Q2;

public class Q2_Case1 {
    //Đề thi thử Fall2022
    //void f1() 
    //(B,9,4)(C,4,3)(D,8,6)(E,2,5)(F,6,7)
    //OUTPUT: (C,4,3)(B,9,4)(E,2,5)(D,8,6)(F,6,7)
    void insert(String xForest, int xRate, int xSound) {
        //You should insert here statements to complete this function
        if (xForest.charAt(0) == 'A') {
            return;
        }
        Boo boo = new Boo(xForest, xRate, xSound);
        if (isEmpty()) {
            root = new Node(boo);
            return;
        } else {
            Node f = null;
            Node p = root;
            while (p != null) {
                if (p.info.sound == xSound) {
                    return;
                }
                f = p;
                if (xSound < p.info.sound) {
                    p = p.left;
                } else {
                    p = p.right;
                }
            }

            if (xSound < f.info.sound) {
                f.left = new Node(boo);
            } else {
                f.right = new Node(boo);
            }

        }

    }
}
