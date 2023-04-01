/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bst_demo01;

import java.io.PrintWriter;
import java.util.LinkedList;

/**
 *
 * @author DELL
 */
public class BST_Fish_Tree {
    BST_Node root;
    
    // Create an empty tree
    public BST_Fish_Tree() {
        root = null;
    }
    public boolean isEmpty() {
        return root==null;
    }
    
    // Add a fish methods- Recursive implementation
    public BST_Node add_recur(BST_Node curref, AquariumFish fish) {
        if(curref==null) 
            curref = new BST_Node(fish);
        else if(fish.compareTo(curref.fish)<0) 
            curref.left = add_recur(curref.left, fish);
        else
            curref.right = add_recur(curref.right, fish);
        return curref;
    }
    public void add_recur(AquariumFish fish) {
        root = add_recur(root, fish);
    }
    
    // Add some fishes using their names only. For testing
    public void add_recur(String...names) {
        for(String name: names)
            add_recur(new AquariumFish(name));
    }
    
    // Add a fish methods- Iterative implementation
    // Duplications are permitted
    public void add(AquariumFish fish) {
        BST_Node newNode = new BST_Node(fish);
        if(root==null) root = newNode;
        else {
            BST_Node pFather = null;   
            BST_Node pBefore = root;
            // Determining the father node
            while(pBefore!=null) {
                pFather = pBefore;
                if(fish.compareTo(pBefore.fish) < 0) pBefore = pBefore.left;
                else
                    pBefore = pBefore.right;
            }
            // Link pFather to the newNode
            if(fish.compareTo(pFather.fish) < 0) pFather.left = newNode;
            else pFather.right = newNode;
        }
    }
    
    // Add some fishes using their names only. For testing
    public void add(String... names) {
        for(String name: names) add(new AquariumFish(name));
    }
    
    // Search operation. Recursive implementation
    private BST_Node search_Recur(BST_Node curref, AquariumFish fish) {
        if(curref==null)
            return null;
        else {
            AquariumFish curFish = curref.fish;
            int d = fish.compareTo(curFish);  // is fish equal curfish return 0, if fish>curFish return 1, if f<cF return -1 
            if(d==0)
                return curref;
            else if(d<0)
                return search_Recur(curref.left, fish);
            else
                return search_Recur(curref.right, fish);
        }
    }
    
    // Search operation. Recursive implementation
    public BST_Node search_Recur(AquariumFish fish) {
        return search_Recur(root, fish);
    }
    
    // Search operation. Iterative implementation
    public BST_Node search(AquariumFish fish) {
        if(this.isEmpty())
            return null;
        BST_Node t = root;
        AquariumFish curFish;
        while(t!=null) {
            curFish = t.fish;
            int d = fish.compareTo(curFish);
            if(d==0)
                return t;
            else if (d<0) 
                t = t.left;
            else
                t = t.right;
        }
        return null;
    }
    
    // Getting the leftmost node of a tree (nut bien trai- ngoai cung ben trai)
    public BST_Node leftMost(BST_Node treeRoot) {
        BST_Node result = treeRoot;
        while(result.left!=null)
            result = result.left;
        return result;
    }
    // Getiing the leftmost node of this tree
    public BST_Node leftMost() {
        return leftMost(root);
    }
    
    // Getting the rightmost node of the tree(nut bien phai- ngoai cung ben phai)
    public BST_Node rightMost(BST_Node treeRoot) {
        BST_Node result = treeRoot;
        while(result.right != null) {
            result = result.right;
    }
        return result;
    }
    // Getting the rightmost of this tree
    public BST_Node rightMost() {
        return rightMost(root);
    }
    
    // visit: Print the fish to monitor(xuat ra man hinh)
    public void visit(BST_Node node) {
        System.out.println(node.fish);
    }
    // visit: Print the fish to the opening file (xuat ra file)
    public void visitF(PrintWriter pw, BST_Node node) {
        try{
            pw.println(node.fish);  // write a fish to file in a line
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    // Breadth Fist Traversal (duyet theo chieu ngang trai sang phai)
    // Print data to monitor(man hinh)
    public void BF_Traverse() {
        if(this.isEmpty()) {
            System.out.println("Empty tree!");
            return;
        }
        MyQueue queue = new MyQueue();
        queue.enqueue(root); // put the root the queue
        BST_Node node;
        while(!queue.isEmpty()) {
            node = queue.dequeue();
            visit(node);
            // put it's left and right child nodes to the queue
            if(node.left!=null)
                queue.enqueue(node.left);
            if(node.right!=null)
                queue.enqueue(node.right);
        }
    }
    
    // Breadth Fist Traversal (duyet theo chieu ngang trai sang phai)
    // Print data to file(xuat ra file)
    public void BF_Traverse_F(String filename) throws Exception {
        if(this.isEmpty()) {
            System.out.println("Empty tree!");
            return;
        }
        // open the text file for writing
        PrintWriter pw = new PrintWriter(filename);
        MyQueue queue = new MyQueue();
        queue.enqueue(root);
        BST_Node node;
        while(!queue.isEmpty()) {
            node = queue.dequeue();
            visitF(pw, node);  // wirte a data line to file
            if(node.left!=null)
                queue.enqueue(node.left);
            if(node.right!=null)
                queue.enqueue(node.right);
        }
        pw.close(); // closing the file
}
    
    //NLR Traversal: Print data to monitor
    private void NLR(BST_Node p) {
        if(p!=null) {
            visit(p);  //Node
            NLR(p.left);
            NLR(p.right);
        }
    }
    public void NLR() {
        if(this.isEmpty())
            System.out.println("Empty tree!");
        else
            NLR(root);
    }
    // NLR Traversal: Print data to file
    private void NLR_F(PrintWriter pw, BST_Node p) {
        if(p!=null) {
            try {
                visitF(pw,p);  // write a fish to file
                NLR_F(pw,p.left);
                NLR_F(pw,p.right);
            } catch(Exception e) {
                System.out.println(e);
            }
        }
    }
    public void NLR_F(String filename) throws Exception{
        if(this.isEmpty())
            System.out.println("Empty tree!");
        else {
            // open the text file for writing
            PrintWriter pw = new PrintWriter(filename);
            NLR_F(pw, root);
            pw.close();  // closing the file
        }
    }
    
    // LNR Traversal: Print data to monitor
    private void LNR (BST_Node p) {
        if(p!=null) {
            LNR(p.left);
            visit(p);   // Node
            LNR(p.right);
        }
    }
    public void LNR() {
        if(this.isEmpty())
            System.out.println("Empty tree!");
        else
            LNR(root);
    }
    // LNR Traversal: Print data to file
    private void LNR_F(PrintWriter pw, BST_Node p) {
        if(p!=null) {
            try {
                LNR_F(pw,p.left);
                visitF(pw,p);  // write a fish to file
                LNR_F(pw,p.right);
            } catch(Exception e) {
                System.out.println(e);
            }
        }
    }
    public void LNR_F(String filename) throws Exception {
        if(this.isEmpty())
            System.out.println("Empty tree!");
        else {
            PrintWriter pw = new PrintWriter(filename);
            LNR_F(pw, root);
            pw.close();  // close file
        }
    }
    
    // RNL Traversal: Print data to monitor
    private void RNL(BST_Node p) {
        if(p!=null) {
            RNL(p.right);
            visit(p);
            RNL(p.left);
        }
    }
    public void RNL() {
        if(this.isEmpty())
            System.out.println("Empty tree!");
        else
            RNL(root);
    }
    // RNL Traversal: Print data to file
    public void RNL_F(PrintWriter pw, BST_Node p) {
        if(p!=null) {
            try {
                RNL_F(pw,p.right);
                visitF(pw,p);
                RNL_F(pw,p.left);
            } catch(Exception e) {
                System.out.println(e);
            }
        }
    }
    public void RNL_F(String filename) throws Exception {
        if(this.isEmpty())
            System.out.println("Empty tree!");
        else {
            PrintWriter pw = new PrintWriter(filename);
            NLR_F(pw,root);
            pw.close();
        }
    }
    
    // Methods are used in remove operations
    // counting children of a node
    private int countChild(BST_Node node) {
        if(node.left==null && node.right==null)
            return 0;
        if((node.left!=null && node.right==null) || node.left==null && node.right!=null)
            return 1;
        return 2;
    }
    
    // Remove a leaf
    private BST_Node deleteLeaf(BST_Node father, BST_Node deleteChild) {
        if(deleteChild==root)
            root = null;    // or father==null, cay 1 nut
        else {
            if(father.left==deleteChild)
                father.left=null;
            else
                father.right=null;
        }
        return deleteChild;
    }
    
    // Remove a node which has ONE child
    private BST_Node deleteOneChildNode(BST_Node father, BST_Node deleteChild) {
        if(deleteChild==root) {   // neu nut goc la nut can xoa thi nut con la nut thay the nut goc
            if(root.left!=null)
                root = root.left;
            else
                root = root.right;
        }
        else {  // neu nuts can xoa khong la nut goc
            BST_Node grandChild;
            if(deleteChild.left!=null)
                grandChild = deleteChild.left;  // xem hinh slide slot 12-13, page 32 de hieu ro 
            else
                grandChild = deleteChild.right;
        }
        return deleteChild;
    }
    
    // Remove a node which has TWO children using Copy method
    private BST_Node deleteTwoChildrenNodeByCopy(BST_Node deleted) {
        //Find the rightmost node in the left subtree
        BST_Node father = deleted;
        BST_Node rightMost = deleted.left;
        while(rightMost.right!=null) {
                father = rightMost;
                rightMost = rightMost.right;
    }
        // Copy data in the rightMost to delete
        deleted.fish = rightMost.fish;
        // Remove the rightMost
        int count = countChild(rightMost);
        if(count==0)  // nút biên phải là nút lá
            return deleteLeaf(father, rightMost);  
        else  // nút biên phải có 1 con
            return deleteOneChildNode(father, rightMost);
}
    
    // Delete a fish
    public BST_Node deleteFish(AquariumFish f) {
        if(this.isEmpty())
                return null;
        BST_Node deleted = root, father = null;
        // Find the deleted node
        while(deleted!=null) {
            int d = f.compareTo(deleted.fish);
            if(d==0)
                break;  // deleted node is found
            else {
                father= deleted;
                if(d<0)
                    deleted = deleted.left;
                else
                    deleted = deleted.right;
            }
        }
        // Remove one node
        if(deleted==null)
            return null;
        int count = countChild(deleted);  //number of it's children
        if(count==0)
            return deleteLeaf(father, deleted);
        if(count==1)
            return deleteOneChildNode(father, deleted);
        return deleteTwoChildrenNodeByCopy(deleted);
    }
    
    // Internal class for level-based processing
    private class Node_Level{
        BST_Node node;
        int level;
        public Node_Level(BST_Node node, int level) {
            this.node = node;
            this.level = level;
        }
    }
    
    // Find the tree's height
    public int height() {
        if(this.isEmpty())return 0;
        int level = 1, maxLevel = 1;
        LinkedList<Node_Level> queue = new LinkedList();
        queue.addLast(new Node_Level(root,level));
        while(!queue.isEmpty()) {
            Node_Level nL = queue.removeFirst();
            level = nL.level;
            if(maxLevel<level) maxLevel = level;
            if(nL.node.left!=null) queue.addLast(new Node_Level(nL.node.left, level+1));
            if(nL.node.right!=null) queue.addLast(new Node_Level(nL.node.right, level+1));
        }
        return maxLevel;
    }
}

/*==================================================================
=======================================================================
||__TRAVERSAL__//breadth-first traversal
 ||     ||______//preorder
 ||     ||______//postorder
 ||     ||______//inorder
 ||     ||______//use BFS change second node have age >=5 to age = 10
 ||     ||______//preorder with condition: 3 <= price <= 5
 ||
 ||___INSERT____//insert by string
 ||       ||____//insert by integer/double
 ||       ||____//insert have age > 4 use BTF
 ||
 ||___SEARCH____//search
 ||       ||____//search by string
 ||       ||____//search by integer/double
 ||
 ||___COUNT_____//count node in tree
 ||       ||____//count node have 1 child
 ||       ||____//count node have exactly 2 child
 ||       ||____//count height of tree
 ||
 ||___DELETE____//delete by copy integer/double
 ||     ||______//delete by copy string
 ||     ||______//delete by node p
 ||
 ||___BALANCE___//balance tree
 ||     ||______//balance simple array list
 ||
 ||___ROTATE____//rotate left
 ||    ||_______//rotate right
 ||    ||_______//rotate any node
 ||    ||_______//rotate right root 
 ||
 ||___GET_______//get node father
 ||       ||____//get node by string
 ||       ||____//get node by integer/double
 ||___OTHER_____//calculate level of node
 ||      ||_____//calculate factor
 ||      ||_____//copy all node to tree by inorder traversal
 ||      ||_____//Calculate balance factor 
 ||      ||_____//Calculate level all node
 ||      ||_____//balance a binary search tree

===================================================================
=================================================================
//breadth-first traversal
    public void bfs(Node p){ //input root
        if(p == null) return;
        MyQueue m = new MyQueue();
        m.enqueue(p);
        while(!m.isEmpty()){
            Node q = (Node)m.dequeue();// get node
            if(q.left != null){ // if lever still hava node left
                m.enqueue(q.left);
            }
            if(q.right != null){ //if lever still hava node right
                m.enqueue(q.right);
            }
            visit(q);// traversal them
        }        
    }
	
//preorder
    public void preorder(Node p){
        if(p == null){
            return;
        }
        visit(p);
        preorder(p.left);
        preorder(p.right);
    }
	
//postorder
	public void postorder(Node p){
        if(p == null){
            return;
        }
        postorder(p.left);
        postorder(p.right);
        visit(p);
    }
	
//inorder
    public void inorder(Node p){
        if(p == null){
            return;
        }
        inorder(p.left);
        visit(p);
        inorder(p.right);
    }
 
//use BFS change second node have age >=5 to age = 10
    public void changeNodeCondition(Node p){
        if(p == null) return;
        MyQueue m = new MyQueue();
        m.enqueue(p);
        while(!m.isEmpty()){
            Node q = (Node)m.dequeue();
            if(q.left != null){
                m.enqueue(q.left);
            }
            if(q.right != null){
                m.enqueue(q.right);
            }
            if(q.info.age >= 5){ //maybe or not
                c++;
                if(c == 2){
                    q.info.age = 10;
                    break; 
                }
            }
        }        
    }
 
//preorder with condition: 3 <= price <= 5
   void preOrder2(Node p, RandomAccessFile f) throws Exception{
	 if(p==null) return;
     if(p.info.price>=3&&p.info.price<=5){
        fvisit(p,f);
     }
     preOrder2(p.left,f);
     preOrder2(p.right,f);
   }
	 
//insert by string
    public void insert(Person x){
        Node p = new Node(x);
        if(isEmpty()){root = p; return;}
        Node f = null;
        Node q = root;
        while(q != null){
            if(q.info.name.equals(x.name)){
                System.out.println("Insertion failed, duplicated key");
                return;
            }
            else if(q.info.name.compareToIgnoreCase(x.name) > 0){f = q; q = q.left;}
            else{f = q; q = q.right;}
        }
        if(f.info.name.compareToIgnoreCase(x.name) > 0) f.left = p;
        else f.right = p;
    }
    
//insert by integer/double
    public void insert(Car x){
	 Node q=new Node(x);
     if(isEmpty())
      {root=q;
        return;
       }
     Node f,p;
     f=null;p=root;
     while(p!=null)
       {if(p.info.price == x.price)
          {System.out.println("The key " + x.price + " already exists, no insertion");
            return;
          }
         f=p;
         if(x.price < p.info.price) p=p.left; else p=p.right;
       } 
      if(x.price< f.info.price) f.left=q; else f.right=q;
    } 
 
//insert have age > 4 use BTF
    public void breadthModifier(){
	if(root == null){ return; //change
            MyQueue m = new MyQueue();
            m.enqueue(root);
            while(!m.isEmpty()){
                Node q = (Node)m.dequeue();         
                if(q.left != null){
                    m.enqueue(q.left);
                }
                if(q.right != null){
                    m.enqueue(q.right);
                }
                if(q.info.age > 4){
                    h.insert(q.info);
                }
            }
		}
	}
 	 
 //search
    public Node search(Person x){
        return search(root, x);
    }

//search by string
    public Node search(Node p, Person x){
        if(p == null){
            return null;
        }
        if(p.info.name.equals.x.name){
            return p;
        }
        else if(p.info.name.compareToIgnoreCase(x.name) > 0){
            return search(p.left,x);
        }
        else{
            return search(p.right,x);
        }
    }
 
//search by integer/double
    public Node search(Node p, int key){
		if(p == null) return null;
		 if(p.info == key) return p;
		 else if(p.info > key) return search(p.left, key);
		 else return search(p.right, key);
		}
 
//count node in tree
    public int count(Node p){
	 if(p==null) return(0);
	   int k,h,r;
	   k = count(p.left);
	   h = count(p.right);
	   r = k+h+1;
	   return(r);
	 }
	
//count node have 1 child
   int countModifer(Node p) {
      int n = 0;
      MyQueue m = new MyQueue();
        m.enqueue(p);
        while(!m.isEmpty()) {
            Node q = (Node)m.dequeue();
            if(q.left != null) m.enqueue(q.left);
            if(q.right != null) m.enqueue(q.right);
            // If q has only one child, increment c by 1
            if(q.left == null && q.right != null) n++;
            if(q.right == null && q.left != null) n++;
        }
      return n;
  }
 
//count node have exactly 2 child
    int countNodeCo2con(Node p) {
      int n = 0;
      MyQueue m = new MyQueue();
        m.enqueue(p);
        while(!m.isEmpty()) {
            Node q = (Node)m.dequeue();
            if(q.left != null) m.enqueue(q.left);
            if(q.right != null) m.enqueue(q.right);
            if(q.left != null && q.right != null) n ++;
        }
      return n;
     }
  
//count height of tree
    public int height(Node p) {
        if (p == null) {
            return 0;
        }
        int l = height(p.left) + 1;
        int r = height(p.right) + 1;
        return (l > r) ? l : r;
    }
 
//delete by copy integer/double
    public void deleByCopy(int xPrice) {
        if (root == null) {
            System.out.println(" The tree is empty, no deletion");
            return;
        }
        Node f, p; // f will be the father of p
        p = root;
        f = null;
        while (p != null) {
            if (p.info.price == xPrice) {
                break;//Found key x
            }
            if (xPrice < p.info.price) {
                f = p;
                p = p.left;
            } else {
                f = p;
                p = p.right;
            }
        }
        if (p == null) {
            System.out.println(" The key " + xPrice + " does not exist, no deletion");
            return;
        }
        if (p.left == null && p.right == null) // p is a leaf node
        {
            if (f == null) // The tree is one node
            {
                root = null;
            } else {
                if (f.left == p) {
                    f.left = null;
                } else {
                    f.right = null;
                }
            }
            return;
        }
        if (p.left != null && p.right == null) // p has only left child
        {
            if (f == null) // p is a root
            {
                root = p.left;
            } else {
                if (f.left == p) // p is a left child
                {
                    f.left = p.left;
                } else {
                    f.right = p.left;
                }
            }
            return;
        }

        if (p.left == null && p.right != null) // p has only right child
        {
            if (f == null) // p is a root
            {
                root = p.right;
            } else {
                if (f.left == p) // p is aleft child
                {
                    f.left = p.right;
                } else {
                    f.right = p.right;
                }
            }
            return;
        }
        if (p.left != null && p.right != null) // p has both left and right children
        {
            Node q, fr, rp; // p's key will be replaced by rp's one
            fr = null;
            q = p.left;
            rp = q;
            while (rp.right != null) {
                fr = rp;
                rp = rp.right; // Find the right most node on the left sub-tree
            }
            p.info = rp.info;
            if (fr == null) // rp is just a left son of p 
            {
                p.left = rp.left;
            } else {
                fr.right = rp.left;
            }
        }
    }
	
//delete by copy string
    void deleByCopy(String xName){
	 Node f,p;
     f=null;p=root;
     while(p!=null)
       {if(p.info.name.equals(xName)) break;
         f=p;
         if(xName.compareTo(p.info.name)<0) p=p.left; else p=p.right;
       }
      if(p==null) return; // not found
      
      // p is leaf node
      if(p.left==null && p.right==null)
        {if(f==null) // p=root
           {root=null;
           }
           else
            {if(p==f.left) f.left=null; f.right=null;
             }
          return;
        } 

      // p has left child only
      if(p.left!=null && p.right==null)
        {if(f==null) // p=root
           {root=p.left;
           }
           else
            {if(p==f.left) f.left=p.left; f.right=p.left;
             }
          return;
        } 

      // p has right child only
      if(p.left==null && p.right!=null)
        {if(f==null) // p=root
           {root=p.right;
           }
           else
            {if(p==f.left) f.left=p.right; f.right=p.right;
             }
          return;
        } 

      // p has both 2 children
      if(p.left!=null && p.right!=null)
        {// find the right most node
          Node q=p.left;
          Node frp, rp; frp=null;rp=q;
          while(rp.right!=null) {frp=rp;rp=rp.right;}
          // rp is the right most node on the left child
          p.info=rp.info;
          if(frp==null) // rp=q
           {p.left=q.left;
           }
           else
           {
            frp.right=rp.left; 
           }
        }
    }
 
//delete by node p
    public void deleteByCopy(Node p) {
        if (isEmpty()) {
            return;
        }
        if(p == null){
            System.out.println("Key does not exists, deletion failed");
            return;
        }
        // Find Node f where f is father of p
        Node f = null;
        Node q = root;
        while(q != p){
            if(q.info.name.compareTo(p.info.name) > 0){         // Changed
                f = q;
                q = q.left;
            }
            else{
                f = q;
                q = q.right;
            }
        }
        // 1. p is a leaf (no right and left child)
        if (p.left == null && p.right == null) {
            // a BST has a Node only
            if (f == null) {
                root = null;
            } 
            else if (f.left == p) {
                f.left = null;
            }
            else if(f.right == p){
                f.right = null;
            }
        }
        // 2. p has a left child only
        else if(p.left != null && p.right == null){
            if(f == null){// remove root
                root = p.left;
            }
            else if(f.right == p){
                f.right = p.left;
            }
            else if(f.left == p){
                f.left = p.left;
            }
        }
        // 3. p has a right child only
        else if(p.right != null && p.left == null){
            if(f == null){// remove root
                root = p.right;
            }
            else if(f.right == p){
                f.right = p.right;
            }
            else if(f.left == p){
                f.left = p.right;
            }
        }
        // 4. Both of right and left child 
        else if(p.left != null && p.right != null){
            f = null;
            Node rp = p.left;
            while(rp.right != null){
                f = rp;
                rp = rp.right;
            }
            p.info = rp.info;
            if(f == null){// rp has no right child 
                p.left = rp.left;
            }
            else{
                f.right = rp.left;
            }
        }
    }
 
//balance tree
    public void balance(ArrayList a, int first, int last){
        if(first > last) return;
        int m = (first + last) /2;
        Person x = ((Node)a.get(m)).info;
        insert(x);
        balance(a, first, m-1);
        balance(a, m+1, last);
    }
	
//balance simple array list
    public void balance(Node p){
        ArrayList a = new ArrayList();
        buildArray(a, p);
        int first = 0;
        int last = a.size() - 1;
        BSTree b = new BSTree(); //create new tree 
        b.balance(a, first, last);
        root = b.root; //referen root to root b
    } 
 
//rotate left
    public Node rotateLeft(Node p){//must be have node right
        if(p.right == null){
            return p;
        }
        Node q = p.right;
        p.right = q.left;
        q.left = p;
        return q;
    }

//rotate right
    public Node rotateRight(Node p){
        if(p.left == null){
            return p;
        }
        Node q = p.left;
        p.left = q.right;
        q.right = p;
        return q;
    }

//rotate any node
       public void rotateModifier(Node node){
        Node nodeRotate = rotateToRight(node);
		Node nodeFather = father(node.info.price);
		if(nodeFather==null) root = nodeRotate;
        else{
			if(nodeFather.left==node) nodeFather.left = nodeRotate; 
			else  nodeFather.right = node;
         }
	}

//rotate right root 
    Node rotateToRight(Node p){//root = rotateToRight(root)
    if(p==null || p.left==null) return(p);
        Node q=p.left;
        p.left=q.right;
        q.right=p;
        return(q);
    }
	
//get node father
    Node father(int xPrice){
	 Node f,p;
     f=null;p=root;
     while(p!=null)
       {if(p.info.price == xPrice) break;
         f=p;
         if(xPrice < p.info.price)  p=p.left; else p=p.right;
       }
     return(f);
    }
		
//get node by string
    public Node getNode(String xName) {
        Node p = root;
        while (p != null) {
            if (p.info.name.compareToIgnoreCase(xName) > 0) p = p.left;
            else if (p.info.name.compareToIgnoreCase(xName) < 0) p = p.right;
            return p;
        }
        return null;
    }
	
//get node by integer/double
    public Node getNode(int x) {
        Node p = root;
        while (p != null) {
            if (p.info > x) p = p.left;
            else if (p.info < x) p = p.right;
            return p;
        }
        return null;
    }
 
//calculate level of node
    public void calLevel(Node p){
        if(p == null){
            return;
        }
        MyQueue m = new MyQueue();
        m.enqueue(p);
        p.level = 1;//first, leve = 1
        while(!m.isEmpty()){
            Node q = (Node)m.dequeue();         
            if(q.left != null){
                q.left.level = q.level +1;
                m.enqueue(q.left);
            }
            if(q.right != null){
                q.right.level = q.level +1;
                m.enqueue(q.right);
            }
            
        }        
    }

//calculate factor
    public void calculateBalance(Node p){
        if(p == null){
            return;
        }
        MyQueue m = new MyQueue();
        m.enqueue(p);
        while(!m.isEmpty()){
            Node q = (Node)m.dequeue();         
            if(q.left != null){
                m.enqueue(q.left);
            }
            if(q.right != null){
                m.enqueue(q.right);
            }
            q.bal = height(q.right) - height(q.left); 
            if(isAVL && q.bal < -1 || q.bal > 1){// De cho  thuc hien nhieu
                isAVL = false;
            }
        }        
    }

//copy all node to tree by inorder traversal
    public void buildArray(ArrayList a, Node p){
        if(p == null){
            return;
        }
        buildArray(a, p.left);
        a.add(p);
        buildArray(a, p.right);
    }

	
//Calculate balance factor 
  void calculateFactorBalance(RandomAccessFile  f123) throws Exception{
     boolean isAVL = true;
     MyQueue q = new MyQueue();
     q.enqueue(root);Node r;
     while(!q.isEmpty())
       {r = q.dequeue();
         r.bal = height(r.right) - height(r.left);//int bal in class Node; // balance factor of the node p = height(p.right) - height(p.left)
         if(r.bal>=2 || r.bal<=-2) isAVL = false;
         if(r.left!=null) q.enqueue(r.left);
         if(r.right!=null) q.enqueue(r.right);
       }
     breadthBal(root,f123);
    if(!isAVL)
     f123.writeBytes("\r\nThe tree is not an AVL tree\r\n");
     else
      f123.writeBytes("\r\nThe tree is an AVL tree\r\n");
  }
  public void breadthBal(Node  p, RandomAccessFile f) throws Exception{//use for balance factor
	if(p==null) return;
    MyQueue q = new MyQueue();
    q.enqueue(p); Node r;
    while(!q.isEmpty())
     {r = q.dequeue();
      fvisitBal(r,f);
      if(r.left != null) q.enqueue(r.left);
      if(r.right != null) q.enqueue(r.right);
     }
   }
  void fvisitBal(Node p, RandomAccessFile f) throws Exception
    {if(p != null) 
      f.writeBytes("("+p.info.name+","+p.info.age+","+p.bal+") ");
    }
  
 //Calculate level all node
  void calculateLevelAllNode(){
	MyQueue q = new MyQueue();
    if(isEmpty()) return; 
    root.level=1;
     q.enqueue(root);Node r;
     while(!q.isEmpty())
       {r = q.dequeue();
        if(r.left!=null) r.left.level = r.level +1;//level in class node
        if(r.right!=null) r.right.level = r.level +1;
        if(r.left!=null) q.enqueue(r.left);
        if(r.right!=null) q.enqueue(r.right);
       }
    }
	void fvisitLevel(Node p, RandomAccessFile f) throws Exception
    {if(p != null) 
      f.writeBytes("("+p.info.name+","+p.info.age+","+p.level+") ");
    }

//balance a binary search tree 
	void balance(){
	 ArrayList<Person> t;
     t = new ArrayList<Person>();
     inOrder(t,root);
     int n = t.size();
     clear();
     balance(t,0,n-1);
   }
   void inOrder(ArrayList<Person> t, Node p){
	if(p==null) return;
    inOrder(t,p.left);
    t.add(p.info);
    inOrder(t,p.right);
  }
  void balance(ArrayList<Person> t, int i, int j)
  {if(i>j) return;
    int k=(i+j)/2;
    insert(t.get(k));//insert person
    balance(t,i,k-1);
    balance(t,k+1,j);
  }
*/

