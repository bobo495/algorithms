package pers.yibo.algorithms.fundamentals;

/**
 * 链表节点
 *
 * @param <T> Object
 * @author yibo
 * @date 2021-07-27 14:24
 **/
public class Node<T> {
    private T t;
    private Node<T> next;

    public Node() {
        this.t = null;
        this.next = null;
    }

    public void setT(T t) {
        this.t = t;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public T getT() {
        return t;
    }

    public Node<T> getNext() {
        return next;
    }
}