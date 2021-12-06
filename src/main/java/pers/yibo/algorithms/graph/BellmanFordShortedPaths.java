package pers.yibo.algorithms.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 基于队列的Bellman-Ford 算法，最短路径树查找 + 负权重边的负权重环检测
 * <p>
 * 当且仅当加权有向图中至少存在一条从 s 到 v 的有向路径
 * 且所有从 s 到 v 的有向路径 上的任意顶点都不存在于任何负权重环中时， s 到 v 的最短路径才是存在的。
 * <p>
 * 在任意含有 V 个顶点的加权有向图中给定起点 s ，从 s 无法到 达任何负权重环，
 * 以下算法能够解决其中的单点最短路径问题：将 distTo[s] 初始化为 0，其他 distTo[] 元素初始化为无穷大。以任意顺序放松有向图的所有边，重复 V 轮。
 * <p>
 * Bellman-Ford 算法所需的时间和 EV 成正比，空间和 V 成正比。
 * <p>
 * 对于任意含有 V 个顶点的加权有向图和给定的起点 s ， 在最坏情况下基于队列的 Bellman-Ford 算法解决最短路径问题（或者找到从 s 可达的负权重环）所需的时间与 EV 成正比， 空间和 V 成正比。
 *
 * @author yibo
 * @date 2021-12-06 15:33
 **/
public class BellmanFordShortedPaths {

    /**
     * 浮点型精度
     */
    private static final double EPSILON = 1E-14;

    /**
     * 最短路径中的边，索引为边的重点
     */
    DirectedEdge[] edgeTo;

    /**
     * 到起点的距离
     */
    double[] distTo;

    /**
     * 顶点是否在队列中
     */
    private final boolean[] onQueue;

    /**
     * 队列：等待relax的顶点
     */
    private final Queue<Integer> queue;

    /**
     * relax的调用次数
     */
    private int cost;

    /**
     * 最短路径树中的负权重环，如不存在则为null
     * <p>
     * 加权有向图中的负权重环是一个总权重（环上的所有边的权重之和）为负的有向环。
     */
    private Iterable<DirectedEdge> cycle;

    /**
     * 计算到其他所有节点的最短路径树
     *
     * @param graph 无环图
     * @param s     起点
     */
    public BellmanFordShortedPaths(WeightedDirectedGraph graph, int s) {
        this.edgeTo = new DirectedEdge[graph.getVertices()];
        this.distTo = new double[graph.getVertices()];
        Arrays.fill(this.distTo, Double.MAX_VALUE);
        this.onQueue = new boolean[graph.getVertices()];

        // 初始化起点距离为0
        distTo[s] = 0;

        // 起点入队
        queue = new LinkedList<>();
        queue.add(s);
        onQueue[s] = true;

        // 最短路径树中不存在负权重环时，正常查找最短路径树，如出现负权重环，则说明最短路径树不存在
        while (!hasNegativeCycle() && !queue.isEmpty()) {
            int v = queue.poll();
            onQueue[v] = false;
            relax(graph, v);
        }
    }

    /**
     * 放松顶点v
     *
     * @param graph 无环图
     * @param v     待放松顶点v
     */
    private void relax(WeightedDirectedGraph graph, int v) {
        for (DirectedEdge edge : graph.getAdjacencyVertices(v)) {
            int w = edge.to();
            if (distTo[w] > distTo[v] + edge.getWeight() + EPSILON) {
                distTo[w] = distTo[v] + edge.getWeight();
                edgeTo[w] = edge;
                // 如果w不在队列中，则将w加入队列
                if (!onQueue[w]) {
                    queue.add(w);
                    onQueue[w] = true;
                }
            }
            // relax方法调用次数 = 顶点数时，检测负权重环
            if (++cost % graph.getVertices() == 0) {
                findNegativeCycle();
                if (hasNegativeCycle()) {
                    // 找到了负权重环，则推出顶点v的relax过程
                    return;
                }
            }
        }
    }

    /**
     * 最短路径树中是否有负权重环，判断cycle是否为空
     *
     * @return true-有，false-没有
     */
    public boolean hasNegativeCycle() {
        return cycle != null;
    }

    /**
     * 查找最短路径树中的负有向环
     */
    public void findNegativeCycle() {
        WeightedDirectedGraph shortedPathsTree = new WeightedDirectedGraph(edgeTo.length);
        for (DirectedEdge edge : edgeTo) {
            if (edge != null) {
                shortedPathsTree.addEdge(edge);
            }
        }
        WeightedDirectedCycle finder = new WeightedDirectedCycle(shortedPathsTree);
        cycle = finder.getCycle();
    }

    /**
     * 输出负权重环
     *
     * @return {@code cycle}
     */
    public Iterable<DirectedEdge> negativeCycle() {
        return cycle;
    }
}
