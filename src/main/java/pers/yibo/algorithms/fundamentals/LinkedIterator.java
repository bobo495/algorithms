package pers.yibo.algorithms.fundamentals;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 有序链表迭代器，不支持remove元素
 *
 * @author yibo
 * @date 2021-07-29 10:30
 **/
public class LinkedIterator<T> implements Iterator<T> {
    /**
     * 当前节点
     */
    private Node<T> current;

    /**
     * 初始化迭代器
     *
     * @param first {@code Node}
     */
    public LinkedIterator(Node<T> first) {
        current = first;
    }

    /**
     * @return current不为空说明next存在
     */
    @Override
    public boolean hasNext() {
        return current != null;
    }

    /**
     * 不支持移除字段
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取current的值
     *
     * @return T
     */
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        T t = current.getT();
        this.current = current.getNext();
        return t;
    }
}