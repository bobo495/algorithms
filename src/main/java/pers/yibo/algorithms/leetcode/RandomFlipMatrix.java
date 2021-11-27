package pers.yibo.algorithms.leetcode;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 519. 随机翻转矩阵
 *
 * @author aoyb
 * 2021/11/27 17:07
 */
public class RandomFlipMatrix {

    Bag[] matrix;
    int m;
    int n;
    Random random;

    class Bag {
        Set<Integer> bag;
        int size;
        int n;

        public Bag(int n) {
            bag = new HashSet<>();
            this.n = n;
        }

        public boolean full() {
            return size == n;
        }

        public int add(int y) {
            while (bag.contains(y)) {
                y = (y + 1) % n;
            }
            bag.add(y);
            size++;
            return y;
        }

    }

    public RandomFlipMatrix(int m, int n) {
        this.matrix = new Bag[m];
        for (int i = 0; i < m; i++) {
            this.matrix[i] = new Bag(n);
        }
        this.m = m;
        this.n = n;
        this.random = new Random();
    }

    public int[] flip() {
        int[] p = new int[]{random.nextInt(m), random.nextInt(n)};
        while (matrix[p[0]].full()) {
            p[0] = (p[0] + 1) % m;
        }
        p[1] = matrix[p[0]].add(p[1]);
        return p;
    }

    public void reset() {
        for (int i = 0; i < m; i++) {
            this.matrix[i] = new Bag(n);
        }
    }

    public static void main(String[] args) {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(r.nextInt(2));
        }
    }

}
