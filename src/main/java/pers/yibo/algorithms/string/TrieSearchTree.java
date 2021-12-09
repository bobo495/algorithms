package pers.yibo.algorithms.string;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 单词查找树
 * <p>
 * 键值对：key为单词，value为单词对应的值
 * <p>
 * 单词查找树的链表结构和键的插入/删除顺序无关，对任意给定的一组键，单词查找树唯一
 * <p>
 * 单词查找树居中查找一个键或插入一个键时，访问数组的次数最多为键的长度+1
 * <p>
 * 字母表大小为R时，在一棵由N个随机键构成的单词查找树中，未命中查找平均所需检查的节点数量为~logR N
 * <p>
 * 一棵单词查找树中的链接总数在RN到RNw之间，w为键的平均长度
 *
 * @author yibo
 * 2021/12/9 20:36
 */
public class TrieSearchTree<V> {
    /**
     * 字母表基数
     */
    private final static int RADIX = 256;
    Node<V> root;
    int size = 0;

    @SuppressWarnings("unchecked")
    private static class Node<V> {
        V value;
        Node<V>[] next = new Node[RADIX];

    }

    /**
     * ST是否为空
     *
     * @return true-空，false-非空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * ST大小
     *
     * @return ST大小
     */
    public int size() {
        return size;
    }

    /**
     * 查找key在ST中的value
     *
     * @param key 待查找字符串
     * @return null-未找到，V-key对应的value
     */
    public V get(String key) {
        Node<V> node = get(root, key, 0);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    /**
     * 递归查找
     * <p>
     * root节点为空。d=0时，未开始匹配字符，仅开始查找下一个字符所在节点。d=1时，对应查找的为第一个字符。
     *
     * @param node 当前节点
     * @param key  待查找字符串
     * @param d    下一个查找的字符
     * @return null-未找到，Node-key对应的节点
     */
    private Node<V> get(Node<V> node, String key, int d) {
        if (node == null) {
            // 空节点，返回为找到
            return null;
        }

        if (d == key.length()) {
            // s所有字符均已匹配
            return node;
        }

        // 递归查找下一个字符
        return get(node.next[key.charAt(d)], key, d + 1);
    }

    /**
     * 找到Node且value非空说明ST中包含key
     *
     * @param key 待查找字符串
     * @return true-包含，false-不包含
     */
    private boolean contains(String key) {
        Node<V> node = get(root, key, 0);
        if (node == null) {
            return false;
        }
        return node.value != null;
    }

    public void put(String key, V value) {
        root = put(root, key, value, 0);
    }

    /**
     * 递归更新或新建节点
     *
     * @param node  当前节点
     * @param key   待put key
     * @param value 待put value
     * @param d     当前递归字符索引
     * @return 更新后的Node
     */
    private Node<V> put(Node<V> node, String key, V value, int d) {
        if (node == null) {
            node = new Node<>();
        }
        if (d == key.length()) {
            // node.value本身为空才增加size
            if (node.value == null) {
                size++;
            }
            node.value = value;
            return node;
        }
        node.next[key.charAt(d)] = put(node.next[key.charAt(d)], key, value, d + 1);
        return node;
    }

    /**
     * 遍历ST的所有key
     *
     * @return ST的所有key
     */
    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    /**
     * 找到以prefix开头的所有key
     *
     * @param prefix 指定前缀
     * @return 以prefix开头的所有key
     */
    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> queue = new LinkedList<>();
        // 找到prefix对应的node，以该node为root节点开始查找
        collect(get(root, prefix, 0), prefix, queue);
        return queue;
    }

    private void collect(Node<V> node, String prefix, Queue<String> queue) {
        if (node == null) {
            return;
        }
        // 找到有value非空的Node，此时prefix为该节点对应Key
        if (node.value != null) {
            queue.add(prefix);
        }
        // 遍历所有子树
        for (char c = 0; c < RADIX; c++) {
            collect(node.next[c], prefix + c, queue);
        }
    }

    /**
     * 找到符合通配符的所有key
     * <p>
     * 通配符为'.'，可匹配任意字符。不支持其他正则，不支持转义
     *
     * @param pattern 通配符
     * @return 符合通配符的所有key
     */
    public Iterable<String> keysThatMatch(String pattern) {
        Queue<String> queue = new LinkedList<>();
        collect(root, "", pattern, queue);
        return queue;
    }


    private void collect(Node<V> node, String prefix, String pattern, Queue<String> queue) {
        if (node == null) {
            return;
        }
        int d = prefix.length();
        if (d == pattern.length() && node.value != null) {
            queue.add(prefix);
            return;
        }
        if (d == pattern.length()) {
            return;
        }
        char next = pattern.charAt(d);
        for (char c = 0; c < RADIX; c++) {
            if (next == '.' || c == next) {
                collect(node.next[c], prefix + c, pattern, queue);
            }
        }
    }

    /**
     * 查找给定字符串的最长键前缀
     * <p>
     * 例如：给定字符串为abcdef，ST中有key abcde，则最长键前缀为5
     *
     * @param s 给定字符串
     * @return 最长键前缀的长度
     */
    public String longestPrefixOf(String s) {
        return s.substring(0, search(root, s, 0, 0));
    }

    private int search(Node<V> node, String s, int d, int length) {
        if (node == null) {
            return length;
        }
        if (node.value != null) {
            length = d;
        }
        // 查找完成
        if (d == s.length()) {
            return length;
        }
        return search(node.next[s.charAt(d)], s, d + 1, length);
    }

    /**
     * 删除key，同时删除多余的节点
     * <p>
     * 例如：删除key shell，ST中有key she，但是无其他key，则同时删除节点ll
     * <p>
     * 删除key she，ST中有key shell，则仅删除she对应的value，不删除节点
     *
     * @param key 待删除key
     */
    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node<V> delete(Node<V> node, String key, int d) {
        // 未找到key
        if (node == null) {
            return null;
        }
        if (d == key.length()) {
            // 找到key则size--
            if (node.value != null) {
                size--;
            }
            // 将该节点value置为null
            node.value = null;
        } else {
            node.next[key.charAt(d)] = delete(node.next[key.charAt(d)], key, d + 1);
        }

        // 对key所在分支的子树进行校验，如果子树value非空，则直接返回node
        if (node.value != null) {
            return node;
        }
        // 遍历node的所有子节点，如存在非空子节点，则返回node
        for (int c = 0; c < RADIX; c++) {
            if (node.next != null) {
                return node;
            }
        }
        // 子树value为空且所有子节点为空，则将该节点置为null
        return null;
    }

    public static void main(String[] args) {
        TrieSearchTree<Integer> searchTree = new TrieSearchTree<>();
        // 测试put
        searchTree.put("shell", 1);
        searchTree.put("shells", 2);
        searchTree.put("she", 3);
        searchTree.put("short", 4);
        searchTree.put("bye", 5);
        searchTree.put("by", 6);
        searchTree.put("the", 7);
        searchTree.put("and", 8);
        searchTree.put("sea", 9);
        // 覆盖值
        searchTree.put("shell", 10);
        // 测试get
        System.out.println("test get: " + searchTree.get("shell"));
        System.out.println("test get: " + searchTree.get("shel"));
        System.out.println("test get: " + searchTree.get("ael"));
        System.out.println("========================");
        // 输出size和所有key
        System.out.println("test size:" + searchTree.size());
        System.out.println("All keys: " + searchTree.keys());
        System.out.println("========================");
        // 测试查找
        System.out.println("keysWithPrefix: " + searchTree.keysWithPrefix("she"));
        System.out.println("keysWithPrefix:" + searchTree.keysWithPrefix("by"));
        System.out.println("keysThatMatch: " + searchTree.keysThatMatch(".he"));
        System.out.println("longestPrefixOf: " + searchTree.longestPrefixOf("byebye"));
        System.out.println("========================");
        // 测试删除
        System.out.println("get she: " + searchTree.get("she"));
        searchTree.delete("she");
        System.out.println("delete she: " + searchTree.get("she"));
        System.out.println("get short: " + searchTree.get("short"));
        searchTree.delete("short");
        System.out.println("delete short: " + searchTree.get("short"));

    }
}
