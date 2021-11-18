package pers.yibo.algorithms.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 广度优先搜索查找图中的路径
 * <p>
 * 对于从 s 可达的任意顶点 v ，广度优先搜索都能找到一条从 s 到 v 的最短路径（没有 其他从 s 到 v 的路径所含的边比这条路径更少）。
 * <p>
 * 广度优先搜索所需的时间在最坏情况下和 V+E 成正比。
 *
 * @author yibo
 * @date 2021-11-18 14:43
 **/
public class BreadthFirstPaths {
    /**
     * 标记搜索过的点
     */
    private final boolean[] marked;
    /**
     * 反向路径记录，edgeTo[w]=v，表示v-w有路
     */
    private final int[] edgeTo;
    /**
     * 起点
     */
    private final int s;

    public BreadthFirstPaths(Graph graph, int s) {
        marked = new boolean[graph.getVertices()];
        edgeTo = new int[graph.getVertices()];
        this.s = s;

    }

    public void bfs(Graph graph, int s) {
        // 队列头为队列中到s需要步数最少的点
        Queue<Integer> queue = new LinkedList<>();
        marked[s] = true;
        queue.add(s);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : graph.getAdjacencyVertices(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.add(w);
                }
            }
        }
    }


    /**
     * 判断s-v是否有路
     *
     * @param v 目标点v
     * @return true-有路径，fals-无路径
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * 输出s-v的路径
     *
     * @param v 目标点v
     * @return 路径上所有点Iterable
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> stack = new Stack<>();
        for (int x = v; x != s; x = edgeTo[v]) {
            stack.push(x);
        }
        // 放入起点
        stack.push(s);
        return stack;
    }
}
