package pers.yibo.algorithms.graph;

import pers.yibo.algorithms.fundamentals.UnionFind;
import pers.yibo.algorithms.sort.MinPriorityQueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 最小生成树的 Kruskal 算法
 * <p>
 * Kruskal 算法能够计算任意加权连通图 的最小生成树。
 *
 * @author yibo
 * @date 2021-11-25 16:17
 **/
public class KruskalMinimumSpanningTree {
    Queue<UndirectedEdge> mstEdges;

    public KruskalMinimumSpanningTree(UndirectedEdgeWeightedGraph graph) {
        mstEdges = new LinkedList<>();

        // 用加权无向图的所有边构建最小优先队列
        MinPriorityQueue<UndirectedEdge> pgEdges = new MinPriorityQueue<>();
        for (UndirectedEdge edge : graph.getAllEdges()) {
            pgEdges.insert(edge);
        }

        // 构建连通图，mst中所有顶点均在同一个连通分量，校验有效边
        UnionFind uf = new UnionFind(graph.getVertices());

        // 优先队列不为空，且mst边不到n-1条，说明mst未构建完
        while (!pgEdges.isEmpty() && mstEdges.size() < graph.getVertices() - 1) {
            // 取出权重最小的有效边
            UndirectedEdge edge = pgEdges.poll();
            int v = edge.either(), w = edge.other(v);
            if (uf.connected(v, w)) {
                // v-w连通，说明边无效
                continue;
            }
            // v-w不连通，则将该边添加到mst，并使v-w连通
            uf.union(v, w);
            mstEdges.add(edge);
        }
    }
}
