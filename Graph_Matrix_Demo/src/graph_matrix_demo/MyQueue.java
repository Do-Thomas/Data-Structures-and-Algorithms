/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph_matrix_demo;

import java.util.LinkedList;

/**
 *
 * @author DELL
 */
public class MyQueue {
    LinkedList<Integer> t;
    MyQueue() {
        t = new LinkedList();
        }
    boolean isEmpty() {
        return (t.isEmpty());
    }
    void enqueue(int x) {
        t.add(x);
    }
    int dequeue() {
        return (t.removeFirst());
    }
}
