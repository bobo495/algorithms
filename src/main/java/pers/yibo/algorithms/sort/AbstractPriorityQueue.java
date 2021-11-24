package pers.yibo.algorithms.sort;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * 优先队列抽象类，除比较方法外，给出具体方法
 * <p>
 * 思想：基于数组构建完全二叉树。保证二叉树的父节点永远大于子节点，不保证两个子节点的大小。
 * 每次添加元素时，从队尾添加，通过{@link AbstractPriorityQueue#swim()}实现队尾元素上浮到正确位置；
 * 每次删除元素时，取出队首元素，然后用队尾元素替换队首元素，并执行{@link AbstractPriorityQueue#sink()}实现队首元素下沉到正确位置。
 * <p>
 * 用下沉操作由 N 个元素构造堆只需少于 2 N 次比较以及少于 N 次交换。
 *
 * @author yibo
 * @date 2021-11-23 16:50
 **/
abstract class AbstractPriorityQueue<T extends Comparable<? super T>> implements Iterable<T> {
    /**
     * 默认初始容量为16
     */
    protected static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    /**
     * 数组形式，基于堆的完全二叉树
     */
    protected T[] priorityQueue;
    /**
     * 队列长度
     */
    protected int size = 0;
    /**
     * 队列容量，数组长度-1
     */
    protected int capacity;

    /**
     * 默认队列长度为16
     */
    public AbstractPriorityQueue() {
        this(DEFAULT_INITIAL_CAPACITY);
    }


    /**
     * 初始化队列
     *
     * @param capacity 队列容量
     */
    @SuppressWarnings("unchecked")
    public AbstractPriorityQueue(int capacity) {
        this.priorityQueue = (T[]) Array.newInstance(Comparable.class, capacity + 1);
        this.capacity = capacity;
    }


    /**
     * 根据数组初始化优先队列
     *
     * @param array 数组
     */
    @SuppressWarnings("unchecked")
    public AbstractPriorityQueue(T[] array) {
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
     * 获取优先队列大小
     *
     * @return 优先队列大小
     */
    public int size() {
        return this.size;
    }

    /**
     * 插入元素item，并上浮至正确位置
     *
     * @param item 新元素
     */
    public void insert(T item) {
        if (this.size == this.capacity) {
            resize(this.capacity * 2);
        }
        this.priorityQueue[++size] = item;
        // 上浮元素
        swim();
    }

    /**
     * 获取优先队列的第一个元素(不删除)
     *
     * @return this.priorityQueue[1]
     */
    public T peak() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        return this.priorityQueue[1];
    }

    /**
     * 弹出优先队列的第一个元素（删除）
     *
     * @return this.priorityQueue[1]
     */
    public T poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        // 记录队列中的最小值
        T first = this.priorityQueue[1];
        // 将最大值换到队尾，并减少队列size
        this.priorityQueue[1] = this.priorityQueue[size--];
        // 将此时的队首元素下沉
        sink();
        if (this.size > 0 && this.size < this.capacity / 4) {
            resize(this.capacity / 2);
        }
        return first;
    }

    /**
     * 队尾元素上浮到正确位置
     */
    private void swim() {
        int index = size;
        while (index > 1 && compare(index / 2, index) > 0) {
            // 父节点和当前节点不符合compare顺序
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
            if (child < size && compare(child + 1, child) < 0) {
                child++;
            }
            if (compare(index, child) < 0) {
                // index和两个child均符合compare方法，说明下沉到了正确位置，退出循环
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

    /**
     * 比较优先队列中的两个元素大小
     *
     * @param i 优先队列下标
     * @param j 优先队列下标
     * @return 大小
     */
    abstract int compare(int i, int j);

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
}
