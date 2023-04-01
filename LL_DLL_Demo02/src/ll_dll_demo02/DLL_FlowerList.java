/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ll_dll_demo02;

/**
 *
 * @author DELL
 */
public class DLL_FlowerList {
    DLL_Node head;
    DLL_Node tail;

    public DLL_FlowerList() {
        head = tail = null;
    }
    
    //Checking whether the list is empty or not
    public boolean Empty() {
        return head == null;
    }
    
    //Add a flower to the beginning of the list     O(1)
    public boolean addFirst(Flower f) {
        DLL_Node newNode = new DLL_Node(f);  //encapsulate aSD in a node
        if(this.Empty()) 
            head = tail = newNode;
        else {
            newNode.next = head;   // linkS: null <-- newNode <--> head
            newNode.previous = null;  //DLL_Node constructor did this assignment
            head.previous = newNode;
            head = newNode;  //now, the newNode is the head
        }
        return true;
    }
    //Add a flower to the beginning of the list. O(1)
    public boolean addFirst(String name, String original, int price) {
        Flower f = new Flower(name, original, price);
        return addFirst(f);
    }
    
    //Add a flower to the end of the list.   O(1)
    public boolean addLast(Flower f) {
        DLL_Node newNode = new DLL_Node(f);
        if(this.Empty())
            head = tail = newNode;
        else {
            tail.next = newNode;  //link the tail <--> newNode --> null
            newNode.next = null;  //DLL_Node constructor did the assignment
            newNode.previous = tail;
            tail = newNode;       //now, the newNode is the tail
        }
        return true;
    }
    
    //Add a flower to the end the list.   O(1)
    public boolean addLast(String name, String original, int price) {
        Flower f = new Flower(name, original, price);
        return addLast(f);
    }
    
    //Add a newNode before a given node.   O(1)
    public boolean addBefore(DLL_Node p, Flower f) {
        if(this.Empty() || p == head)
            return addFirst(f);
        else {
            DLL_Node newNode = new DLL_Node(f);
            DLL_Node before = p.previous;
            // before <--> newNode <--> p
            before.next = newNode;  //before --> newNode --> p
            newNode.next = p;
            p.previous = newNode;   // before <-- newNode <-- p
            newNode.previous = before;
        }
        return true;
    }
    
    //Add a newNode after a given node.   O(1)
    public boolean addAfter(DLL_Node p, Flower f) {
        if(this.Empty() || p == tail)
            return addLast(f);
        else {
            DLL_Node newNode = new DLL_Node(f);
            DLL_Node after = p.next;
            // p <--> newNode <--> after
            p.next = newNode;
            newNode.next = after;          // p --> newNode --> after
            after.previous = newNode;
            newNode.previous = p;         // p <-- newNode <--after
        }
        return true;
    }
    
    //Search operation on flower's name- Using linear search
    public DLL_Node search (String flowerName) {
        if(this.Empty()) 
            return null;
        Flower f = new Flower(flowerName);
        DLL_Node p = head;
        while (p!=null) {
            if(p.flower.equals(f))
                return p;
            else
                p=p.next;
        }
        return null;
    }
    
    //Remove operations
    public DLL_Node removeFirst() {
        if(this.Empty())
            return null;
        DLL_Node result = head;
        if (head == tail)
            head=tail=null;   //list has only one item
        else {
            DLL_Node newHead = head.next;
            newHead.previous = null;
            head = newHead;
        }
        return result;
    }
    
    public DLL_Node removeLast() {
        if(this.Empty())
            return null;
        DLL_Node result = tail;
        if(head==tail)
            head=tail=null;  
        else {
            DLL_Node newTail = tail.previous;
            newTail.next = null;
            tail = newTail;
        }
        return result;
    }
    private DLL_Node remove(DLL_Node p) {
        if(this.Empty() || p==null)
            return null;
        if(p==head)
            return removeFirst();
        if(p==tail) 
            return removeLast();
        DLL_Node before = p.previous;
        DLL_Node after = p.next;
        //before <--> p <--> after.   Remove p
        before.next = after;
        after.previous = before;
        return p;
    }
    
    public Flower remove(String flowerName) {
        DLL_Node p =this.search(flowerName);
        DLL_Node removedNode = remove(p);
        return (removedNode!=null) ? removedNode.flower: null;
    }
    
    //Traverals: print all flowers
    public void printALl() {
        if(this.Empty())
            System.out.println("Empty list!");
        else {
            for(DLL_Node p =head; p!=null; p=p.next)
                System.out.println(p.flower);
        }
    }
    
    //Traversals: print all flowers in reverse order
    public void printAll_reverse() {
        if(this.Empty())
            System.out.println("Empty list!");
        else {
            for(DLL_Node p=tail;p!=null; p=p.previous)
                System.out.println(p.flower);
        }
    }
    
    //Traversals: print all flowers BASED ON their original
    public void printAll_Org(String original) {
        if(this.Empty())
            System.out.println("Empty list!");
        else {
            for(DLL_Node p=head; p!=null; p=p.next)
                if(p.flower.original.equals(original))
                    System.out.println(p.flower);
        }
    }
}
