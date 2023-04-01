/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph_matrix_demo;

import java.io.PrintWriter;
import java.util.LinkedList;

/**
 *
 * @author DELL
 * Dijkstra- Find the shortest paths from a given vertex in
 * a directed weighted graph(do thi co huong co trong so)
 */
public class DJ_Finder {
    // INF (infinity-vo cuc) = lay gtri lon nhat cua kieu 
    // int(co the chon kieu khac)=2147483647 de lam gtri vo cuc
    public static final int INF = Integer.MAX_VALUE;
    Graph_Matrix g;
    int startV= -1;  //chon startVertex de bat dau di tim duong ngan nhat
    boolean[] flags;  // array of flags
    int[] costs;   // array of costs from startV to each vertex
    int[] predecessors;  // array of predecessors of each vertex
    int n;  // number of vertices
    boolean finished = false;  // whether DJ algorithm finished or not

    public DJ_Finder(Graph_Matrix g) {
        this.g = g;
        // Create array of marks, costs and predecessors(to tien/ tien boi)
        n = g.nVertices;
        flags = new boolean[n];
        costs = new int[n];
        predecessors = new int[n];
    }
    
    // Preparing before finding shortest path from a given vertex
    public void prepare(int startV) {
        for(int i=0; i<n; i++) {
            flags[i] = false; // this vertex is not examined yet
            costs[i] = INF; //infinity
            predecessors[i]= -1;   // predecessors=null
        }
        // Initiate data of the vertex startV
        // flags=true thi khong xet lai nut do nua
        flags[startV] = true;
        costs[startV] = 0;
        finished = false;
    }
    // getting the current vertex having minimum cost
    private int getMinCostVertex() {
        int minVertex = -1;   // -1 ~ null
        for(int i=0; i<n; i++) {
            if(flags[i]==false) {
                if(minVertex==-1)
                    minVertex = i;
                else if(costs[i] < costs[minVertex])
                    minVertex = i;
            }
        }
        return minVertex;
    }
    
    // Run the Shortest Path DJ algorithm from the start vertex
    public void DJ(int startV) {
        this.startV = startV;
        finished = false;
        prepare(startV);  //preparation
        int v= startV;
        int newCostToU, weightVU;
        while(v!=-1) {  // when not completed yet
            flags[v] = true;  // khac null-> co gtri -> true(da danh dau)
            // examine non- marked adjacents of v
            for(int u=0; u<n; u++) {
                weightVU = g.adjMatrix[v][u];
                if(weightVU < INF && flags[u]==false) {
                    newCostToU = costs[v] + weightVU;
                    if(newCostToU < costs[u]) {
                        costs[u] = newCostToU;
                        predecessors[u] = v; // nut truoc u la v vif v -> u
                    }
                }
            }
            v= getMinCostVertex();  // get the next vertex
        }
        finished = true;
    }
    
    // Get the edge v --> u, using the format: "[v,u,8]"
    private String getEdgeStr(int v, int u) {
        return "[" + g.vSet[v] + "," + g.vSet[u] + "," + g.adjMatrix[v][u] + "]";
    }
    // get a shortest path from startV to the vertex u
    private String getShortestPath(int u) {
        LinkedList<String> path = new LinkedList();
        int dest = u;  // destination: dich den
        int src = predecessors[dest];  // nut truoc nut destination
        while(src!=-1) {
            path.addFirst(getEdgeStr(src, dest));
            dest = src;
            src = predecessors[dest];
        }
        String result = "** Shortest Path " + g.vSet[startV] + " -> " +
                        g.vSet[u] + ", length= "+costs[u] + " : " +path;
        return result;
    }
    
    // Convert shorest paths to String
    public String spsToString() {
        String result= null;
        if(finished) {
            result="";
            for(int i=0; i<n; i++)
                result += getShortestPath(i) +"\r\n";
        }
        return result;
    }
    
    // Print all shortest paths to file
    public boolean printSPsToFile(String filename) throws Exception {
        if(!finished)
            return false;
        PrintWriter pw = new PrintWriter(filename);
        pw.print(this.spsToString());
        pw.close();
        return true;
    }
}
