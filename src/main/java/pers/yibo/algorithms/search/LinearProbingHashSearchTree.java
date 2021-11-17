package pers.yibo.algorithms.search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 基于线性探测的搜索树 - {@link java.util.HashMap}实现原理
 * <p>
 * 实现散列表的另一种方式就是用大小为 M 的数组保存 N 个键值对（M > N ）
 * 我们需要依靠 数组中的空位解决碰撞冲突。基于这种策略的所有方法被统称为开放地址散列表。
 * <p>
 * 开放地址散列表中最简单的方法叫做线性探测法：当碰撞发生时（当一个键的散列值已经被另 一个不同的键占用），我们直接检查散列表中的下一个位置（将索引值加 1）。
 * 这样的线性探测可能会产生三种结果：
 * <p>
 * 1. 命中，该位置的键和被查找的键相同；
 * <p>
 * 2. 未命中，键为空（该位置没有键）；
 * <p>
 * 3. 继续查找，该位置的键和被查找的键不同。
 * <p>
 * 我们用散列函数找到键在数组中的索引，检查其中的键和被查找的键是否相同。如果不同则继 续查找（将索引增大，到达数组结尾时折回数组的开头），直到找到该键或者遇到一个空元素。
 * <p>
 * 为了保证性能，我们会动态调整数组的大小来保证使用率在 1/8 到 1/2 之间。
 *
 * @author yibo
 * @date 2021-11-17 15:27
 **/
public class LinearProbingHashSearchTree<K, V> {
    /**
     * 探测表默认容量为16
     */
    private final static int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    /**
     * 符号表中键值对的总数
     */
    private int size;

    /**
     * 线性探测表的容量
     */
    private int capacity;

    /**
     * 最小阈值
     */
    private int minThreshold;
    /**
     * 最大阈值
     */
    private int maxThreshold;

    private K[] keys;
    private V[] values;

    /**
     * 使用 DEFAULT_INITIAL_CAPACITY 初始化探测表
     */
    public LinearProbingHashSearchTree() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * 初始化探测表，容量指定为 capacity
     *
     * @param capacity 探测表容量
     */
    @SuppressWarnings("unchecked")
    public LinearProbingHashSearchTree(int capacity) {
        this.capacity = capacity;
        this.minThreshold = capacity / 8;
        this.maxThreshold = capacity / 2;
        keys = (K[]) new Object[capacity];
        values = (V[]) new Object[capacity];
    }

    /**
     * 增加元素
     *
     * @param key   key
     * @param value value
     */
    public void put(K key, V value) {
        if (this.size >= this.maxThreshold) {
            // 当已有键值对超过容量的一半时，对探测表扩容，每次扩容将容量翻倍
            resize(capacity * 2);
        }
        int index;
        // 逐个遍历从hash(key)开始的连续非空元素，找到相同的则替换value，否则找到空元素为止
        for (index = hash(key); keys[index] != null; index = (index + 1) % capacity) {
            if (keys[index].equals(key)) {
                // 如果找到了key相等的键值对，则更新value并返回
                values[index] = value;
                return;
            }
        }
        // 按照线性探测方法找到空的位置，将新的键值对插入到该位置
        keys[index] = key;
        values[index] = value;
        size++;
    }

    /**
     * 获取value
     *
     * @param key 目标key
     * @return key对应value
     */
    public V get(K key) {
        // 逐个遍历从hash(key)开始的连续非空元素，找到相同的key则返回对应的value
        for (int i = hash(key); keys[i] != null; i = (i + 1) % capacity) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        // 未找到key，返回null
        return null;
    }

    /**
     * 探测表中是否包含key
     *
     * @param key 目标key
     * @return true-存在，false-不存在
     */
    public boolean contains(K key) {
        return get(key) != null;
    }

    /**
     * 删除指定键值对
     * <p>
     * 若key存在，则其会在一段连续的非空子序列中，假设删除了子序列中间的元素，则后续元素会断开，
     * 无法从hash(key)索引到，因此后续的元素需要重新put一次
     *
     * @param key 待删除元素
     */
    public void delete(K key) {
        if (!contains(key)) {
            return;
        }
        int index = hash(key);
        // 搜索key值所在的index
        while (!keys[index].equals(key)) {
            index = (index + 1) % capacity;
        }
        // index对应键值对置空
        keys[index] = null;
        values[index] = null;
        size--;

        // 遍历剩余子序列，即index后连续的非空序列，重新put这些元素（先置空后put）
        index = (index + 1) % capacity;
        while (keys[index] != null) {
            K tmpKey = keys[index];
            V tmpValue = values[index];

            keys[index] = null;
            values[index] = null;
            size--;

            put(tmpKey, tmpValue);
            index = (index + 1) % capacity;
        }

        // 当剩余size仅有容量的1/8时，将探测表容量减半，减少未命中的概率
        if (size > 0 && size == this.minThreshold) {
            resize(capacity / 2);
        }
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    private void resize(int capacity) {
        LinearProbingHashSearchTree<K, V> searchTree = new LinearProbingHashSearchTree<>(capacity);
        for (int i = 0; i < this.capacity; i++) {
            if (keys[i] != null) {
                searchTree.put(keys[i], values[i]);
            }
        }
        // 更新容量和键值对
        this.keys = searchTree.keys;
        this.values = searchTree.values;
        this.capacity = capacity;
        this.minThreshold = capacity / 8;
        this.maxThreshold = capacity / 2;
    }

    /**
     * 遍历keys
     *
     * @return keys迭代器
     */
    public Iterable<K> keys() {
        Queue<K> queue = new LinkedList<>();
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null) {
                queue.add(keys[i]);
            }
        }
        return queue;
    }

    public void print() {
        System.out.println("---------------SEARCH TREE------------------");
        System.out.println("size: " + size + " , capacity: " + capacity);
        keys().forEach(key -> System.out.println("key: " + key + " , value: " + get(key)));
        System.out.println("-------------END SEARCH TREE----------------");
    }

    public static void main(String[] args) {
        LinearProbingHashSearchTree<Integer, Integer> searchTree = new LinearProbingHashSearchTree<>(16);
        searchTree.put(1, 1);
        searchTree.put(2, 2);
        searchTree.put(3, 3);
        searchTree.put(4, 4);
        searchTree.put(5, 5);
        searchTree.print();

        searchTree.put(6, 6);
        searchTree.put(7, 7);
        searchTree.put(8, 8);
        searchTree.put(9, 9);
        searchTree.print();

        searchTree.delete(9);
        searchTree.delete(8);
        searchTree.delete(7);
        searchTree.delete(6);
        searchTree.delete(5);
        searchTree.print();
    }
}
