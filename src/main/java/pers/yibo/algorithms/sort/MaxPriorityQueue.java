package pers.yibo.algorithms.sort;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 基于堆的优先队列 - 大顶堆
 * <p>
 * 继承{@link AbstractPriorityQueue}，重写{@link AbstractPriorityQueue#compare(int, int)}方法，实现队首为最大元素
 *
 * @author yibo
 * @date 2021-11-11 11:41
 **/
public class MaxPriorityQueue<T extends Comparable<? super T>> extends AbstractPriorityQueue<T> {

    public MaxPriorityQueue() {
        super();
    }

    public MaxPriorityQueue(int capacity) {
        super(capacity);
    }

    public MaxPriorityQueue(T[] array) {
        super(array);
    }

    @Override
    int compare(int i, int j) {
        return this.priorityQueue[j].compareTo(this.priorityQueue[i]);
    }


    @Override
    public Iterator<T> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<T> {
        private final MaxPriorityQueue<T> copy;

        public HeapIterator() {
            copy = new MaxPriorityQueue<>(size());
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
        MaxPriorityQueue<Integer> queue = new MaxPriorityQueue<>(nums);

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
