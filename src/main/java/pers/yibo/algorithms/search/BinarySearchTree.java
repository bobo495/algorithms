package pers.yibo.algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉查找树
 * <p>
 * 保证节点的左子树key均小于节点key，节点的右子树key均大于节点key
 * <p>
 * 在由 N 个随机键构造的二叉查找树中， 查找命中平均所需的比较次数为∼ 2lnN
 * <p>
 * 在一棵二叉查找树中，所有操作在最坏情况下所需的时间都和树的高度成正比。
 *
 * @author yibo
 * @date 2021-11-16 11:09
 **/
public class BinarySearchTree<K extends Comparable<K>, V> {
    /**
     * 二叉树节点
     */
    private class Node {
        K key;
        V value;
        Node left, right;
        /**
         * 该节点的子树节点数
         * <p>
         * size = size ( left ) + size ( right ) + 1
         */
        int size;

        public Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }

        @Override
        public String toString() {
            return "{" +
                    "\"key\":" +
                    key +
                    ",\"value\":" +
                    value +
                    ",\"size\":" +
                    size +
                    '}';
        }
    }

    /**
     * 二叉搜索树的根节点
     */
    private Node root;

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
     * 查找key对应的value
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
     * 插入元素key,value
     *
     * @param key   key
     * @param value value
     */
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    /**
     * 递归查找插入元素的位置，并且重新计算每个节点对应的size
     *
     * @param node  当前查找的节点
     * @param key   key
     * @param value value
     * @return 更新后的节点
     */
    private Node put(Node node, K key, V value) {
        if (node == null) {
            // 新节点插入到二叉树中
            return new Node(key, value, 1);
        }

        int compare = key.compareTo(node.key);
        if (compare > 0) {
            node.right = put(node.right, key, value);
        } else if (compare < 0) {
            node.left = put(node.left, key, value);
        } else {
            // key值相等时，更新value
            node.value = value;
        }
        // 重新计算size
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * 查找二叉搜索树中的最大值
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
     * 查找二叉搜索树中的最小值
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
     * 向下取整
     *
     * @param key 目标key
     * @return 小于等于key的最大值
     */
    public K floor(K key) {
        Node node = floor(root, key);
        if (node == null) {
            return null;
        }
        return node.key;
    }

    /**
     * 递归查找向下取整的Node
     *
     * @param node 当前node
     * @param key  key
     * @return 小于等于key值的最大节点
     */
    private Node floor(Node node, K key) {
        if (node == null) {
            // 没有小于等于key的值
            return null;
        }

        int compare = key.compareTo(node.key);
        if (compare == 0) {
            return node;
        }

        if (compare < 0) {
            // key小于node节点，说明目标key在左子树
            return floor(node.left, key);
        }
        // 当前节点小于key，则递归查找右子树，如果右子树有符合条件的值，则取该值，否则取node
        Node t = floor(node.right, key);
        if (t != null) {
            return t;
        }
        // 没有比node更大的数字，则直接返回node
        return node;
    }

    public K ceiling(K key) {
        Node node = ceiling(root, key);
        if (node == null) {
            return null;
        }
        return node.key;
    }

    /**
     * 递归查找向上取整的节点
     *
     * @param node 节点
     * @param key  目标key
     * @return 大于等于key的最小值
     */
    private Node ceiling(Node node, K key) {
        if (node == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare == 0) {
            return node;
        }
        if (compare > 0) {
            // key大于node节点，说明结果在右子树
            return ceiling(node.right, key);
        }
        // key小于node节点，此时检索左子树，如果左子树还有符合条件的值，则取该值
        Node t = ceiling(node.left, key);
        if (t != null) {
            return t;
        }
        return node;
    }

    /**
     * 返回排名为k的节点（小于目标节点的节点有k个）
     *
     * @param k 排名
     * @return 从0开始第k小的key
     */
    public K select(int k) {
        Node node = select(root, k);
        if (node == null) {
            return null;
        }
        return node.key;
    }


    private Node select(Node node, int k) {
        if (node == null) {
            return null;
        }
        // 比当前节点小的节点数量
        int leftSize = size(node.left);
        if (leftSize > k) {
            // 小于当前节点的数字多余k个，继续检索左子树
            return select(node.left, k);
        }
        if (leftSize < k) {
            // 小于当前节点的数字少于k个，检索右子树，并去掉 leftSize 和 当前node
            return select(node.right, k - leftSize - 1);
        }
        // 正好为k个则返回当前node
        return node;
    }

    /**
     * 查找key在二叉搜索树中的排名
     *
     * @param key 待查找key
     * @return key在二叉搜索树中的排名
     */
    public int rank(K key) {
        return rank(root, key);
    }

    /**
     * 递归查找以node为根节点的二叉树中，小于key的node数量
     *
     * @param node 当前node
     * @param key  待查找key
     * @return 小于key的node数量
     */
    private int rank(Node node, K key) {
        if (node == null) {
            // 比key小的节点不存在
            return 0;
        }
        int compare = key.compareTo(node.key);
        if (compare == 0) {
            // 找到了key值，则左子树数量为排名
            return size(node.left);
        }
        if (compare < 0) {
            // key小于当前节点，则小于key的节点均在左子树
            return rank(node.left, key);
        }
        // key大于当前节点，则从右子树继续查找是否右比key小的数字
        return size(node.left) + 1 + rank(node.right, key);
    }

    /**
     * 删除二叉搜索树中的最小节点
     */
    public void deleteMin() {
        // 更新root
        root = deleteMin(root);
    }

    /**
     * 递归查找最小节点，找到后用node.right替换node.left
     * <p>
     * 情况1: 1-2-3 ，遍历到node 1时，1的左右节点均为null，此时结果为null-2-3
     * 情况2：null-2-3，遍历到node 2时，2的左节点为null，此时2的父节点会指向2的右节点3
     *
     * @param node 递归查找的当前节点
     * @return 节点更新后的状态
     */
    private Node deleteMin(Node node) {
        if (node.left == null) {
            // 左节点为空时，返回右节点
            return node.right;
        }
        // 左节点存在，则从左节点删除一个节点
        node.left = deleteMin(node.left);
        // 重新计算size
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * 删除二叉搜索树中的最大节点
     */
    public void deleteMax() {
        root = deleteMax(root);
    }

    /**
     * 递归查找最大节点，找到后用node.right替换node.left
     *
     * @param node 当前节点
     * @return 更新后的节点
     */
    private Node deleteMax(Node node) {
        if (node.right == null) {
            // 右节点为空时，返回左节点
            return node.left;
        }
        // 右节点存在，则继续查找右节点
        node.right = deleteMax(node.right);
        node.size = size(node.right) + size(node.left) + 1;
        return node;
    }

    /**
     * 删除指定节点
     *
     * @param key 待删除节点的key
     */
    public void delete(K key) {
        root = delete(root, key);
    }

    /**
     * 递归查找并删除指定节点
     *
     * @param node 当前node
     * @param key  待删除节点
     * @return 更新后的节点
     */
    private Node delete(Node node, K key) {
        if (node == null) {
            // 当前节点为null，说明未找到目标节点，则不做操作
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare > 0) {
            // 待删除节点在右子树
            node.right = delete(node.right, key);
        } else if (compare < 0) {
            // 待删除节点在左子树
            node.left = delete(node.left, key);
        } else {
            // 找到了待删除节点
            if (node.left == null) {
                // 左子树为空，则直接用右子树替换当前节点
                return node.right;
            }
            if (node.right == null) {
                // 同上
                return node.left;
            }
            // 临时节点，用来记录左右子树的指针
            Node t = node;
            // 将当前节点替换为右子树的最小节点
            node = min(node.right);
            // 删除右子树的最小节点，并将node.right指向更新后的右子树
            node.right = deleteMin(node.right);
            // node.left指向t的左子树
            node.left = t.left;
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
        System.out.println("-----------BINARY SEARCH TREE--------------");
        print(root);
        System.out.println("---------END BINARY SEARCH TREE------------");
    }

    /**
     * 全局查找，并返回所有节点的key
     *
     * @return BST的key Iterable
     */
    public Iterable<K> keys() {
        return keys(min(), max());
    }

    /**
     * 范围查找，返回区间内的节点key
     *
     * @param min 最小key
     * @param max 最大key
     * @return BST的key Iterable
     */
    public Iterable<K> keys(K min, K max) {
        Queue<K> queue = new LinkedList<>();
        keys(root, queue, min, max);
        return queue;
    }

    /**
     * 中序遍历方法，递归查找，低值添加到队列头，高值添加到队列尾
     *
     * @param node  递归的当前node
     * @param queue 从小到大排序的FIFO队列
     * @param low   低值
     * @param high  高值
     */
    public void keys(Node node, Queue<K> queue, K low, K high) {
        if (node == null) {
            return;
        }
        int compareLow = low.compareTo(node.key);
        int compareHigh = high.compareTo(node.key);
        if (compareLow < 0) {
            // 低值小于当前节点，则递归添加左子树中的值
            keys(node.left, queue, low, high);
        }

        if (compareLow <= 0 && compareHigh >= 0) {
            // 低值小于等于当前节点，高值大于等于当前节点，说明node符合条件
            queue.add(node.key);
        }

        if (compareHigh > 0) {
            // 高值大于当前节点，则递归添加右子树中的值
            keys(node.right, queue, low, high);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>();
        tree.put(10, 10);
        tree.put(5, 5);
        tree.put(12, 12);
        tree.put(9, 9);
        tree.put(6, 6);
        tree.put(15, 15);
        tree.put(20, 20);

        tree.print();

        tree.keys(8, 12).forEach(System.out::println);

        tree.delete(9);
        tree.print();

        tree.deleteMax();
        tree.print();

        System.out.println(tree.get(tree.select(2)));
        System.out.println(tree.select(10));
        System.out.println(tree.rank(12));
    }
}
