package pers.yibo.algorithms.sort;

import java.util.Iterator;

/**
 * 索引优先队列接口
 *
 * @author yibo
 * @date 2021-11-25 09:33
 **/
public interface IndexPriorityQueue<T extends Comparable<? super T>> {
    /**
     * 判断队列是否为空
     *
     * @return true-empty
     */
    boolean isEmpty();

    /**
     * 获取优先队列大小
     *
     * @return 优先队列大小
     */
    int size();

    /**
     * 索引是否存在
     *
     * @param index 索引
     * @return true-存在，false-不存在
     */
    boolean contains(int index);

    /**
     * 获取索引对应的值
     *
     * @param index 索引
     * @return 对应的值
     */
    T get(int index);

    /**
     * 新增或替换数据
     *
     * @param index 索引
     * @param item  元素
     */
    void put(int index, T item);

    /**
     * 根据索引删除数据
     *
     * @param index 索引
     */
    void delete(int index);

    /**
     * 获取优先队列的第一个元素的索引(不删除)
     *
     * @return this.priorityQueue[1]
     */
    int peakIndex();

    /**
     * 获取优先队列的第一个元素(不删除)
     *
     * @return this.items[this.priorityQueue[1]]
     */
    T peakItem();

    /**
     * 弹出优先队列的第一个元素，返回对应的索引（删除）
     *
     * @return this.priorityQueue[1]
     */
    int pollIndex();

    /**
     * 弹出优先队列的第一个元素，返回对应的元素值（删除）
     *
     * @return this.items[this.priorityQueue[1]]
     */
    T pollItem();

    /**
     * 按顺序遍历索引列表
     *
     * @return 索引列表
     */
    Iterator<Integer> getIndices();

    /**
     * 按顺序遍历元素的值列表
     *
     * @return 值列表
     */
    Iterator<T> getItems();
}
