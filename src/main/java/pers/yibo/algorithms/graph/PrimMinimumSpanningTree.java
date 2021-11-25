package pers.yibo.algorithms.graph;

import pers.yibo.algorithms.sort.MinIndexPriorityQueue;

import java.util.Arrays;

/**
 * Prim 算法的即时实现
 *
 * @author yibo
 * @date 2021-11-23 16:12
 **/
public class PrimMinimumSpanningTree {
    /**
     * 已经添加到MST中的点
     */
    private final boolean[] marked;

    /**
     * 顶点距离树最近的边(MST的所有边)
     */
    private final UndirectedEdge[] edgeTo;

    /**
     * edgeTo对应的权重
     */
    private final double[] distTo;

    /**
     * 最小索引优先队列：基于权重排序
     */
    private final MinIndexPriorityQueue<Double> pqEdges;

    public PrimMinimumSpanningTree(UndirectedEdgeWeightedGraph graph) {
        this.marked = new boolean[graph.getVertices()];
        this.edgeTo = new UndirectedEdge[graph.getVertices()];
        this.distTo = new double[graph.getVertices()];
        // 初始化权重为最大值
        Arrays.fill(distTo, Double.MAX_VALUE);
        this.pqEdges = new MinIndexPriorityQueue<>(graph.getVertices());

        // 用第一个点初始化队列
        distTo[0] = 0;
        pqEdges.put(0, 0D);
        while (!pqEdges.isEmpty()) {
            // 取出优先队列中权重最小的有效边中，不属于MST的顶点，并将其加入到MST中
            visit(graph, pqEdges.pollIndex());
        }
    }

    private void visit(UndirectedEdgeWeightedGraph graph, int v) {
        marked[v] = true;
        for (UndirectedEdge edge : graph.getAdjacencyVertices(v)) {
            int w = edge.other(v);
            if (marked[w]) {
                // 对端顶点也在MST中，则该边无效
                continue;
            }
            // 当该边的权重小于distTo对应权重，则更新，并将该点及权重添加到优先队列中
            if (edge.getWeight() < distTo[w]) {
                edgeTo[w] = edge;
                distTo[w] = edge.getWeight();
                pqEdges.put(w, distTo[w]);
            }
        }
    }
}
