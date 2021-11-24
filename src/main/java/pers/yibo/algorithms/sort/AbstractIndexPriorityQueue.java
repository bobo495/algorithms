package pers.yibo.algorithms.sort;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * 索引优先队列
 * <p>
 * 固定容量，不能修改大小
 *
 * @author yibo
 * @date 2021-11-24 15:28
 **/
abstract class AbstractIndexPriorityQueue<T extends Comparable<? super T>> implements Iterable<Integer> {
    /**
     * 默认初始容量为16
     */
    protected static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    /**
     * 优先队列的索引下标
     */
    protected int[] priorityQueue;

    /**
     * 优先队列逆序，reverse[priorityQueue[i]]=priorityQueue[reverse[i]]=i
     */
    protected int[] reverse;

    /**
     * 索引对应的值列表，位置始终保持不变
     */
    protected T[] items;

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
    public AbstractIndexPriorityQueue() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * 初始化队列
     *
     * @param capacity 队列容量
     */
    @SuppressWarnings("unchecked")
    public AbstractIndexPriorityQueue(int capacity) {
        this.priorityQueue = new int[capacity + 1];
        this.items = (T[]) Array.newInstance(Comparable.class, capacity + 1);
        this.capacity = capacity;
        this.reverse = new int[capacity + 1];
        // 初始化reverse为-1，用于确定索引是否存在
        Arrays.fill(this.reverse, -1);
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
     * 索引是否存在
     *
     * @param index 索引
     * @return true-存在，false-不存在
     */
    public boolean contains(int index) {
        validateIndex(index);
        return this.reverse[index] != -1;
    }

    /**
     * 获取索引对应的值
     *
     * @param index 索引
     * @return 对应的值
     */
    public T get(int index) {
        if (!contains(index)) {
            throw new NoSuchElementException("index is not in the priority queue");
        }
        return this.items[index];
    }

    /**
     * 新增或替换数据
     *
     * @param index 索引
     * @param item  元素
     */
    public void put(int index, T item) {
        validateIndex(index);
        if (contains(index)) {
            this.items[index] = item;
            // 更新后，对该元素进行上浮/下沉校验
            swim(this.reverse[index]);
            sink(this.reverse[index]);
            return;
        }
        this.items[index] = item;
        this.reverse[index] = ++this.size;
        this.priorityQueue[this.size] = index;
        swim(this.size);
    }

    /**
     * 根据索引删除数据
     *
     * @param index 索引
     */
    public void delete(int index) {
        if (!contains(index)) {
            throw new NoSuchElementException("index is not in the priority queue");
        }
        // 获取索引在优先队列中的位置
        int indexQueue = this.reverse[index];
        // 将该索引放到队列尾
        exchange(indexQueue, this.size--);
        // 调整该节点位置
        swim(indexQueue);
        sink(indexQueue);
        // 删除数据
        this.items[index] = null;
        this.reverse[index] = -1;
    }

    /**
     * 获取优先队列的第一个元素(不删除)
     *
     * @return this.priorityQueue[1]
     */
    public int peakIndex() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        return this.priorityQueue[1];
    }

    /**
     * 获取优先队列的第一个元素(不删除)
     *
     * @return this.items[this.priorityQueue[1]]
     */
    public T peakItem() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        return this.items[this.priorityQueue[1]];
    }

    /**
     * 弹出优先队列的第一个元素对应的索引（删除）
     *
     * @return this.priorityQueue[1]
     */
    public int pollIndex() {
        int firstIndex = this.priorityQueue[1];
        poll();
        return firstIndex;
    }

    /**
     * 弹出优先队列的第一个元素（删除）
     *
     * @return this.items[this.priorityQueue[1]]
     */
    public T pollItem() {
        T firstItem = this.items[this.priorityQueue[1]];
        poll();
        return firstItem;
    }

    /**
     * 弹出优先队列的第一个元素（删除）
     */
    private void poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        // 记录队列中的最小值
        this.items[this.priorityQueue[1]] = null;
        // reverse置为-1
        this.reverse[this.priorityQueue[1]] = -1;
        // 将最大值换到队尾，并减少队列size
        exchange(1, size--);
        // 将此时的队首元素下沉
        sink(1);
    }


    /**
     * 队尾元素上浮到正确位置
     */
    private void swim(int index) {
        while (index > 1 && compare(index / 2, index) > 0) {
            // 父节点和当前节点不符合compare顺序
            exchange(index, index / 2);
            index = index / 2;
        }
    }

    /**
     * 队首元素下沉到正确位置
     */
    private void sink(int index) {
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
     * 交换优先队列下标，并对应更新逆序下标
     *
     * @param i 队列中的值
     * @param j 队列中的值
     */
    private void exchange(int i, int j) {
        int tmp = this.priorityQueue[i];
        this.priorityQueue[i] = this.priorityQueue[j];
        this.priorityQueue[j] = tmp;
        this.reverse[this.priorityQueue[i]] = i;
        this.reverse[this.priorityQueue[j]] = j;
    }

    /**
     * 比较优先队列中的两个元素大小
     *
     * @param i 优先队列下标
     * @param j 优先队列下标
     * @return 大小
     */
    abstract int compare(int i, int j);

    /**
     * 有效索引校验
     *
     * @param index 索引
     */
    private void validateIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index is negative: " + index);
        }
        if (index >= this.capacity) {
            throw new IllegalArgumentException("index >= capacity: " + index);
        }
    }
}
