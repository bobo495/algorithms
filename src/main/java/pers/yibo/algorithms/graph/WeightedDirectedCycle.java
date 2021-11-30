package pers.yibo.algorithms.graph;

import java.util.Stack;

/**
 * 加权有向图的环检测
 *
 * @author yibo
 * @date 2021-11-18 15:57
 **/
public class WeightedDirectedCycle {
    /**
     * 顶点是否被标记
     */
    private final boolean[] marked;

    /**
     * 记录路径
     */
    private final DirectedEdge[] edgeTo;
    /**
     * 记录环的路径
     */
    private Stack<DirectedEdge> cycle;

    /**
     * 顶点是否在栈中
     */
    private final boolean[] onStack;

    public WeightedDirectedCycle(WeightedDirectedGraph graph) {


        this.marked = new boolean[graph.getVertices()];
        this.edgeTo = new DirectedEdge[graph.getVertices()];
        this.onStack = new boolean[graph.getVertices()];

        for (int v = 0; v < graph.getVertices(); v++) {
            if (!marked[v]) {
                dfs(graph, v);
            }
        }
    }

    private void dfs(WeightedDirectedGraph graph, int v) {
        marked[v] = true;
        onStack[v] = true;
        for (DirectedEdge edge : graph.getAdjacencyVertices(v)) {
            int w = edge.to();
            if (hasCycle()) {
                // 已经找到环，则终止查找
                return;
            } else if (!marked[w]) {
                // w未被标记，则继续查找
                edgeTo[w] = edge;
                dfs(graph, w);
            } else if (onStack[w]) {
                // v-w有路，且w已经在栈中，说明存在环
                cycle = new Stack<>();
                DirectedEdge f = edge;
                // 遍历边，直到找到环
                while (f.from() != w) {
                    cycle.push(f);
                    f = edgeTo[f.from()];
                }
                cycle.push(f);

                return;
            }
        }
        // v搜索完毕后，出栈
        onStack[v] = false;
    }

    /**
     * 判断是否有环
     *
     * @return true-有环，false-无环
     */
    public boolean hasCycle() {
        return cycle != null;
    }

    /**
     * 获取环路径
     *
     * @return 环路径
     */
    public Iterable<DirectedEdge> getCycle() {
        return cycle;
    }

    public static void main(String[] args) {
        WeightedDirectedGraph graph = new WeightedDirectedGraph(5);
        graph.addEdge(new DirectedEdge(1, 2, 1));
        graph.addEdge(new DirectedEdge(2, 3, 1));
        graph.addEdge(new DirectedEdge(3, 4, 1));
        graph.addEdge(new DirectedEdge(4, 2, 1));
        WeightedDirectedCycle c = new WeightedDirectedCycle(graph);
        System.out.println(c.getCycle());
    }
}
