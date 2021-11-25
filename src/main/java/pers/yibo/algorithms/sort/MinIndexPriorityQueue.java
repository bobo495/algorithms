package pers.yibo.algorithms.sort;

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
    MinIndexPriorityQueue<T> copy() {
        MinIndexPriorityQueue<T> copy = new MinIndexPriorityQueue<>(this.priorityQueue.length - 1);
        for (int i = 1; i <= size; i++) {
            copy.put(this.priorityQueue[i], this.items[priorityQueue[i]]);
        }
        return copy;
    }

    public static void main(String[] args) {
        MinIndexPriorityQueue<String> queue = new MinIndexPriorityQueue<>();
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
