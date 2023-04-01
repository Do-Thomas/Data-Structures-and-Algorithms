/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll_sll_demo01;

/**
 *
 * @author DELL
 */
import java.io.File; //for accessing file managed by operating system
import java.io.RandomAccessFile;  //for writing data to binary file
import java.io.FileReader;      //for reading text file (read a character)
import java.io.BufferedReader;   //for reading text file (read a line)
import java.io.PrintWriter;   //for writing data to text file
import java.util.StringTokenizer;   //for splitting a string

public class SLL_SoftDrink {

    SLL_Node head, tail;   //2 references
    //constructor: Initiating an empty singly linked list

    public SLL_SoftDrink() {
        head = tail = null;
    }

    //Checking whether the list is empty or not
    public boolean Empty() {
        return head == null;
    }

    //Add a soft drink to the beginning of the list. O(1)
    public void addFirst(SoftDrink aSD) {
        SLL_Node newNode = new SLL_Node(aSD);   //Wrap the aSD in a node
        if (this.Empty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;    //link the newNode to the head
            head = newNode;         //now, the newNode is the head
        }
    }

    //add a soft drink to the beginning of the list.  O(1)
    public void addFirst(String pLine, String company, int volume, int price) {
        addFirst(new SoftDrink(pLine, company, volume, price));
    }

    //add a soft drink to the end of the list.    O(1)
    public void addLast(SoftDrink aSD) {
        SLL_Node newNode = new SLL_Node(aSD);
        if (this.Empty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;   // link the tail to the newNode
            tail = newNode;        //now, the newNode is the tail
        }
    }

    //add a soft drink to the end of the list.     O(1)
    public void addLast(String pLine, String company, int volume, int price) {
        addLast(new SoftDrink(pLine, company, volume, price));
    }

    //Read data lines in a text file to the list
    //Format: Miranda, Shirinda, 300, 7300
    private SoftDrink createSD(String line) {
        StringTokenizer stk = new StringTokenizer(line, ",");
        String productLine = stk.nextToken().trim();
        String company = stk.nextToken().trim();
        int volume = Integer.parseInt(stk.nextToken().trim());
        int price = Integer.parseInt(stk.nextToken().trim());
        return new SoftDrink(productLine, company, volume, price);
    }

    public void loadFromFile(String fName) throws Exception {
        FileReader fr = new FileReader(fName);
        BufferedReader bf = new BufferedReader(fr);
        String line;
        StringTokenizer stk;
        for (int i = 0; i < 3; i++) {  //test the addFirst() method
            line = bf.readLine();
            this.addFirst(createSD(line));
        }
        while ((line = bf.readLine()) != null) //test the addLast() method
        {
            this.addLast(createSD(line));
        }
    }

    //Search operation on productLine- Using linear search.     O(n)
    public SoftDrink search(String productLine) {
        if (this.Empty()) {
            return null;
        }
        SLL_Node p = head;
        while (p != null) {  //O(n)
            if (p.data.productLine.equals(productLine)) {
                return p.data;
            } else {
                p = p.next;
            }
        }
        return null;
    }

    // Reverse the SLL
    public void reverse() {
        if (this.Empty()) {
            return;
        }
        SLL_Node after = null, p = head, before = p.next;
        while (p != null) {
            p.next = after;
            after = p;  //shift a step
            p = before;
            if (p != null) {
                before = p.next;
            }
        }

        //Update head and tail
        p = head;
        head = tail;
        tail = p;
    }

    //Remove a soft drink based on it's product line - Linear search: O(n)
    public SoftDrink remove(String pLine) {
        if (this.Empty()) {
            return null;
        }
        SoftDrink aSoftDrink = new SoftDrink(pLine);
        SoftDrink removedEle = null;
        SLL_Node pAfter = null, pDel = head;
        //Linear search for positioning the node which will be removed
        while (pDel != null && !pDel.data.equals(aSoftDrink)) {  //O(n)
            pAfter = pDel;
            pDel = pDel.next;
        }
        //In case of the list contains the node which must be removed
        if (pDel != null) {
            removedEle = pDel.data;  //return value
            if (pDel == head) {   //removing the head
                if (head == tail) {
                    head = tail = null;  //list coontains only one node
                } else {
                    head = head.next;
                }
            } else if (pDel == tail) {  //removing the tail
                pAfter.next = null;
                tail = pAfter;
            } else {
                pAfter.next = pDel.next;   //removing a middle node
            }
        }
        return removedEle;
    }

    // Traversing for printing elements to monitor
    private void visit(SLL_Node node) {
        System.out.print(node.data + "\n");
    }

    // printing all elements
    public void printAll() {
        if (this.Empty()) {
            System.out.println("EMPTY LIST.");
        } else {
            for (SLL_Node p = head; p != null; p = p.next) {
                visit(p);
            }
        }
    }

    //Traversing for println all elements of a company
    public void printCompanyBased(String company) {
        if (this.Empty()) {
            System.out.println("EMPTY LIST.");
        } else {
            for (SLL_Node p = head; p != null; p = p.next) {
                if (p.data.company.equals(company)) {
                    visit(p);
                }
            }
        }
    }

    //Ascending sort by soft drink's price then by product line.   O(n^2)
    public void ascSort_Price_then_pLine() {
        if (this.Empty()) {
            return;
        }
        SoftDrink t;
        SLL_Node i, j;
        for (i = head; i != tail; i = i.next) {
            for (j = i.next; j != null; j = j.next) {
                if (j.data.compareTo(i.data) < 0) {
                    t = i.data; //moving up smaller data
                    i.data = j.data;
                    j.data = t;
                }
            }
        }
    }

    //OPERAIONS FOR TRAVERSING A LIST TO BINARY AND TEXT FILES
    //Traversing for printing elements to opening binary file
    private void visit_binF(RandomAccessFile f, SLL_Node node) throws Exception {
        // write a string as a sequence of bytes
        f.writeBytes(node.data.toString() + "\r\n");  //line-by-line format
    }

    //Traversing for printing all elements to a new binary file
    public void printAll_binF(String filename) throws Exception {
        if (this.Empty()) {
            System.out.println("EMPTY LIST.");
        } else {
            File f = new File(filename);
            if (f.exists()) {
                f.delete();  //delete old file
            }
            RandomAccessFile rf = new RandomAccessFile(f, "rw");
            for (SLL_Node p = head; p != null; p = p.next) {
                visit_binF(rf, p);
            }
            rf.close();
        }
    }

    // Traversing for printing elements to a opening text file
    private void visit_txtF(PrintWriter f, SLL_Node node) throws Exception {
        f.println(node.data);  //line-by-line format
    }

    //Traversing for printing all elements to a NEW binary file
    public void printAll_txtF(String filename) throws Exception {
        if (this.Empty()) {
            System.out.println("EMPTY LIST.");
        } else {
            PrintWriter pw = new PrintWriter(filename);   //overriding node
            for (SLL_Node p = head; p != null; p = p.next) {
                visit_txtF(pw, p);
            }
            pw.close();
        }
    }

}

/*=====================================================================
======================================================================
(more)===============================================================
(2)
//add last
    public void addLast(Person x){
        Node q = new Node(x);
        if(isEmpty()){head=tail=q;return;}
        tail.next=q;tail=q;
    }
	
//add first
    public void addFirst(Person x){
		Node p = new Node(x);
		if(isEmpty()) {head = tail = p;}
		else {p.next = head; head = p;}
    }
	
//add many
	public void addMany(String [] a, int [] b){
		int n,i; n=a.length;
		for(i=0;i<n;i++) addLast(new Person(a[i],b[i]));
    }
	
//add if age > 4
    public void addLastCondition(){
        Node p=head;
        while(p!=null){
            if(p.info.age>4) h.addLast(p.info);
            p=p.next;
        }
    }

//add element have max age
    public void addMaxAgeInH(){
		Person k = getMaxColor();
		Node p = head;
		while(p != null){
			if(p.info.age == k.age) h.addLast(p.info);
			p = p.next;
		}
	}
	
//insert position k
 public Node getNode(int k) {          
        int c = 0;                              
        Node p = head;
        while (p != null && c < k) {
            p = p.next;
            c++;                                
        }
        return p;                               
    }
	public void insertPositionK(Person x, int position) {
		if (isEmpty()) head = tail = new Node(x);
		int count = 1;
		Node p = head;
		while (p!= null && count < position) {
			p = p.next;
			count ++;
		}
		Node temp = p.next;
		p.next = new Node(x, temp);
	} 
	
//insert after position k
	public void addAfterPositionK(int k, Person c){
        Node p = new Node(c);
        if(k == -1){
            addFirst(c); return;
        }
        int count = 0;
        Node p1 = head;
        while(p1 != null && count < k){
            p1 = p1.next; count++;
        }
        if(p1.next == null && count == k){
            addLast(c); return;
        }
        p.next = p1.next;
        p1.next = p; 
    }
	
//search node
	public Node search(int x{
		Node p = head;
		while(p != null && p.info != x) p = p.next;
		return p;
	}
	
//search node with string
	public Node search(String colorName){
        Node p = head;
        while(p != null){
            if(p.info.name.equalsIgnoreCase(colorName)){
                break;
            }
            p = p.next;
        }
        return p;
	}
	
//search node with integer/double
	public Node search(int xAge){
		Node p=head;
		while(p!=null){
			if(p.info.age == xAge) return(p);
			p=p.next;
        }
		return(null);
     }
	
//delete node
	public void dele(Node q){
		Node f,p; f=null;p=head;
		while(p!=null){
			if(p==q) break;
			f=p;p=p.next;
		}
		if(p==null) return;
		if(f==null){
			head=head.next;
			if(head==null) tail=null;
			return;
		}
		f.next=p.next;
		if(f.next==null) tail=f;
	 }
	 
//delete all
	public void deleAll(int xAge){
		Node q;
		while(true){
			q = searchByAge(xAge);
			if(q==null) break;
			dele(q);
		}   
    }
	 
//delete at position k
	public void deleteAt(int k) {
        if (isEmpty()) return;
        if (k == 0) {//check if node is head
            Node p = head;
            head = head.next;
            p.next = null;
        } else {
            Node p = getNode(k);//get node position k
            if (p == null) return;
            Node q = getNode(k - 1);//q is node before of p
            // Remove p
            q.next = p.next;
            p.next = null;
            if (p == tail) tail = q;
        }
    }

//delete first no age < 6
    public void deleteFirstCondition(){
		Node p = head;
		while(p != null){
			if(p.info.age<6) dele(p);
			p=p.next;
		}
    }
	
//delete a node after position k
    public void deleteAfterPosK(int k){
        if(isEmpty()) return;
        //remove head
        if(k == -1) {
            Node p = head;
            head = head.next;
            p.next = null;
        }else {
            Node p = getNode(k + 1);
            if(p == null) return;
            Node q = getNode(k);
            //remove p
            q.next = p.next;
            p.next = null;
            if(p == tail) tail = q;
        }
    }
	
//delete node after 2 node have age < 9
	public void deleteAfterTwoNodeAgeSmallerNine(){
		Node p = head;
		while(p != null && p.info.age >= 9){
			p = p.next;
		}
		if(p == null && p.next == null){
			return;
		}
		remove(p.next.next);
	}
	
//delete node thirdth have age < 6
	public void removeThirthAgeSmallerSix(){
		Node p = head;
		int count = 0;
		while(p != null){
			if(p.info.age < 6 && count != 3) count ++;
			else if(p.info.age < 6 && count == 3) break;
			p = p.next;
		}
		remove(p);
	}
	
//delete node first after node have position k
	public void deleteFirstAfterPosition(int k){
		Node p = getNode(k);
        if(p == null || p.next == null){//if p is tail then donothing
            return;
        }
        Node q = p.next;//if p is before tail then remove tail
        if(q.next == null){
            tail = p;
            p.next = null;
        }
        else{
            p.next = q.next;
            q.next = null;
        }
	}
	
//delete node after node have price > xPrice
	public void deleteAfterCondition(double xPrice){
		Node p = head;
		while (p != null && p < xPrice) {
			p = p.next;
		}
		if (p == null || p.next == null) return;
		Node q = p.next;
		if (q == tail) tail = p;
		p.next = q.next;
		q.next = null;
	}

//delete 2 node last have age > 5
	public void removeTwoLastNodeCondition(){
		int c = 0; int sz = size();
		for(int i = sz - 1; i >= 0; i--){
			Node p = getNode(i);
			if(p.info.age > 5){
				c++;
				remove(p);
            if(c >= 2) break;
			}
		}
    }
	
//delete second biggest
	public void removeSecond() {
        Person firstMax = getMaxColor();
        if(firstMax == null) return;
        int n = size();
        if(n <= 1 ) return;
        int imax = 0; Node p = head;
        while(p != null && p.info.age == firstMax.age){
            imax++;
            p = p.next;
        }
        // Find second max starting from imax
        Person secondMax = (p != null) ? p.info : null;
        for (int i = imax + 1; i < n; i++) {
            Node pi = getNode(i);
            if(pi.info.age > secondMax.age && pi.info.age != firstMax.age){
                imax = i;
                secondMax = pi.info;
            }
        }
        if(imax < n) remove(imax);
    } 
	 
//sort by string
    public void sort(){
		Node pi,pj; Person x;
		pi=head;
		while(pi!=null){
			pj=pi.next;
			while(pj!=null){
				if(pj.info.name.compareTo(pi.info.name)<0){
					x=pi.info;pi.info=pj.info;pj.info=x;
				}
				pj=pj.next;
			}
			pi=pi.next;
		}
    }
	
//sort by integer/ double
	public void sort() {
		Node pi,pj; Car x;
		pi=head;
		while(pi!=null){
			pj=pi.next;
			while(pj!=null){
				if(pj.info.price<pi.info.price){
					x=pi.info;pi.info=pj.info;pj.info=x;
				}
				pj=pj.next;
			}
			pi=pi.next;
		}
    }
	
//sort by for
	public void sortByFor(){
		int n = (size() > 3) ? 3 : size();
			int n = size();         
    // for(int i = n-3; i < n; i++) //last 3 element
    // for(int j = i+1; j < n; j++) //first 3 element
		for(int i = 0; i < n - 1; i++){   
			for(int j = i+1; j < n; j++){
				Node pi = getNode(i);
				Node pj = getNode(j);
				if(pi.info.name.compareToIgnoreCase(pj.info.name) > 0){
					Person temp = pi.info;
					pi.info = pj.info;
					pj.info = temp;
				}
			}
		}
	}
	
//sort 3 first element 
	public void sortThird() {
        Node pi, pj; pi = head; int count = 0; 
        while(pi != null) {
            count++; pj = pi.next; int count1 = 0;
            while(pj != null) {
                count1++;
                if(pi.info.name.compareToIgnoreCase(pj.info.name) < 0) {
                    Person t = pi.info; pi.info = pj.info;pj.info = t;
                }
                pj = pj.next; if(count1 == 3 - count) break;
            }
            pi = pi.next; if(count == 2) break;
        }
    }
	
//swap min max
	public void swapMinMax(){
		Node min = getMin();
		Node max = getMax();       
        Person t = min.info;
        min.info = max.info;
        max.info = t;
	}
	
//swap node max second with node min first
	public void swapMax2Min1(){
		Node max = getMax();
		Node min = getMin();
		Node p = head;
		int count = 0;
		while(p != null){
			if(p.info.age == max.info.age) count++;
			if(count == 2) break;
			p = p.next;
		}
		Person temp;
		temp = p.info;
		p.info = min.info;
		min.info= temp;
	}
     
//get node at index k
	public Node getNode(int k) {
        int c = 0;
        Node p = head;
        while (p != null && c < k) {
            p = p.next;
            c++;
        }
        return p;
    }
	
//get node max
	public Person getMaxPerson(){
        if(isEmpty()) return null;
        Person max = head.info;                
        Node p = head;
        while( p != null){
            if(p.info.age > max.age) max = p.info;
            p = p.next;
        }
        return max; 
    }

//traverse
    public void traverse(){
		MyList h=new MyList();
		Node p=head;Person x;
		while(p!=null)
			{x=p.info;h.addFirst(x);
			p=p.next;
        }
		head=h.head;tail=h.tail;
    }

//replace a node
	public void replace{
	    Node p = head;
        while(p != null){
            if(p.info.name.equals("xName")){// xName was given
                 break;
            }
            p = p.next;
        }
         if(p != null){                       
              p.info.name = yName;// yName was given
        }
	}
	
//count number of node
	int count(Node p)
	 {if(p==null) return(0);
	   int k,h,r;
	   k = count(p.left);
	   h = count(p.right);
	   r = k+h+1;
	   return(r);
	 }
	
//reverse list
	public void reverse() {
        int n = size(), i, j;
        for (i = 0, j = n - 1; i < j; i++, j--) {
            Node pi = getNode(i);// create a node = getnode index i
            Node pj = getNode(j);// create a node = getnode index j
            Person temp = pi.info;// Note: change value of node, not change node
            pi.info = pj.info;
            pj.info = temp;
        }
    }
	
//append another list
    void appendAnotherList(MyList h){
      Node p=h.head;
         while(p!=null)
          {addLast(p.info);
            p=p.next;
      }
    }
	
//change name first
    void changeNameFirst(){
      Node p=head;
        while(p!=null)
         {if(p.info.name.equals("C6")) {p.info.name="CX";break;}
           p=p.next;
         }
	}
}
 */

 /*(less)=============================================================
(1)================================================================
//add last
    public void addLast(int x) 
    {Node p = new Node(x);
     if(isEmpty()) {head = tail = p;}
     else {tail.next = p; tail = p;}
    }
	
//add first
	public void addFirst(int x) 
    {Node p = new Node(x);
     if(isEmpty()) {head = tail = p;}
     else {p.next = head; head = p;}
    }
	
//return number of nodes in the list
    public int size() 
    {Node p = head; int c = 0;
      while(p != null) {c++; p = p.next;}
      return c;
    }
	
//sort the list ascending 
    public void sort() 
    {int n = size();
     for (int i = 0; i < n - 1; i++) 
     {for (int j = i + 1; j < n; j++) 
     {Node pi = get(i), pj = get(j);
       if(pi.info > pj.info) {
        int t = pi.info; pi.info = pj.info; pj.info = t;}
     }}
    }
	
//return the first Node which infor = given x otherwise return null
    public Node search(int x) 
    {Node p = head;
      while(p != null && p.info != x) p = p.next;
      return p;
	}
//delete the first node
	void dele(Node q)
	 {Node f,p; f=null;p=head;
	  while(p!=null)
	   {if(p==q) break;
		f=p;p=p.next;
	   }
	  if(p==null) return;
	  if(f==null)
	   {head=head.next;
		if(head==null) tail=null;
		return;
	   }
	  f.next=p.next;
	  if(f.next==null) tail=f;
	 }
	 
//remove a node from the list
    public void remove(Node p) 
    {if(p == null) return;
     //find q where q.next = p
     Node f = head, q = null;
     while(f != null && f != p) {q = f; f = f.next;}
     //remove head
     if(q == null) {head = head.next;
        if(head == null) tail = null;
     }else {q.next = p.next;
       if(p == tail) tail = q;
     }
     p.next = null;
    }

//reverse a list
    public void reverse() 
    {int n = size();
     for(int i = 0, j = n - 1; i < j; i ++, j --) {
      Node pi = get(i), pj = get(j);
      int t = pi.info; pi.info = pj.info; pj.info = t;}
    }

 */
