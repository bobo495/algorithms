package pers.yibo.algorithms.sort;

/**
 * 索引优先队列 - 大顶堆
 *
 * @author yibo
 * @date 2021-11-24 17:12
 **/
public class MaxIndexPriorityQueue<T extends Comparable<? super T>> extends AbstractIndexPriorityQueue<T> {

    public MaxIndexPriorityQueue() {
        super();
    }

    public MaxIndexPriorityQueue(int capacity) {
        super(capacity);
    }

    @Override
    int compare(int i, int j) {
        return this.items[this.priorityQueue[j]].compareTo(this.items[this.priorityQueue[i]]);
    }

    @Override
    MaxIndexPriorityQueue<T> copy() {
        MaxIndexPriorityQueue<T> copy = new MaxIndexPriorityQueue<>(this.priorityQueue.length - 1);
        for (int i = 1; i <= size; i++) {
            copy.put(this.priorityQueue[i], this.items[priorityQueue[i]]);
        }
        return copy;
    }

    public static void main(String[] args) {
        MaxIndexPriorityQueue<String> queue = new MaxIndexPriorityQueue<>();
        queue.put(2, "b");
        queue.put(1, "a");
        queue.put(5, "e");
        queue.put(4, "d");
        queue.getIndices().forEachRemaining(System.out::println);
        queue.getItems().forEachRemaining(System.out::println);
        System.out.println("======END======");

        queue.put(3, "c");
        queue.getItems().forEachRemaining(System.out::println);
        System.out.println("======END======");

        queue.put(3, "g");
        queue.getItems().forEachRemaining(System.out::println);
        System.out.println("======END======");

        queue.delete(4);
        queue.getItems().forEachRemaining(System.out::println);
        System.out.println("======END======");
    }
}
