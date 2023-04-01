/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursion_NQueens;

/**
 *
 * @author DELL
 */ 
// Class for position of a queen
public class QueenPosition {
    int x, y;

    public QueenPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    // Checking whether 2 queen position are valid or not
    public boolean valid (QueenPosition p) {
        // violations in vertical (dọc), horizontal(ngang) and diagonal(đường chéo) directions
        int dx = this.x - p.x;
        if(dx<0)
            dx =-dx;
        int dy = this.y - p.y;
        if(dy<0)
            dy =-dy;
        // on the same row -> dx=0
        // same column -> dy=0
        // on the diagonal (trên cùng đường chéo -> dx= dy
        if (dx==0 || dy==0 || dx==dy) 
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "{" + x + ", " + y + '}';
    }
    
    
}
