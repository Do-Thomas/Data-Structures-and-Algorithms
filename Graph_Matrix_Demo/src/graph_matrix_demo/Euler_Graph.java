/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph_matrix_demo;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author DELL
 */
public class Euler_Graph extends Graph_Matrix{
    ArrayList<Integer> E_cycle;   // E-cycle = chu trinh Euler
    int startV = 0;  // start vertex is used to find the Euler cycle
    Euler_Graph() {
        super();
    }
    // Constructor: User assigns vertex names in a string
    Euler_Graph(String vertexNames) {
        super(vertexNames);
    }
    // Determining a vertex's degree 
    // bac cua dinh la tong cua 1 hang trong ma tran ke
    private int degree(int v) {
        int result = 0;
        for(int j=0; j<nVertices; j++) 
            result += adjMatrix[v][j];
        return result;
    }
    //counting number of odd vertices in the graph 
    // neu 1 dinh chia 2 du 1 (>0) thi countOdd++; nguoc lai du = 0 thi la bac chan -> count khong doi
    private int countOddVertices() {
        int count = 0;
        for(int i=0; i<nVertices; i++) 
            if(degree(i)%2>0)
                count++;
        return count;
    }
    // Checking whether the graph has the Euler cycle or not- Euler theorem
    // A graph has the Euler cycle <--> all vertices have even degree
    public boolean hasEulerCycle() {
        return countOddVertices() == 0;
    }
    // A garph has the Euler path <--> there are exactly 2 odd- degree vertices
    public boolean hasEulerPath() {
        return countOddVertices() == 2;
    }
    // When the vertex v is considered when detecting the Euler cycle
    // We can choose an arbitrary adjacent of v
    // To simplify, we choose the first adjacent of v
    private int firstAdjacent(int v) {
        for(int j=0; j<nVertices; j++)
            if(adjMatrix[v][j]>0)
                return j;
        return -1;
    }
    // Copy this graph to the other (copy graph ban dau de tuy chinh xu ly)
    // When the Euler algorithm runs, adjacency matrix must be changed
    public Euler_Graph copy() {
        Euler_Graph result = new Euler_Graph();
        result.nVertices = this.nVertices;
        result.vSet = this.vSet;
        result.setAdjMatrix(this.adjMatrix);
        return result;
    }
    // From u, going to v, one edge/ connection must be removed
    private void removeOneEdge(int u, int v) {
        if(adjMatrix[u][v]>0) {
            adjMatrix[u][v]--;
            adjMatrix[v][u]--;
        }
    }
    // Finding the Euler cycle from the vertex startV
    public ArrayList<Integer> findEulerCycle (int startV) {
        if(!hasEulerCycle())
            return E_cycle = null;
        this.startV = startV;
        Euler_Graph gr = this.copy();  // copy this graph to gr
        E_cycle = new ArrayList();  // Initial the Euler cycle result
        Stack<Integer> stk = new Stack();// set of vertices examined
        stk.push(startV);  //push to the stack all vertices of each sub-cycle
        int u;
        while(!stk.empty()) {
            u = stk.peek(); // detect edges of a sub- cycle
            int v= gr.firstAdjacent(u); // first adjacent of u
            if(v>=0) {  // v is an adjacent of u  -> an edge of a sub- cycle
                stk.push(v);  //put v to the set of examined
                gr.removeOneEdge(u, v); // remove connection u <--> v
            }
            // When u is isolated (degree=0), all sub-cycles related to u are detected
            // u is added to E-cycle
            else E_cycle.add(stk.pop());
        }
        return E_cycle;
    }
    // Get Euler cycle in string format
    public String EulerCycleStr() {
        if(E_cycle ==null)
            return null;
        String result="Euler cycle from"+this.vSet[startV] + ": ";
        for(Integer v: E_cycle)
            result += this.vSet[v]+" ";
        return result;
    }
    // Write the Euler cycle to file
    public boolean printEulerCycleToFile(String filename) throws Exception {
        if(E_cycle == null)
            return false;
        File f = new File(filename);  // open new file
        if(f.exists())
            f.delete();
        RandomAccessFile rf = new RandomAccessFile(f,"rw");
        rf.writeBytes(this.EulerCycleStr() + "\r\n");
        rf.close();
        return true;
    }
}
