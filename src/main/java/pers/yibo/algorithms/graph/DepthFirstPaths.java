package pers.yibo.algorithms.graph;

import java.util.Stack;

/**
 * 深度优先搜索查找图中的路径
 *
 * @author yibo
 * @date 2021-11-18 14:23
 **/
public class DepthFirstPaths {
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

    /**
     * DFS搜索路径
     *
     * @param graph 指定图
     * @param s     指定起点
     */
    public DepthFirstPaths(Graph graph, int s) {
        marked = new boolean[graph.getVertices()];
        edgeTo = new int[graph.getVertices()];
        this.s = s;
        dfs(graph, s);
    }

    /**
     * 在由边 v-w 第一次访问任意 w 时，将 edgeTo[w] 设为 v 来 记住这条路径。
     * 换句话说， v-w 是从 s 到 w 的路径上的最后一条已知的边。这样，搜索的结果是一 棵以起点为根结点的树， edgeTo[] 是一棵由父链接表示的树。
     *
     * @param graph 指定图
     * @param v     当前搜索点
     */
    public void dfs(Graph graph, int v) {
        // 记录已经搜索过的点
        marked[v] = true;
        for (int w : graph.getAdjacencyVertices(v)) {
            if (!marked[w]) {
                // 每一条新的边，用edgeTo记录
                edgeTo[w] = v;
                // 继续搜索后续路径
                dfs(graph, w);
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
