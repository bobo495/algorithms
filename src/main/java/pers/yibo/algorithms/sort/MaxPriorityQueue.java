package pers.yibo.algorithms.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 基于堆的优先队列
 * <p>
 * 思想：基于数组构建完全二叉树。保证二叉树的父节点永远大于子节点，不保证两个子节点的大小。
 * 每次添加元素时，从队尾添加，通过{@link MaxPriorityQueue#swim()}实现队尾元素上浮到正确位置；
 * 每次删除元素时，取出队首元素，然后用队尾元素替换队首元素，并执行{@link MaxPriorityQueue#sink()}实现队首元素下沉到正确位置。
 * <p>
 * 用下沉操作由 N 个元素构造堆只需少于 2 N 次比较以及少于 N 次交换。
 *
 * @author yibo
 * @date 2021-11-11 11:41
 **/
public class MaxPriorityQueue<T extends Comparable<? super T>> {
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
    public MaxPriorityQueue() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * 初始化队列
     *
     * @param capacity 队列容量
     */
    @SuppressWarnings("unchecked")
    public MaxPriorityQueue(int capacity) {
        this.priorityQueue = (T[]) Array.newInstance(Comparable.class, capacity + 1);
        this.capacity = capacity;
    }

    /**
     * 根据数组初始化优先队列
     *
     * @param array 数组
     */
    @SuppressWarnings("unchecked")
    public MaxPriorityQueue(T[] array) {
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
     * 删除队列中值最大的元素
     *
     * @return this.priorityQueue[1]
     */
    public T delete() {
        // 记录队列中的最大值
        T max = this.priorityQueue[1];
        // 将最大值换到队尾，并减少队列size
        this.priorityQueue[1] = this.priorityQueue[size--];
        // 将此时的队首元素下沉
        sink();
        if (size > 0 && size < capacity / 4) {
            resize(capacity / 2);
        }
        return max;
    }

    /**
     * 队尾元素上浮到正确位置
     */
    private void swim() {
        int index = size;
        while (index > 1 && this.priorityQueue[index / 2].compareTo(this.priorityQueue[index]) < 0) {
            // 父节点<子节点，则交换节点元素
            exchange(index / 2, index);
            index = index / 2;
        }
    }

    /**
     * 队首元素下沉到正确位置
     */
    private void sink() {
        int index = 1;
        while (index * 2 <= size) {
            // 比较两个孩子大小，确认下沉位置
            int childIndex = index * 2;
            if (childIndex < size && this.priorityQueue[childIndex].compareTo(this.priorityQueue[childIndex + 1]) < 0) {
                // 两个孩子均存在，且第二个孩子更大，则与第二个孩子交换
                childIndex++;
            }
            if (this.priorityQueue[childIndex].compareTo(this.priorityQueue[index]) < 0) {
                // 更大的孩子值 < 当前节点值，说明已下沉到正确位置
                break;
            }
            exchange(index, childIndex);
            // index更新至孩子位置
            index = childIndex;
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
        MaxPriorityQueue<Integer> queue = new MaxPriorityQueue<>(nums);
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
    }
}
