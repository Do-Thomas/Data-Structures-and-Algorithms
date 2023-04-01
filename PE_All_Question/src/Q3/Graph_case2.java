
package Q3;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Stack;


public class Graph_case2 {
    //f2: Dijkstra's shortest path
    //1. Find the shortest path from vertext 2 -> 5, then vertex 0 (A) -> 6(G)
    // - A B C E D G
    
    //2. The 4 vertices selected into the set S.
    // - E D F G
    
    //3. Vertices in shortest path.
    //- C E D F
    int n;
    int[][] a;
    
    
    void dijkstra(ArrayList<Integer> path, ArrayList<Integer> setS, int fro, int to, RandomAccessFile f) throws Exception {
        int INF = 99;
        boolean[] S = new boolean[n];
        int[] d = new int[n];
        int[] p = new int[n];
        int i, j, k, t;
        for (i = 0; i < n; i++) {
            S[i] = false;
            d[i] = a[fro][i];
            p[i] = fro;
        }

        S[fro] = true;

        while (true) {
            t = INF;
            k = -1;
            for (i = 0; i < n; i++) {
                if (S[i]) {
                    continue;// Đỉnh nào được chọn thì bỏ qua
                }
                if (d[i] < t) {//nếu khoảng cách nhỏ hơn thì up lại
                    t = d[i];
                    k = i;
                }
            }
            if (k == -1) {
                return;
            }
            S[k] = true;
            setS.add(k);
            if (k == to) {
                break;//gặp đỉnh đích
            }//update d[i] and p[i
            // update data
            for (i = 0; i < n; i++) {
                if (S[i]) {
                    continue;
                }
                if (d[i] > d[k] + a[k][i]) {
                    d[i] = d[k] + a[k][i];
                    p[i] = k;
                }
            }
        }
        Stack s = new Stack();
        i = to;
        while (true) {
            s.push(i);
            if (i == fro) {
                break;
            }
            i = p[i];
        }
        i = s.pop();
        path.add(i);
        while (!s.isEmpty()) {
            i = s.pop();
            path.add(i);
        }
    }
    
    void f2() {
        ArrayList<Integer> path1 = new ArrayList<>();
        ArrayList<Integer> setS1 = new ArrayList<>();
        ArrayList<Integer> path2 = new ArrayList<>();
        ArrayList<Integer> setS2 = new ArrayList<>();
        dijkstra(path1, setS1, 2, 5, f);
        dijkstra(path2, setS2, 0, 6, f);
        for (int i = 0; i < path2.size(); i++) {
            fvisit(path2.get(i), f);
        }

        f.writeBytes("\r\n");
        for (int i = setS2.size() - 4; i < setS2.size(); i++) {
            fvisit(setS2.get(i), f);
        }

        f.writeBytes("\r\n");
        for (int i = 0; i < path1.size(); i++) {
            fvisit(path1.get(i), f);
        }
        //f2: diijkstra(0,6)
    //to = 6, fro = 0;
    //void f2
    }
}
