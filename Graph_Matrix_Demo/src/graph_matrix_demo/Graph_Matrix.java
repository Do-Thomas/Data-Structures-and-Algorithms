/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph_matrix_demo;

import java.io.File;
import java.io.RandomAccessFile;

/**
 *
 * @author DELL
 * Class for a simple graph using adjacency matrix
 */
public class Graph_Matrix {
    int nVertices;  // number of vertices
    int [][] adjMatrix;   // adjacency matrix;
    char [] vSet;       // vertex names. As default, they are ABCDE...
    // Constructor: Assigning default vertex names as ABCD...
    Graph_Matrix() {
        vSet = "ABCDEFGHIJKLMNOPQRSTUVXYZ".toCharArray();
    }
    
    // Contructor: User assigns vertex names in a string
    Graph_Matrix(String vertexNames) {
        vSet = vertexNames.toCharArray();
    }
    
    // Assigning an adjacency matrix to the graph
    void setAdjMatrix(int [][] m) {
        nVertices = m.length;
        adjMatrix = new int[nVertices][nVertices];
        for(int i = 0; i<nVertices; i++) {
            for(int j =0; j<nVertices; j++)
                adjMatrix[i][j] = m[i][j];
        }
    }
    
    // Dispaly the adjacency matrix
    void displayAdjMatrix() {
        int i, j;
        System.out.println("\nThe adjacency matrix:");
        for(i=0; i<nVertices; i++) {
            System.out.println();
            for(j=0; j<nVertices; j++)
                System.out.printf("%3d",adjMatrix[i][j]);
        }
    }
    
    // Create boolean visited[]. It is used in traversal algorithms
    private boolean[] createFlags() {
        boolean [] visited = new boolean[nVertices]; // create a flag array
        for(int i=0; i<nVertices; i++)
            visited[i] = false;
        return visited;
    }
    
    //METHOD FOR TRAVERSING A GRAPH. OUTPUT TO MONITOR
    //visiting the i(th) vertex: print out the vertex name
    void visit(int i) {
        System.out.print(vSet[i]+" ");
    }
    // Breadth-first traversing a connected component from the k(th) vertex
    void BF_traverseComponent(int k) {
        MyQueue q = new MyQueue();
        boolean [] visited = createFlags();
        q.enqueue(k);  // start the traversal
        visited[k] = true;
        int v;
        while(!q.isEmpty()) {
            v = q.dequeue();
            visit(v);
            for(int i=0; i<nVertices;i++) // goto it's adjacents
                if(!visited[i] && adjMatrix[v][i]>0) {
                    q.enqueue(i);
                    visited[i] = true;
                }
        }
    }
    // Breath-first traverse all vertices from the first vertex: 0
    void BF_traverseAll() {
        MyQueue q = new MyQueue();
        boolean [] visited = createFlags();
        int i,j;
        for(i=0; i<nVertices;i++) {  //start the traversal
            if(visited[i]==false) {  //i(th) vertex is not visited
                visited[i]=true;
                q.enqueue(i);
                while(!q.isEmpty()) {
                    int v = q.dequeue();
                    visit(v);
                    for(j=0; j<nVertices;j++) //goto it's adjacents
                        if(adjMatrix[v][j]>0 && !visited[j]) {
                            q.enqueue(j);
                            visited[j]=true;
                        }
                }
                
            }
        }
    }
    // Depth-first traversal the vertex v- Recursive implementation
    void DFS(int v, boolean[] visited) {
        visit(v);
        visited[v]=true;
        for(int j=0; j<nVertices;j++) //goto it's adjacency vertices
            if(!visited[j] && adjMatrix[v][j]>0)
                DFS(j, visited);
    }
    // Depth- first traverse a connected component from the k(th) vertex
    void DF_traverseComponent(int k) {
        boolean [] visited = createFlags();  //create flags
        for(int i=0; i<nVertices; i++)  //DF traversal all vertices
            if(visited[i]==false)
                DFS(i, visited);
    }
    // Depth-first tarverse all vertices from the first vertex: 0
    void DF_trverseAll() {
        boolean [] visited = createFlags();  //create flags
        for(int i=0; i<nVertices; i++)
            if(visited[i]==false)
                DFS(i, visited);
    }
    
    // METHODS FOR TRAVERSING A GRAPH. OUTPUT TO A FILE
    // Create a new file
    private RandomAccessFile createFile(String fname) throws Exception {
        File f = new File(fname);  //open new fule for writing result
        if(f.exists())
            f.delete();  // if file existed then delete it to create new one.
        RandomAccessFile rf = new RandomAccessFile(f,"rw");
        return rf;
    }
    // Visiting the i(th) vertex: write the vertex name to file
    void visit_F(RandomAccessFile f, int i) throws Exception {
        f.writeBytes(vSet[i]+" ");
    }
    //Breath-first traversing a connected component from the k(th) vertex
    void BF_traverseComponent_F(String filename, int k) throws Exception {
        RandomAccessFile rf = createFile(filename);
        boolean [] visited = createFlags();
        MyQueue q = new MyQueue();  // Traversing the graph to file
        q.enqueue(k);;
        visited[k] = true;
        int v;
        rf.writeBytes("BF traversal from the vertex "+vSet[k]+":\r\n");
        while(!q.isEmpty()) {
            v= q.dequeue();
            visit_F(rf,v);
            for(int j=0; j<nVertices; j++)
                if(!visited[j] && adjMatrix[v][j]>0) {
                    q.enqueue(j);
                    visited[j]=true;
                }
        }
    rf.writeBytes("\r\n");
    rf.close();  //closing the file
    }
    // Breath-first traverse all vertices from the first vertex
    void BF_traverseAll_F(String filename) throws Exception {
        RandomAccessFile rf = createFile(filename);
        boolean [] visited = createFlags();
        rf.writeBytes("BF all vertices, from "+vSet[0]+":\r\n");
        MyQueue q = new MyQueue();
        int i,j;
        for(i=0; i<nVertices;i++) {  //traversing all vertices
            if(visited[i]==false) { // if i(th) vertex is not visited
                visited[i]=true;
                q.enqueue(i);
                while(!q.isEmpty()) {
                    int v = q.dequeue();
                    visit_F(rf,v);
                    for(j=0; j<nVertices; j++)  //examining all it's adjacents
                        if(adjMatrix[v][j]>0 && !visited[j]) {
                            q.enqueue(j);
                            visited[j]=true;
                        }
                }
            }
        }
        rf.writeBytes("\r\n");
        rf.close();  
    }
    // Depth-first search the vertex v of the graph- Recursive implementation
    // Using the marked array, names visited
    void DFS_F(RandomAccessFile rf, int v, boolean [] visited) throws Exception {
        visit_F(rf, v);
        visited[v] = true;
        for(int j=0; j<nVertices; j++) // examining adjacent vertices
            if(!visited[j] && adjMatrix[v][j]>0)
                DFS_F(rf,j, visited);
    }
    //Depth-first traverse a connected component from the k(th) vertex
    void DF_traverseComponent_F(String filename, int k) throws Exception {
        RandomAccessFile rf = createFile(filename);
        boolean [] visited = createFlags();
        rf.writeBytes("DF from "+ vSet[k]+":\r\n");
        DFS_F(rf,k,visited);
        rf.writeBytes("\r\n");
        rf.close();
    }
    // Depth-first traverse all vertice from the first vertex
    void DF_traverseAll_F(String filename) throws Exception {
        RandomAccessFile rf = createFile(filename);
        boolean [] visited = createFlags();
        rf.writeBytes("DF traverse all vertex from "+vSet[0]+":\r\n");
        int i=0;
        for(i=0;i<nVertices; i++)
            if(visited[i]==false)
                DFS_F(rf,i,visited);
        rf.writeBytes("\r\n");
        rf.close();
    }
}
