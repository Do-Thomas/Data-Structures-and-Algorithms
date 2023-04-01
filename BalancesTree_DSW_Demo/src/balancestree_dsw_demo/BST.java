/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balancestree_dsw_demo;


public class BST {
    BST_Node root;
    int noOfNodes = 0;   //number of nodes in the tree

    public BST() {
        root = null;
    }
    
    // Add methods - Recursive implemetation
    public BST_Node add_recur(BST_Node curref, Comparable data) {
        if(curref == null) {
            curref = new BST_Node(data);
            noOfNodes++;
            // nếu nút cần thêm vào nhỏ hơn thì thêm vào trái, ngược lại thêm vào phải
        } else if (data.compareTo(curref.data)<0)
            curref.left = add_recur(curref.left, data);
        else curref.right = add_recur(curref.right, data);
        return curref;
    }
    public void add_recur(Comparable data) {
        root = add_recur(root, data);
    }
    public void add_recur(Comparable... group) {
        for(Comparable data: group)
            add_recur(data);
    }
    public void add_Array(Comparable[] group) {
        for(Comparable data: group)
            add_recur(data);
    }
    
    // NLR Depth First Traversal to print the tree in aligned format
    private void printAligned(BST_Node p, int nSpace) {
        if(p!=null) {
            for(int i = 0; i<nSpace; i++) System.out.print(" ");
            System.out.println(p.data);
            printAligned(p.left, nSpace+3);
            printAligned(p.right, nSpace+3);
        }
    }
    public void printAligned() {
        if(root == null) System.out.println("Empty tree!");
        else
            printAligned(root, 0);
    }
    
    //DSW IMPLEMENTATION
    // Rotate right (quay phai), Granparent Gr, parent Par, left child Ch
    // xem slide co anh minh hoa se hieu ki hon
    public void rotateRight(BST_Node Gr, BST_Node Par, BST_Node Ch) {
        if(Par==root) root = Ch;   // It means that Gr=null
        else Gr.right = Ch;
        Par.left = Ch.right;
        Ch.right = Par;
    }
    
    // Create right backbone(duong xuong song phai)- Using right rotations
    // xem slide hinh minh hoa
    void createBackbone() {
        BST_Node Gr=null, Par = root, leftChild;
        while(Par!=null) {
            while(Par.left!=null) {   // rotate right all a subtree
                leftChild = Par.left;
                rotateRight(Gr, Par, leftChild);
                Par = leftChild;   // sau khi quay phải, nút con trái trở thành nút cha
                // continue rotating right
            }
            Gr = (Gr==null) ? root: Gr.right;  // Shift dơn ONE node 
            Par = Par.right;  //nút con phải trở thành Par/Gr cho cây con nhỏ bên dưới
        }
    }
    
    // Compute 2- based logarithm
    private double log2(double x) {
        return Math.log(x)/Math.log(2);  // log(10)x/log(10)2 = log(2)x
    }
    
    // Compute the height of the perfect balanced BST having given number of nodes
    private int heightPerfectBalancedTree(int noOfNodes) {
        return (int)log2(noOfNodes+1);  //cong thuc
    }
    
    // maximum nodes in the perfect balanced BST having the height of H
    // so nut toi da cua cay nhi phan hoan hao co chieu cao H
    private int sizeOfPerfectBalancedTree(int H) {
        return (int)(Math.pow(2,H)-1);  //cong thuc
    }
    
    // Rotate left Granparent Gr, parent Pr, right child Ch
    // xem slide co hinh minh hoa se hieu ki hon
    public void rotateLeft(BST_Node Gr, BST_Node Par, BST_Node Ch) {
        if(Par==root) root = Ch;  // It means Gr = null
        else Gr.left = Ch;
        Par.right = Ch.left;
        Ch.left = Par;
    }
    
    // Create balanced BST using left rotations
    void createBalancedTree() {
        // height of the perfect balanced tree
        int H = heightPerfectBalancedTree(noOfNodes);
        
        // number of nodes in the perfect balanced tree
        int perfectSize = sizeOfPerfectBalancedTree(H);
        int extraNodes = noOfNodes-perfectSize; //nút dư thừa=tổng nút - nút của cây hoàn hảo
        
         // Rotate left extra nodes to the left sub-tree
        BST_Node Gr=null, Par=root, rightChild=Par.right;
        for(int i = 0; i<extraNodes; i++) {
            rotateLeft(Gr, Par, rightChild);
            // Propagating(lan truyen) LEFT ROTATIONS along the right side
            Gr=rightChild;
            Par=Gr.right;
            if(Par!=null) rightChild = Par.right;
            else rightChild = null;
        }
        
        // Create perfect balanced tree having size nodes
        Gr = root;
        while(perfectSize>1) {
            perfectSize /= 2;
            // Loop rotating left from the root
            Gr=null; Par=root; rightChild=Par.right;
            for(int i = 0; i<perfectSize; i++) {
                if(rightChild!=null) {
                    rotateLeft(Gr, Par, rightChild);
                    // Progating(lan truyen) LEFT ROTATIONS along the right side
                    Gr=rightChild;
                    Par=Gr.right;
                    if(Par!=null) rightChild = Par.right;
                    else rightChild = null;
                }
            }
        }
    }
    
    // Balancing tree using DSW algorithm.  O(n)
    public void DSW_Balance() {
        if(root!=null) {
            // can bang kep - double balance
            createBackbone();       // step 1:  O(n)
            createBalancedTree();   // step 2:  O(n)
        }
    }
}
