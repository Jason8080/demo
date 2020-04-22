package com.gm.algorithm.red.black;

public class Node<C extends Comparable>{
    // 父节点
    private Node parent;
    // 普通节点
    private Node left;
    private C c;
    private Node right;
    // 颜色说明: 红1 黑0
    private int cColor = 1;

    // 构建根节点
    public static <C extends Comparable> Node root(C c) {
        Node<C> root = twig(c);
        root.blackOxide();
        return root;
    }
    // 构建末节点
    public static <C extends Comparable> Node twig(C c) {
        Node<C> root = new Node<C>(null, c, null);
        return root;
    }

    // 构建普通节点
    public Node(Node left, C c, Node right) {
        this.left = left;
        this.c = c;
        this.right = right;
    }

    // 获取内容
    public C getC() {
        return c;
    }

    public Node getParent() {
        return parent;
    }

    public boolean isBlack() {
        return cColor==0;
    }

    public boolean isRight() {
        Node left = parent.getLeft();
        if(this != left){
            return true;
        }
        return false;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setChild(Node twig) {
        int i = c.compareTo((C) twig.c);
        if (i > 0){
            // 放左边
            this.setLeft(twig);
        }else {
            // 放右边
            this.setRight(twig);
        }
    }

    /**
     * 根据父节点获取叔父节点
     * @param parent 父节点
     * @param <C> 内容泛型
     * @return 叔父节点
     */
    public <C extends Comparable> Node getUncle(Node<C> parent) {
        if(this.right.c == parent.c){
            return this.left;
        }
        return this.right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    // 黑化: 将节点变黑
    public void blackOxide() {
        this.cColor = 0;
    }
    // 红化: 将节点变红
    public void redOxide() {
        this.cColor = 1;
    }

    @Override
    public String toString() {
        String leftStr = left!=null?left.getC() + "(" + (left.isBlack() ? "黑" : "红") + ")":"";
        String rightStr = right!=null?right.getC() + "(" + (right.isBlack() ? "黑" : "红") + ")":"";
        return "Node{" +
                "left=" + leftStr +
                ", c=" + c + "(" + (isBlack() ? "黑" : "红") + ")" +
                ", right=" + rightStr +
                '}';
    }


}