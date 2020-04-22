package com.gm.algorithm.red.black;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 红黑树算法类.
 *
 * @author : Jason.lee
 * @version : 1.0
 */
public class RedBlack<C extends Comparable> {
    /**
     * 根节点
     */
    private Node<C> tree;

    public Node<C> getTree() {
        return tree;
    }

    /**
     * 初始化根节点
     * 此时只有一个根节点还不是二叉树
     *
     * @param root
     */
    public RedBlack(C root) {
        this.tree = Node.root(root);
    }

    /**
     * 插入
     */
    public void insert(C c) {
        // 1. 构建末节点
        Node<C> twig = Node.twig(c);
        // 2. 获取父节点
        Node<C> parent = getParent(tree, twig);
        // 3. 重编树结构
        refactor(parent, twig);
        // 4. 修复根节点
        repairRoot();
    }

    private void repairRoot() {
        // 根节点没有父节点
        if (tree.getParent() != null) {
            tree.setParent(null);
        }
        // 根节点必须是黑色
        if (!tree.isBlack()) {
            tree.blackOxide();
        }
    }


    /**
     * 检查树结构
     *
     * @param parent 父节点
     * @param twig   末节点
     */
    private void refactor(Node<C> parent, Node<C> twig) {
        if (parent.isBlack()) {
            // 不用修改树结构, 直接追加到末尾
            append(parent, twig);
        } else {
            // 调整树结构
            adjust(parent, twig);
        }
    }

    /**
     * 调整树结构
     *  @param parent 父节点
     * @param twig   新节点
     */
    private void adjust(Node<C> parent, Node<C> twig) {
        // 1. 获取祖节点
        Node<C> grand = parent.getParent();
        // 2. 获取叔节点
        Node<C> uncle = grand.getUncle(parent);
        // 叔父节点也是红色, 变色即可
        if (uncle == null || !uncle.isBlack()) {
            changeColor(uncle, parent, grand, twig);
        } else {
            // 叔父节点是黑色
            rotate(uncle, parent, grand, twig);
        }
    }

    /**
     * 旋转
     *
     * @param uncle  叔节点
     * @param parent 父节点
     * @param grand  祖节点
     * @param twig
     */
    private void rotate(Node<C> uncle, Node<C> parent, Node<C> grand, Node<C> twig) {
        // 1. 判断父节点是否是右节点
        boolean parentRight = parent.isRight();
        // 2. 判断新节点是否右节点
        boolean twigRight = twig.isRight();
        // 父右插右: 父成祖, 祖变左, 新在右 (左旋转)
        if (parentRight && twigRight) {
            rightRight(uncle, parent, grand, twig);
        }
        // 父右插左: 新成祖, 祖变左, 父在右 (左旋转)
        else if (parentRight && !twigRight) {
            rightLeft(uncle, parent, grand, twig);
        }
        // 父左插右: 新成祖, 祖变右, 父在左 (右旋转)
        else if (!parentRight && twigRight) {
            leftRight(uncle, parent, grand, twig);
        }
        // 父左插左: 父成祖, 祖变右, 新在左 (右旋转)
        else if (!parentRight && !twigRight) {
            leftLeft(uncle, parent, grand, twig);
        }
    }

    private void changeRoot(Node<C>... ns) {
        for (Node<C> n : ns) {
            if (n.getParent() == null) {
                tree = n;
            }
        }
    }

    @SuppressWarnings("all")
    private void leftLeft(Node<C> uncle, Node<C> parent, Node<C> grand, Node<C> twig) {
        // 父成祖
        Node grandParent = null;
        if (grand != null) {
            grandParent = grand.getParent();
            if (grandParent != null) {
                grandParent.setChild(parent);
            }
        }
        parent.setParent(grandParent);
        // 祖变右
        parent.setRight(grand);
        grand.setParent(parent);
        // 新在左
        parent.setLeft(twig);
        twig.setParent(parent);
        // 变色
        turnColors(parent, grand);
        // 判断是否继续调整
        keep(parent);
    }

    @SuppressWarnings("all")
    private void leftRight(Node<C> uncle, Node<C> parent, Node<C> grand, Node<C> twig) {
        // 新成祖
        Node grandParent = null;
        if (grand != null) {
            grandParent = grand.getParent();
            if (grandParent != null) {
                grandParent.setChild(twig);
            }
        }
        twig.setParent(grandParent);
        // 祖变右
        twig.setRight(grand);
        grand.setParent(twig);
        // 父在左
        twig.setLeft(parent);
        parent.setParent(twig);
        // 变色
        turnColors(twig, grand);
        // 判断是否继续调整
        keep(twig);
    }

    @SuppressWarnings("all")
    private void rightLeft(Node<C> uncle, Node<C> parent, Node<C> grand, Node<C> twig) {
        // 新成祖
        Node grandParent = null;
        if (grand != null) {
            grandParent = grand.getParent();
            if (grandParent != null) {
                grandParent.setChild(twig);
            }
        }
        twig.setParent(grandParent);
        // 祖变左
        twig.setLeft(grand);
        grand.setParent(twig);
        // 父在右
        twig.setRight(parent);
        parent.setParent(twig);
        // 变色
        turnColors(twig, grand);
        // 判断是否继续调整
        keep(twig);
    }

    @SuppressWarnings("all")
    private void rightRight(Node<C> uncle, Node<C> parent, Node<C> grand, Node<C> twig) {
        // 父成祖
        Node grandParent = null;
        if (grand != null) {
            grandParent = grand.getParent();
            if (grandParent != null) {
                grandParent.setChild(parent);
            }
        }
        parent.setParent(grandParent);
        // 祖变左
        parent.setLeft(grand);
        grand.setParent(parent);
        // 新在右
        parent.setRight(twig);
        twig.setParent(parent);
        // 变色
        turnColors(parent, grand);
        // 判断是否继续调整
        keep(parent);
    }

    /**
     * 检查是否继续
     *
     * @param twig
     */
    private void keep(Node<C> twig) {
        Node parent = twig.getParent();
        Node grand = parent.getParent();
        if(grand!=null) {
            adjust(parent, twig);
        }else {
            // 父节点是根节点
            rotateRoot(parent, twig);
        }
    }

    private void rotateRoot(Node<C> root, Node<C> twig) {
        boolean twigRight = twig.isRight();
        if(twigRight) {
            leftRoot(root);
        }
        else if(!twigRight){
            rightRoot(root);
        }
    }

    private void leftRoot(Node<C> root) {
        Node right = root.getRight();
        Node rightLeft = right.getLeft();
        right.setParent(null);
        right.setLeft(root);
        root.setParent(right);
        root.setRight(rightLeft);
    }

    private void rightRoot(Node<C> root) {
        Node left = root.getLeft();
        Node leftRight = left.getRight();
        left.setParent(null);
        left.setRight(root);
        root.setParent(left);
        root.setLeft(leftRight);
    }

    /**
     * 变色
     */
    private void turnColors(Node<C>... ns) {
        for (Node<C> n : ns) {
            if (n.isBlack()) {
                n.redOxide();
            } else {
                n.blackOxide();
            }
        }
    }

    /**
     * 变色
     *  @param uncle  叔节点
     * @param parent 父节点
     * @param grand  祖节点
     * @param twig
     */
    private void changeColor(Node uncle, Node<C> parent, Node<C> grand, Node<C> twig) {
        twig.setParent(parent);
        parent.setChild(twig);
        // 1. 变色
        parent.blackOxide();
        uncle.blackOxide();
        // 2. 判断祖父节点
        grand.redOxide();
        Node g1 = grand.getParent();
        // 3. 祖父也不是黑色, 需要继续调整
        if(g1==null){
            rotateRoot(grand, twig);
        }else if (!g1.isBlack()) {
            adjust(grand, twig);
        }
    }

    private void append(Node<C> parent, Node<C> twig) {
        twig.setParent(parent);
        parent.setChild(twig);
    }


    /**
     * 根据新结点/末节点查找父节点
     *
     * @param root 根节点
     * @param twig 末节点
     * @return 父节点
     */
    public Node<C> getParent(Node root, Node twig) {
        Node<C> parent;
        int i = root.getC().compareTo(twig.getC());
        if (i > 0) {
            // 放左边
            Node left = root.getLeft();
            if (left == null) {
                return root;
            }
            parent = getParent(left, twig);
        } else {
            // 放右边
            Node right = root.getRight();
            if (right == null) {
                return root;
            }
            parent = getParent(right, twig);
        }
        return parent;
    }



    @Override
    public String toString() {
        return tree.toString();
    }

    private String getBlank(Integer count) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<count; i++){
            sb.append(" ");
        }
        return sb.toString();
    }
}
