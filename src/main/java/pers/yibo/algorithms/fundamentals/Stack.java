package pers.yibo.algorithms.fundamentals;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 栈
 *
 * @author yibo
 * @date 2021-07-27 11:25
 **/
public class Stack<T> implements Iterable<T> {
    /**
     * 最后入栈的元素
     */
    private Node<T> top;
    /**
     * 栈长度
     */
    private int n;

    public Stack() {
        this.top = null;
        this.n = 0;
    }

    /**
     * 判断是否为空
     *
     * @return {@code true} 为空，{@code false} 非空
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * 返回栈大小
     *
     * @return 栈大小
     */
    public int size() {
        return n;
    }

    public void push(T t) {
        Node<T> oldLast = this.top;
        this.top = new Node<>();
        this.top.setT(t);
        this.top.setNext(oldLast);
        n++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        T t = this.top.getT();
        this.top = this.top.getNext();
        n--;
        return t;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        return this.top.getT();
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
        return new LinkedIterator<>(top);
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");

        System.out.println(stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.peek() + "\t" + stack.pop() + "\t" + stack.size());
        }
        System.out.println(stack);
    }
}
