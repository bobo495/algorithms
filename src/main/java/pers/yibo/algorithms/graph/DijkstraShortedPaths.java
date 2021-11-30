package pers.yibo.algorithms.graph;

import pers.yibo.algorithms.sort.MinIndexPriorityQueue;

import java.util.Arrays;

/**
 * Dijkstra最短路径算法
 * <p>
 * 能够解决 <em>边权重非负</em> 的加权有向图的单起点最短路径问题
 * <p>
 * 该算法每次都会为最短路径树添加一条边，该边由一个树中的顶点指向非树顶点w，且是起点到w的最短路径
 *
 * @author yibo
 * 2021/11/30 20:33
 */
public class DijkstraShortedPaths {
    /**
     * 最短路径中的边，索引为边的重点
     */
    DirectedEdge[] edgeTo;

    /**
     * 到起点的距离
     */
    double[] distTo;

    /**
     * 最小索引优先队列：索引为edgeTo下标，值为distTo[]
     */
    MinIndexPriorityQueue<Double> pqEdges;

    public DijkstraShortedPaths(WeightedDirectedGraph graph, int start) {
        this.edgeTo = new DirectedEdge[graph.getVertices()];
        this.distTo = new double[graph.getVertices()];
        // 初始化权重为最大值
        Arrays.fill(distTo, Double.MAX_VALUE);
        this.pqEdges = new MinIndexPriorityQueue<>(graph.getVertices());

        // 初始化起点
        distTo[start] = 0;
        pqEdges.put(start, 0D);
        while (!pqEdges.isEmpty()) {
            // 取出优先队列中，路径最小的元素对应的索引（即顶点id），添加到最短路径中
            relax(graph, pqEdges.pollIndex());
        }
    }

    /**
     * 顶点的松弛
     * <p>
     * 对于start到任意顶点v，distT[v]表示了s->v的最短路径，其中不可达的点路径为无穷大
     * <p>
     * 顶点的松弛使得，从v->w的任意一条边e，都有：{@code distTo[w] <= distTo[v] + wight ( v -> w )}
     *
     * @param graph {@link WeightedDirectedGraph}
     * @param v     下一个添加到最短路径中的点
     */
    private void relax(WeightedDirectedGraph graph, int v) {
        // 查找该点的邻接节点
        for (DirectedEdge edge : graph.getAdjacencyVertices(v)) {
            int w = edge.to();
            if (distTo[w] > distTo[v] + edge.getWeight()) {
                // w到起点的距离 比 v到起点的距离 + v->w边更远，说明起点到w可以经过v
                distTo[w] = distTo[v] + edge.getWeight();
                edgeTo[w] = edge;

                // 此时，edge为有效边，添加到优先队列中
                pqEdges.put(w, distTo[w]);
            }
        }
    }
}
