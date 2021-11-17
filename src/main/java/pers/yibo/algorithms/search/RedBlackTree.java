package pers.yibo.algorithms.search;

/**
 * 红黑树-自平衡BST（二叉树形式表示的2-3树）- {@link java.util.TreeMap}实现原理
 * <p>
 * 1. 红链接将两个2-节点连接起来构成一个3-节点
 * 2. 黑链接则为普通2-节点
 * <p>
 * 红黑树条件
 * 1. 红链接均为左链接
 * 2. 没有任何一个节点同时连接两个红链接
 * 3. 红黑树是 完美黑平衡 的，即任意空链接到根节点的路径上黑链接数量是相同的
 * <p>
 * 根节点必定为黑色，但是执行{@link RedBlackTree#flipColors(Node)}时，可能导致根节点变红。
 * 因此每次插入都将根节点置为黑色，此时每次根节点由黑变红时，说明红黑树的黑链接数量增加了1。
 * <p>
 * 一棵大小为 N 的红黑树的高度不会超过 2lgN
 * <p>
 * 一棵大小为 N 的红黑树中，根结点到任意结点的平均路径长度为∼ 1.00lgN
 *
 * @author yibo
 * @date 2021-11-16 16:28
 **/
public class RedBlackTree<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    /**
     * 红黑树节点
     */
    private class Node {
        K key;
        V value;
        Node left, right;
        /**
         * 父节点指向该节点的链接颜色
         */
        boolean color;
        /**
         * 该节点的子树节点数
         * <p>
         * size = size ( left ) + size ( right ) + 1
         */
        int size;

        public Node(K key, V value, int size, boolean color) {
            this.key = key;
            this.value = value;
            this.size = size;
            this.color = color;
        }

        @Override
        public String toString() {
            return "{" +
                    "\"key\":" +
                    key +
                    ",\"value\":" +
                    value +
                    ",\"color\":" +
                    (color ? "RED" : "BLACK") +
                    ",\"size\":" +
                    size +
                    ",\"left\":" +
                    (left == null ? "EMPTY" : "NONEMPTY") +
                    ",\"right\":" +
                    (right == null ? "EMPTY" : "NONEMPTY") +
                    '}';
        }
    }

    /**
     * 判断该节点的父链接是否为RED
     *
     * @param node 待判断节点
     * @return 该节点的父链接是否为RED
     */
    private boolean isRed(Node node) {
        if (node == null) {
            // 空链接为黑节点
            return BLACK;
        }
        return node.color == RED;
    }


    /**
     * 获取二叉搜索树的节点数
     *
     * @return 二叉搜索树的节点数
     */
    public int size() {
        return size(root);
    }

    /**
     * 获取节点的子树节点数
     * <p>
     * 直接返回node.size会出现空指针的情况
     *
     * @param node 二叉树节点
     * @return 节点的子树节点数
     */
    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.size;
    }

    /**
     * 树是否为空
     *
     * @return true-空，false-非空
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * 判断key是否存在
     *
     * @param key 待判断key
     * @return true-存在，false-不存在
     */
    public boolean contains(K key) {
        return get(key) != null;
    }

    /**
     * 查找key对应的value，查找方法与二叉树一致
     *
     * @param key key
     * @return value，不存在则为null
     */
    public V get(K key) {
        return get(root, key);
    }

    /**
     * 递归查找
     *
     * @param node 当前查找的节点node
     * @param key  key
     * @return value
     */
    private V get(Node node, K key) {
        if (node == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare == 0) {
            return node.value;
        } else if (compare > 0) {
            return get(node.right, key);
        } else {
            return get(node.left, key);
        }
    }

    /**
     * 向红黑树中新增元素
     *
     * @param key   新增元素key值，如果已存在则覆盖
     * @param value 新增元素value值
     */
    public void put(K key, V value) {
        root = put(root, key, value);
        // 每次插入都将根节点颜色置为黑色，当根节点由红变黑时，红黑树的黑链接高度增加1
        root.color = BLACK;
    }

    /**
     * 递归查找并添加元素，并且对每个节点更新后的颜色进行判断，确保红黑树符合黑链接平衡条件
     *
     * @param node  当前节点
     * @param key   新增元素key值
     * @param value 新增元素value值
     * @return 更新后的节点
     */
    private Node put(Node node, K key, V value) {
        if (node == null) {
            // 新增节点为红链接
            return new Node(key, value, 1, RED);
        }

        /*
        与BST一致的插入方法
         */
        int compare = key.compareTo(node.key);
        if (compare > 0) {
            node.right = put(node.right, key, value);
        } else if (compare < 0) {
            node.left = put(node.left, key, value);
        } else {
            node.value = value;
        }

        // 返回平衡后的节点
        return balance(node);
    }


    /**
     * 查找二叉搜索树中的最大值，查找方法与二叉树一致
     *
     * @return 最大key
     */
    public K max() {
        return max(root).key;
    }

    /**
     * 递归查找最大节点
     *
     * @param node 当前节点
     * @return 最大节点
     */
    private Node max(Node node) {
        if (node.right == null) {
            return node;
        }
        return max(node.right);
    }

    /**
     * 查找二叉搜索树中的最小值，查找方法与二叉树一致
     *
     * @return 最大key
     */
    public K min() {
        return min(root).key;
    }

    /**
     * 递归查找最小节点
     *
     * @param node 当前节点
     * @return 最小节点
     */
    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    /**
     * 删除最小节点
     */
    private void deleteMin() {
        if (!isRed(root.left) && !isRed(root.right)) {
            // 根节点左右节点均为黑链接时，将根节点颜色设为红色（还原根节点颜色）
            root.color = RED;
        }
        root = deleteMin(root);
        if (!isEmpty()) {
            // 根节点颜色转换为黑色
            root.color = BLACK;
        }
    }

    /**
     * 递归删除最小节点
     * <p>
     * 通过{@link RedBlackTree#moveRedLeft(Node)}方法保持黑链接平衡
     * 当 node.left为黑且 node.left.left为黑或不存在（即node.left为最小节点）时，对红链接位置进行调整
     *
     * @param node 当前node
     * @return 更新后node
     */
    private Node deleteMin(Node node) {
        if (node.left == null) {
            // node左子树为空，说明node为最小节点，此时node置为null，即删除该节点
            return null;
        }

        if (!isRed(node.left) && !isRed(node.left.left)) {
            node = moveRedLeft(node);
        }
        node.left = deleteMin(node.left);
        // 重平衡node节点
        return balance(node);
    }

    /**
     * 删除最大节点
     */
    public void deleteMax() {
        if (!isRed(root.left) && !isRed(root.right)) {
            // 根节点左右节点均为黑链接时，将根节点颜色设为红色（还原根节点颜色）
            root.color = RED;
        }
        root = deleteMax(root);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    /**
     * 通过递归删除最大节点
     * <p>
     * 通过{@link RedBlackTree#moveRedRight(Node)}方法保持黑链接平衡
     * 当node.right为黑且node.right.left为黑或不存在时，对红链接位置进行调整
     * <p>
     * 当node.left为红链接时，将该节点右旋，即使得父节点指向3节点的左侧，此时右节点被移除后，可通过重平衡的方式将3节点的右侧节点调整到右节点
     *
     * @param node 当前node
     * @return 更新后node
     */
    private Node deleteMax(Node node) {
        if (isRed(node.left)) {
            // 左链接为红链接时，执行右旋
            node = rotateRight(node);
        }
        if (node.right == null) {
            return null;
        }
        if (!isRed(node.right) && !isRed(node.right.left)) {
            moveRedRight(node);
        }
        node.right = deleteMax(node.right);
        return balance(node);
    }

    /**
     * 删除指定节点
     *
     * @param key 节点key值
     */
    public void delete(K key) {
        if (!contains(key)) {
            // key不存在则直接返回
            return;
        }

        if (!isRed(root.left) && !isRed(root.right)) {
            // 根节点左右节点均为黑链接时，将根节点颜色设为红色（还原根节点颜色）
            root.color = RED;
        }
        root = delete(root, key);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    /**
     * 递归删除，判断待删除节点在左子树还是右子树，参考{@link RedBlackTree#deleteMin()}和{@link RedBlackTree#deleteMax()}的平衡策略分别进行调整
     *
     * @param node 当前节点
     * @param key  待删除节点key值
     * @return 更新后节点
     */
    private Node delete(Node node, K key) {
        if (key.compareTo(node.key) < 0) {
            // key小于当前node，则遍历左子树
            if (!isRed(node.left) && !isRed(node.left.left)) {
                // 左子树节点需要删除，则对左子树黑链接平衡
                node = moveRedLeft(node);
            }
            node.left = delete(node.left, key);
        } else {
            if (isRed(node.left)) {
                // 右子树节点需要删除，执行右旋,将父节点指向3-节点的左侧
                node = rotateRight(node);
            }
            if (key.compareTo(node.key) == 0 && node.right == null) {
                // 找到了待删除节点并且右子树为空时，说明该节点无子节点，则直接置空
                return null;
            }
            if (!isRed(node.right) && !isRed(node.right.left)) {
                // 右子树执行黑链接平衡
                node = moveRedRight(node);
            }
            if (key.compareTo(node.key) == 0) {
                // 找到了待删除节点，找到右子树的最小节点
                Node t = min(node.right);
                // 将右子树最小节点的值替换到当前节点，然后删除右子树最小节点
                node.key = t.key;
                node.value = t.value;
                node.right = deleteMin(node.right);
            } else {
                // 未找到则继续查找
                node.right = delete(node.right, key);
            }
        }
        return balance(node);
    }

    /**
     * 左旋操作，红链接在右侧时执行，即将父节点指向该节点的右子树
     * <p>
     * 更新前：
     * 5
     * 2     8
     * 1     6 9
     * <p>
     * 更新后:
     * 8
     * 5      9
     * 2 6
     * 1
     *
     * @param node 当前节点的右链接为红链接
     * @return 更新后的node
     */
    private Node rotateLeft(Node node) {
        // 记录右节点
        Node right = node.right;
        // node和right之间的节点挂在node下
        node.right = right.left;
        // node节点挂在right的左节点下
        right.left = node;
        // right的颜色为node的颜色
        right.color = node.color;
        // node的颜色置为红色
        node.color = RED;
        // right的size更新
        right.size = node.size;
        // node的size更新
        node.size = size(node.left) + size(node.right) + 1;
        // 父节点指向right
        return right;
    }

    /**
     * 右旋操作，即将父节点指向该节点的左子树，当节点的父链接和左子链接均为红色时需要执行
     * <p>
     * 更新前：
     * 8
     * 5      9
     * 2 6
     * 1
     * <p>
     * 更新后:
     * 5
     * 2     8
     * 1     6 9
     *
     * @param node 当前节点的左链接为红链接
     * @return 更新后的node
     */
    private Node rotateRight(Node node) {
        // 记录左节点
        Node left = node.left;
        // 左节点的右子树挂在node节点下
        node.left = left.right;
        // node挂在左节点的右子树下
        left.right = node;
        // 更新节点颜色
        left.color = node.color;
        node.color = RED;
        // 更新节点大小
        left.size = node.size;
        node.size = size(node.left) + size(node.right) + 1;
        // 返回left节点
        return left;
    }

    /**
     * 颜色转换
     * <p>
     * 当节点node的左右链接均为红链接并且node节点为黑链接时，将左右链接颜色转黑，并且将node的节点转红
     *
     * @param node 待转换颜色节点node
     */
    private void flipColors(Node node) {
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
        node.color = !node.color;
    }

    /**
     * node节点为红链接，node.left和node.left.left均为黑链接，则需将左子树的黑链接数-1
     * <p>
     * 1. node.right.left为红链接时，将该红链接移动到node.left.left，并且父节点指向node.right.left
     * 2. node.right.left为黑链接时，仅变换node节点颜色，此时node节点的左右子树均为红节点。
     * 删除左节点后，通过{@link RedBlackTree#balance(Node)}方法可将右子树的红节点移动到左子树中
     *
     * @param node 当前节点
     * @return 更新后节点
     */
    private Node moveRedLeft(Node node) {
        // 颜色转换
        flipColors(node);
        if (isRed(node.right.left)) {
            // 如果右子树的左节点为红链接，则将该红链接移动到左子树，此时黑链接高度不变
            // 首先右旋node.right，使node.right变为2节点
            node.right = rotateRight(node.right);
            // 然后node节点左旋，此时相当于node的父节点指向了node.right.left，并且node.right.left的红链接移动到了node.left.left
            node = rotateLeft(node);
            // 颜色转换回去
            flipColors(node);
        }
        return node;
    }

    /**
     * node节点为红链接
     * <p>
     * 1. node.left.left为红链接，此时将该红链接移动到node.right.right，并且父节点指向node.left
     * 2. node.left.left为黑链接，则仅变换node节点颜色
     * <p>
     * 变换后的节点为非平衡状态，后续可通过{@link RedBlackTree#balance(Node)}调整
     *
     * @param node 当前节点
     * @return 更新后节点
     */
    private Node moveRedRight(Node node) {
        flipColors(node);
        if (isRed(node.left.left)) {
            // 红链接仅会在左子树，因此红链接移动到右子树时，仅需执行rotateRight。
            node = rotateRight(node);
            flipColors(node);
        }
        return node;
    }

    /**
     * 重平衡节点
     *
     * @param node 待平衡节点
     * @return 更新后节点
     */
    private Node balance(Node node) {
        /*
        顺序不能交换：当node本身为红节点时，会有如下转换：1 -> 2 -> 3
         */
        // 插入完成后校验红链接
        if (isRed(node.right) && !isRed(node.left)) {
            // node右子树为红链接，左子树为黑链接，则左旋node
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            // node左子树为红链接，并且node左子树的左子树也为红链接，则右旋node
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            // node左右子树均为红链接，则颜色变换
            flipColors(node);
        }
        // 更新size
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }


    /**
     * 中序遍历，从小到大的遍历方法
     */
    private void print(Node node) {
        if (node == null) {
            return;
        }
        print(node.left);
        System.out.println(node);
        print(node.right);
    }

    /**
     * 打印 BST
     */
    public void print() {
        System.out.println("-------------RED BLACK TREE----------------");
        print(root);
        System.out.println("-----------END RED BLACK TREE--------------");
    }

    public static void main(String[] args) {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();
        tree.put(6, 6);
        tree.put(8, 8);
        tree.put(9, 9);
        tree.put(10, 10);
        tree.put(11, 11);
        tree.put(12, 12);
        tree.put(13, 13);
        tree.put(15, 15);
        tree.put(16, 16);
        tree.put(17, 17);

        tree.print();
        tree.deleteMin();
        tree.print();
        tree.deleteMax();
        tree.print();
        tree.delete(10);
        tree.print();
        tree.delete(9);
        tree.print();
    }
}
