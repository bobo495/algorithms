package pers.yibo.algorithms.leetcode;

import java.util.*;

/**
 * 397. 整数替换
 * <p>
 * 给定一个正整数n ，你可以做如下操作：
 * <p>
 * 如果n是偶数，则用n / 2替换n 。
 * 如果n是奇数，则可以用n + 1或n - 1替换n 。
 * n变为 1 所需的最小替换次数是多少？
 *
 * @author yibo
 * @date 2021-11-19 14:46
 **/
public class IntegerReplacement {

    Map<Integer, Integer> map;

    /**
     * dfs + 记忆化搜索
     */
    public int integerReplacement(int n) {
        map = new HashMap<>(16);
        return dfs(n);
    }

    public int dfs(int n) {
        if (n == 1) {
            return 0;
        }

        if (n == Integer.MAX_VALUE) {
            return 32;
        }

        if (map.containsKey(n)) {
            return map.get(n);
        }
        int step = 0;
        if (n % 2 == 0) {
            step += 1 + dfs(n / 2);
        } else {
            step += 2 + Math.min(dfs(n / 2), dfs(n / 2 + 1));
        }
        map.put(n, step);
        return step;
    }

    /**
     * HashSet替换数组
     */
    public int integerReplacement4(int n) {
        if (n == 1) {
            return 0;
        }

        if (n == Integer.MAX_VALUE) {
            return 32;
        }
        Set<Integer> next = new HashSet<>();
        next.add(n);
        return nextStep(next, 0);
    }

    public int nextStep(Set<Integer> set, int step) {
        Set<Integer> next = new HashSet<>();
        for (int a : set) {
            if (a % 2 == 0) {
                if (a == 2) {
                    return step + 1;
                }
                next.add(a / 2);
            } else {
                next.add(a + 1);
                next.add(a - 1);
            }
        }
        return nextStep(next, step + 1);
    }


    /**
     * bfs
     */
    public int integerReplacement2(int n) {

        if (n == 1) {
            return 0;
        }

        if (n == Integer.MAX_VALUE) {
            return 32;
        }

        int[] queue = new int[]{n};

        return nextStep(queue, 0, 1);
    }

    public int nextStep(int[] queue, int step, int size) {
        Set<Integer> history = new HashSet<>();
        int[] nextQueue = new int[size * 2];
        int index = 0;
        for (int i = 0; i < size; i++) {
            int a = queue[i];
            if (history.contains(a)) {
                continue;
            }
            history.add(a);
            if (a % 2 == 0) {
                if (a == 2) {
                    return step + 1;
                }
                nextQueue[index++] = a / 2;
            } else {
                nextQueue[index++] = a + 1;
                nextQueue[index++] = a - 1;
            }
        }
        System.out.println(queue.length);
        return nextStep(nextQueue, step + 1, index);
    }
}
