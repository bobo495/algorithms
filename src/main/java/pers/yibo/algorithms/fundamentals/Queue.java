package pers.yibo.algorithms.fundamentals;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 队列
 *
 * @author yibo
 * @date 2021-07-27 11:25
 **/
public class Queue<T> implements Iterable<T> {

    /**
     * 最先进队列的元素
     */
    private Node<T> first;
    /**
     * 最后进队列的元素
     */
    private Node<T> last;
    /**
     * 队列长度
     */
    private int n;

    /**
     * 初始化队列
     */
    public Queue() {
        this.first = null;
        this.last = null;
        this.n = 0;
    }

    /**
     * 判断是否为空
     *
     * @return {@code true} 为空，{@code false} 非空
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * 返回队列大小
     *
     * @return 队列大小
     */
    public int size() {
        return n;
    }

    /**
     * 返回队列中最前的元素
     *
     * @return 队列中最前的元素
     */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        return first.getT();
    }

    /**
     * 入队
     *
     * @param t object
     */
    public void enqueue(T t) {
        Node<T> oldLast = this.last;
        this.last = new Node<>();
        last.setT(t);
        if (isEmpty()) {
            this.first = this.last;
        } else {
            oldLast.setNext(this.last);
        }
        n++;
    }

    /**
     * 出队
     * <p>
     * 返回队列最前的元素，并且队列-1
     *
     * @return 队列最前的元素
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        T t = this.first.getT();
        this.first = this.first.getNext();
        n--;
        if (isEmpty()) {
            last = null;
        }
        return t;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (T t : this) {
            s.append(t);
            s.append(" ");
        }
        return s.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedIterator<>(this.first);
    }

    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");

        System.out.println(queue);
        while (!queue.isEmpty()) {
            System.out.println(queue.peek() + "\t" + queue.dequeue() + "\t" + queue.size());
        }
        System.out.println(queue);
    }
}
