package com.gm.algorithm.red.black;


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
    private final Node<C> tree;

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
            // 1. 获取祖节点
            Node<C> grand = parent.getParent();
            // 2. 获取叔节点
            Node<C> uncle = grand.getUncle(parent);
            // 3. 调整树结构
            adjust(parent, grand, uncle, twig);
        }
    }

    /**
     * 调整树结构
     * @param uncle 叔节点
     * @param parent 父节点 ()
     * @param grand 祖节点
     * @param twig
     */
    private void adjust(Node<C> uncle, Node<C> parent, Node<C> grand, Node<C> twig) {
        // 叔父节点也是红色, 变色即可
        if (uncle == null || !uncle.isBlack()) {
            changeColor(grand, parent, uncle, twig);
        } else {
            // 叔父节点是黑色
            rotate(grand, parent, uncle, twig);
        }
    }

    /**
     * 旋转
     *
     * @param twig
     * @param grand  祖节点
     * @param parent 父节点
     * @param uncle  叔节点
     */
    private void rotate(Node grand, Node<C> parent, Node<C> uncle, Node<C> twig) {
        // 1. 判断父节点是否是右节点
        boolean parentRight = parent.isRight();
        // 2. 判断新节点是否右节点
        boolean twigRight = twig.isRight();
        // 父右插右: 父成祖, 祖变左, 新在右 (左旋转)
        // 父右插左: 新成祖, 祖变左, 父在右 (左旋转)
        // 父左插右: 新成祖, 祖变右, 父在左 (右旋转)
        // 父左插左: 父成祖, 祖变右, 新在右 (右旋转)
    }

    /**
     * 变色
     *  @param grand  祖节点
     * @param parent 父节点
     * @param uncle  叔节点
     * @param twig
     */
    private void changeColor(Node grand, Node<C> parent, Node uncle, Node<C> twig) {
        // 1. 变色
        parent.blackOxide();
        uncle.blackOxide();
        // 2. 判断祖父节点
        Node g1 = grand.getParent();
        if (g1 != null) {
            grand.redOxide();
        }
        // 3. 祖父也不是黑色, 需要继续调整
        if(g1!=null && !g1.isBlack()){
            adjust(g1.getUncle(grand), grand, g1, twig);
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
        Node parent = null;
        if (root != null) {
            int i = root.compareTo(twig);
            if (i > 0) {
                // 放左边
                Node left = root.getLeft();
                parent = getParent(left, twig);
            } else {
                // 放右边
                Node right = root.getRight();
                parent = getParent(right, twig);
            }
        }
        return parent;
    }
}
