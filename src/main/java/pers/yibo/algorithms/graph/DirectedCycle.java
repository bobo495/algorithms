package pers.yibo.algorithms.graph;

import java.util.Stack;

/**
 * 有向图的环检测
 *
 * @author yibo
 * @date 2021-11-18 15:57
 **/
public class DirectedCycle {
    /**
     * 顶点是否被标记
     */
    private final boolean[] marked;

    /**
     * 记录路径
     */
    private final int[] edgeTo;
    /**
     * 记录环的路径
     */
    private Stack<Integer> cycle;

    /**
     * 顶点是否在栈中
     */
    private final boolean[] onStack;

    public DirectedCycle(Graph graph) {

        if (!(graph instanceof DirectedGraph)) {
            throw new IllegalArgumentException("graph is not DirectedGraph!");
        }

        this.marked = new boolean[graph.getVertices()];
        this.edgeTo = new int[graph.getVertices()];
        this.onStack = new boolean[graph.getVertices()];

        for (int v = 0; v < graph.getVertices(); v++) {
            if (!marked[v]) {
                dfs(graph, v);
            }
        }
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        onStack[v] = true;
        for (int w : graph.getAdjacencyVertices(v)) {
            if (hasCycle()) {
                // 已经找到环，则终止查找
                return;
            } else if (!marked[w]) {
                // w未被标记，则继续查找
                edgeTo[w] = v;
                dfs(graph, w);
            } else if (onStack[w]) {
                // v-w有路，且w已经在栈中，说明存在环
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
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
    public Iterable<Integer> getCycle() {
        return cycle;
    }

    public static void main(String[] args) {
        Graph graph = new DirectedGraph(5);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 2);
        DirectedCycle c = new DirectedCycle(graph);
        System.out.println(c.getCycle());
    }
}
