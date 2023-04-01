/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bst_demo01;

/**
 *
 * @author DELL
 */
public class BST_Node {
    AquariumFish fish;       // basic structure in a BST
    BST_Node left, right;

    public BST_Node(AquariumFish fish) {
        this.fish = fish;
        left = right = null;
    }
    
    
}
