package pers.yibo.algorithms.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 最小优先队列
 *
 * @author yibo
 * @date 2021-11-23 16:50
 **/
public class MinPriorityQueue<T extends Comparable<? super T>> {
    /**
     * 默认初始容量为16
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    /**
     * 数组形式，基于堆的完全二叉树
     */
    private T[] priorityQueue;
    /**
     * 队列长度
     */
    private int size = 0;
    /**
     * 队列容量，数组长度-1
     */
    private int capacity;

    /**
     * 默认队列长度为16
     */
    public MinPriorityQueue() {
        this(DEFAULT_INITIAL_CAPACITY);
    }


    /**
     * 初始化队列
     *
     * @param capacity 队列容量
     */
    @SuppressWarnings("unchecked")
    public MinPriorityQueue(int capacity) {
        this.priorityQueue = (T[]) Array.newInstance(Comparable.class, capacity + 1);
        this.capacity = capacity;
    }


    /**
     * 根据数组初始化优先队列
     *
     * @param array 数组
     */
    @SuppressWarnings("unchecked")
    public MinPriorityQueue(T[] array) {
        this.priorityQueue = (T[]) Array.newInstance(Comparable.class, array.length + 1);
        this.capacity = array.length;
        for (T item : array) {
            insert(item);
        }
    }

    /**
     * 判断队列是否为空
     *
     * @return true-empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 插入元素item，并上浮至正确位置
     *
     * @param item 新元素
     */
    public void insert(T item) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        this.priorityQueue[++size] = item;
        // 上浮元素
        swim();
    }

    /**
     * 删除队列中值最小的元素
     *
     * @return this.priorityQueue[1]
     */
    public T delete() {
        // 记录队列中的最小值
        T min = this.priorityQueue[1];
        // 将最大值换到队尾，并减少队列size
        this.priorityQueue[1] = this.priorityQueue[size--];
        // 将此时的队首元素下沉
        sink();
        if (size > 0 && size < capacity / 4) {
            resize(capacity / 2);
        }
        return min;
    }

    /**
     * 队尾元素上浮到正确位置
     */
    private void swim() {
        int index = size;
        while (index > 1 && priorityQueue[index / 2].compareTo(priorityQueue[index]) > 0) {
            exchange(index, index / 2);
            index = index / 2;
        }
    }

    /**
     * 队首元素下沉到正确位置
     */
    private void sink() {
        int index = 1;
        while (index * 2 <= size) {
            int child = index * 2;
            // 找到更小的孩子
            if (child < size && priorityQueue[child].compareTo(priorityQueue[child + 1]) > 0) {
                child++;
            }
            if (priorityQueue[index].compareTo(priorityQueue[child]) < 0) {
                // 当前位置比child都小，说明下沉到了正确位置，退出循环
                break;
            }
            exchange(index, child);
            index = child;
        }
    }


    /**
     * 交换数组中的元素
     *
     * @param i 队列中的值
     * @param j 队列中的值
     */
    private void exchange(int i, int j) {
        T tmp = this.priorityQueue[i];
        this.priorityQueue[i] = this.priorityQueue[j];
        this.priorityQueue[j] = tmp;
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        T[] copyPriorityQueue = (T[]) Array.newInstance(Comparable.class, capacity + 1);
        if (size >= 0) {
            System.arraycopy(this.priorityQueue, 1, copyPriorityQueue, 1, size);
        }
        this.priorityQueue = copyPriorityQueue;
        this.capacity = capacity;
    }


    @Override
    public String toString() {
        return Arrays.toString(this.priorityQueue);
    }

    public static void main(String[] args) {
        Integer[] nums = new Integer[]{1, 5, 7, 3, 9, 4, 8, 5, 6, 2};
        MinPriorityQueue<Integer> queue = new MinPriorityQueue<>(nums);
        System.out.println(queue);
        queue.insert(6);
        System.out.println(queue);
        System.out.println(queue.delete());
        System.out.println(queue);
        System.out.println(queue.delete());
        System.out.println(queue.delete());
        System.out.println(queue.delete());
        System.out.println(queue.delete());
        System.out.println(queue.delete());
        System.out.println(queue.delete());
        System.out.println(queue);
        System.out.println(queue.delete());
        System.out.println(queue.delete());
        System.out.println(queue.delete());
        System.out.println(queue.delete());
    }
}
