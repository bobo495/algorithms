package pers.yibo.algorithms.string;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 基于三向单词查找树的符号表
 *
 * @author yibo
 * 2021/12/19 17:22
 */
public class TernarySearchTrie<V> {
    /**
     * 根节点，默认value为空
     */
    private Node<V> root;

    /**
     * 单词查找树的大小
     */
    private int size;

    private static class Node<V> {
        /**
         * 当前节点的字符
         */
        char c;
        /**
         * 字符串相关联的值，不存在时为null
         */
        V value;
        /**
         * 三向单词树子树
         * <p>
         * left: 下一个字符小于c
         * mid: 下一个字符等于c
         * right: 下一个字符大于c
         */
        Node<V> left, mid, right;
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
            return null;
        }


        int compare = Integer.compare(key.charAt(d), node.c);
        if (compare > 0) {
            return get(node.right, key, d);
        }
        if (compare < 0) {
            return get(node.left, key, d);
        }
        //相等时，说明找到了d对应的字符
        if (d == key.length() - 1) {
            return node;
        }
        return get(node.mid, key, d + 1);
    }


    /**
     * 找到Node且value非空说明ST中包含key
     *
     * @param key 待查找字符串
     * @return true-包含，false-不包含
     */
    private boolean contains(String key) {
        return get(key) != null;
    }

    public void put(String key, V value) {
        root = put(root, key, value, 0);
    }

    private Node<V> put(Node<V> node, String key, V value, int d) {
        char c = key.charAt(d);
        if (node == null) {
            node = new Node<>();
            node.c = c;

        }
        if (d == key.length() - 1) {
            if (node.value == null) {
                size++;
            }
            node.value = value;
            // value为空时，则是删除该key
            if (value == null) {
                size--;
            }
            return node;
        }
        int compare = Integer.compare(key.charAt(d), node.c);
        if (compare > 0) {
            node.right = put(node.right, key, value, d);
        } else if (compare < 0) {
            node.left = put(node.left, key, value, d);
        } else {
            node.mid = put(node.mid, key, value, d + 1);
        }
        return node;
    }


    /**
     * 遍历ST的所有key
     *
     * @return ST的所有key
     */
    public Iterable<String> keys() {
        Queue<String> queue = new LinkedList<>();
        collect(root, new StringBuilder(), queue);
        return queue;
    }

    /**
     * 找到以prefix开头的所有key
     *
     * @param prefix 指定前缀
     * @return 以prefix开头的所有key
     */
    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> queue = new LinkedList<>();
        Node<V> node = get(root, prefix, 0);
        if (node == null) {
            return queue;
        }
        if (node.value != null) {
            queue.add(prefix);
        }
        collect(node.mid, new StringBuilder(prefix), queue);
        return queue;
    }

    private void collect(Node<V> node, StringBuilder prefix, Queue<String> queue) {
        if (node == null) {
            return;
        }

        collect(node.left, prefix, queue);

        if (node.value != null) {
            queue.add(prefix.toString() + node.c);
        }
        collect(node.mid, prefix.append(node.c), queue);
        // 删除node.c
        prefix.deleteCharAt(prefix.length() - 1);
        collect(node.right, prefix, queue);
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
        collect(root, new StringBuilder(), 0, pattern, queue);
        return queue;
    }

    private void collect(Node<V> node, StringBuilder prefix, int i, String pattern, Queue<String> queue) {
        if (node == null) {
            return;
        }
        char c = pattern.charAt(i);
        if (c == '.' || c < node.c) {
            collect(node.left, prefix, i, pattern, queue);
        }
        if (c == '.' || c == node.c) {
            if (i == pattern.length() - 1 && node.value != null) {
                queue.add(prefix.toString() + node.c);
            }
            if (i < pattern.length() - 1) {
                collect(node.mid, prefix.append(node.c), i + 1, pattern, queue);
                // 删除node.c
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
        if (c == '.' || c > node.c) {
            collect(node.right, prefix, i, pattern, queue);
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
        if (s.length() == 0) {
            return null;
        }
        int length = 0;
        Node<V> node = root;
        // s的下标
        int i = 0;
        while (node != null && i < s.length()) {
            int compare = Integer.compare(s.charAt(i), node.c);
            if (compare > 0) {
                node = node.right;
            } else if (compare < 0) {
                node = node.left;
            } else {
                i++;
                if (node.value != null) {
                    length = i;
                }
                node = node.mid;
            }
        }
        return s.substring(0, length);
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
        put(key, null);
    }

    public static void main(String[] args) {
        TernarySearchTrie<Integer> searchTree = new TernarySearchTrie<>();
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
        System.out.println("test contains: " + searchTree.contains("short"));
        System.out.println("test contains: " + searchTree.contains("the"));
        System.out.println("test contains: " + searchTree.contains("anda"));
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
