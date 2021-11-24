package pers.yibo.algorithms.sort;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 基于堆的优先队列 - 小顶堆
 * <p>
 * 继承{@link AbstractPriorityQueue}，重写{@link AbstractPriorityQueue#compare(int, int)}方法，实现队首为最小元素
 *
 * @author yibo
 * @date 2021-11-23 16:50
 **/
public class MinPriorityQueue<T extends Comparable<? super T>> extends AbstractPriorityQueue<T> {

    public MinPriorityQueue() {
        super();
    }

    public MinPriorityQueue(int capacity) {
        super(capacity);
    }

    public MinPriorityQueue(T[] array) {
        super(array);
    }

    @Override
    int compare(int i, int j) {
        return this.priorityQueue[i].compareTo(this.priorityQueue[j]);
    }


    @Override
    public Iterator<T> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<T> {
        private final MinPriorityQueue<T> copy;

        public HeapIterator() {
            copy = new MinPriorityQueue<>(size());
            for (int i = 1; i <= size; i++) {
                copy.insert(priorityQueue[i]);
            }
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return copy.poll();
        }
    }

    public static void main(String[] args) {
        Integer[] nums = new Integer[]{1, 5, 7, 3, 9, 4, 8, 5, 6, 2};
        MinPriorityQueue<Integer> queue = new MinPriorityQueue<>(nums);

        System.out.println("迭代器测试：");
        queue.iterator().forEachRemaining(System.out::println);

        System.out.println("队列功能测试：");
        System.out.println(queue);
        queue.insert(6);
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.peak());
        System.out.println(queue.peak());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}
