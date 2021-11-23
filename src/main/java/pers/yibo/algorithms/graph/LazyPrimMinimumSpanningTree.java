package pers.yibo.algorithms.graph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 最小生成树的 Prim 算法的延时实现
 * <p>
 * Prim 算法能够得到任意加权连通图的最小生成树。
 * <p>
 * 它的每一步都会为一棵生长中的 树添加一条边。一开始这棵树只有一个顶点，然后会向它添加 V-1 条边，
 * 每次总是将下一条连接树中的顶点与不在树中的顶点且权重最小的边（黑色表示）加入树中（即由树中的顶点所定义的切分 中的一条横切边）
 * <p>
 * Prim 算法的这种实现使用了一条优先队列来保存所有的横切边、一个由顶点索引的数组来标记树的 顶点以及一条队列来保存最小生成树的边。这种延时实现会在优先队列中保留失效的边。
 *
 * @author yibo
 * @date 2021-11-23 15:07
 **/
public class LazyPrimMinimumSpanningTree {
    /**
     * 已经添加到MST中的点
     */
    private final boolean[] marked;

    /**
     * MST中的边
     */
    private final Queue<UndirectedEdge> mstEdges;

    /**
     * MST的权重
     */
    private double weight;

    /**
     * 优先队列：基于权重排序
     */
    private final PriorityQueue<UndirectedEdge> pqEdges;

    /**
     * 默认图为连通的
     *
     * @param graph 加权无向图
     */
    public LazyPrimMinimumSpanningTree(UndirectedEdgeWeightedGraph graph) {
        this.marked = new boolean[graph.getVertices()];
        this.mstEdges = new LinkedList<>();
        this.pqEdges = new PriorityQueue<>();

        // 顶点0为起点，直接添加到MST中
        visit(graph, 0);
        while (!pqEdges.isEmpty()) {
            // 优先队列中取出权重最小的边
            UndirectedEdge edge = pqEdges.poll();
            int v = edge.either();
            int w = edge.other(v);
            if (marked[v] && marked[w]) {
                // v和w均在MST中，说明该边无效，则继续取
                continue;
            }
            // 最小权重的边添加到MST中
            mstEdges.add(edge);
            weight += edge.getWeight();
            // 添加新点对应的有效边
            if (!marked[v]) {
                visit(graph, v);
            }
            if (!marked[w]) {
                visit(graph, w);
            }
        }
    }

    /**
     * mst添加一个点，并且优先队列中添加所有与这个点连接且另一个顶点不在mst中的边
     *
     * @param graph 图
     * @param v     顶点
     */
    private void visit(UndirectedEdgeWeightedGraph graph, int v) {
        marked[v] = true;
        for (UndirectedEdge edge : graph.getAdjacencyVertices(v)) {
            if (!marked[edge.other(v)]) {
                pqEdges.add(edge);
            }
        }
    }

    public Iterable<UndirectedEdge> getMstEdges() {
        return mstEdges;
    }

    public double getWeight() {
        return weight;
    }
}