package pers.yibo.algorithms.fundamentals;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 背包
 *
 * @author yibo
 * @date 2021-07-27 11:25
 **/
public class Bag<T> implements Iterable<T> {

    /**
     * 起始背包元素
     */
    private Node<T> first;
    /**
     * 背包中的元素数量
     */
    private int n;

    /**
     * 初始化空背包
     */
    public Bag() {
        this.first = null;
        this.n = 0;
    }

    /**
     * 判断背包是否为空
     *
     * @return {@code true} 为空；{@code false} 非空
     */
    public boolean isEmpty() {
        return this.first == null;
    }

    /**
     * 返回背包大小
     *
     * @return 背包大小
     */
    public int size() {
        return this.n;
    }

    /**
     * 新对象添加至背包（后进先出）
     * <p>
     * old: Node1
     * <p>
     * new: Node2 -> node1
     *
     * @param t 新对象
     */
    public void add(T t) {
        Node<T> oldFirst = this.first;
        this.first = new Node<>();
        this.first.setT(t);
        this.first.setNext(oldFirst);
        n++;
    }

    /**
     * 返回一个有序链表迭代器
     *
     * @return 迭代器
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedIterator<>(this.first);
    }

    public static void main(String[] args) {
        Bag<String> bag = new Bag<>();
        bag.add("1");
        bag.add("2");
        bag.add("3");

        System.out.println(bag.size());
        for (String s : bag) {
            System.out.println(s);
        }
    }
}
