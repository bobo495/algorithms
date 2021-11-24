package pers.yibo.algorithms.sort;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 索引优先队列 - 小顶堆
 *
 * @author yibo
 * @date 2021-11-24 17:12
 **/
public class MinIndexPriorityQueue<T extends Comparable<? super T>> extends AbstractIndexPriorityQueue<T> {

    public MinIndexPriorityQueue() {
        super();
    }

    public MinIndexPriorityQueue(int capacity) {
        super(capacity);
    }

    @Override
    int compare(int i, int j) {
        return this.items[this.priorityQueue[i]].compareTo(this.items[this.priorityQueue[j]]);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Integer> {

        private final MinIndexPriorityQueue<T> copy;

        public HeapIterator() {
            copy = new MinIndexPriorityQueue<>(priorityQueue.length - 1);
            for (int i = 1; i <= size; i++) {
                copy.put(priorityQueue[i], items[priorityQueue[i]]);
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
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return copy.pollIndex();
        }
    }

    public static void main(String[] args) {
        MinIndexPriorityQueue<String> queue = new MinIndexPriorityQueue<>();
        queue.put(2, "b");
        queue.put(1, "a");
        queue.put(5, "e");
        queue.put(4, "d");
        queue.iterator().forEachRemaining(System.out::println);
        System.out.println("======END======");

        queue.put(3, "c");
        queue.iterator().forEachRemaining(System.out::println);
        System.out.println("======END======");

        queue.put(3, "g");
        queue.iterator().forEachRemaining(System.out::println);
        System.out.println("======END======");

        queue.delete(4);
        queue.iterator().forEachRemaining(System.out::println);
        System.out.println("======END======");
    }
}
