package com.gm.algorithm.red.black;

import java.util.ArrayList;
import java.util.List;

/**
 * RedBlackApplication.
 *
 * @author : Jason.lee
 * @version : 1.0
 */
public class RedBlackApplication {


    /**
     * 效验红黑树算法
     * @param args
     */
    public static void main(String[] args) {
        RedBlack rb = new RedBlack(100);
        rb.insert(200);
        rb.insert(50);
        rb.insert(25);
        rb.insert(80);
        rb.insert(40);
        rb.insert(45);
        toListString(rb.getTree());
    }







    private static void toListString(Node node) {
        System.out.println("红色: ");
        List all = toList(node);
        all.stream().forEach(x -> {
            System.out.print("  ");
            System.out.println(x);
        });
    }
    private static List toList(Node node) {
        List all = new ArrayList();
        if(node==null){
            return all;
        }
        if(!node.isBlack()){
            all.add(node.getC());
        }
        Node left = node.getLeft();
        Node right = node.getRight();
        toLr(all, left, right);
        return all;
    }

    private static void toLr(List all, Node left, Node right) {
        all.addAll(toList(left));
        all.addAll(toList(right));
    }
}
