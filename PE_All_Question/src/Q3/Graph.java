package Q3;

import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Graph {

    //Đề thi thử Fall 2022
    //f1 - depth-first, i = 0, display 5 vertices from the 2nd vertex
    //the 2nd to 6th
    //A B E H I C D G F 
    // B E H I 
    int n;
    int[][] a;

    void depth2(ArrayList<Integer> list, boolean[] visited, int k, RandomAccessFile f) throws Exception {
        list.add(k);
        visited[k] = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && a[k][i] > 0) {
                depth2(list, visited, i, f);
            }
        }
    }

    void depth2(ArrayList<Integer> list, int k, RandomAccessFile f) throws Exception {
        boolean[] visited = new boolean[20];
        int i;
        for (i = 0; i < n; i++) {
            visited[i] = false;
        }
        depth2(list, visited, k, f);
        for (i = 0; i < n; i++) {
            if (!visited[i]) {
                depth2(list, visited, i, f);
            }
        }
    }
    
    void f1() {
//        ArrayList<Integer> list = new ArrayList<>();
//        depth2(list, 0, f);
//        for (int i = 1; i < 6; i++) {
//            fvisit(list.get(i), f);
    }
//=========================================================================================================================================== 
    //f1: depth-first, i = 2, display first 6 vertices only
    // C H D A E F I G B
    //C H D A E F
    int count  = 0;
    void depth2(boolean[] visited, int k, RandomAccessFile f) throws Exception {
        if (count < 6) 
            fvisit(k, f); //in ra màn hình 
        count++;
        visited[k] = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && a[k][i] > 0) {
                depth(visited, i, f);
            }
        }
    }
    
    void depth2(int k, RandomAccessFile f) throws Exception {
        boolean[] visited = new boolean[20];
        int i;
        for (i = 0; i < n; i++) {
            visited[i] = false;
        }
        depth2(visited, k, f);
        for (i = 0; i < n; i++) {
            if (!visited[i]) {
                depth2(visited, i, f);
            }
        }
    }
}
