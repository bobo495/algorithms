package pers.yibo.algorithms.graph;

import pers.yibo.algorithms.fundamentals.Bag;

/**
 * 有向图
 *
 * @author yibo
 * @date 2021-11-18 15:23
 **/
public class DirectedGraph implements Graph {

    /**
     * 顶点数
     */
    private final int vertices;

    /**
     * 边数
     */
    private int edges;

    /**
     * 邻接表，第i个Bag表示顶点i的所有邻接节点
     */
    private final Bag<Integer>[] adjacencyList;

    @SuppressWarnings("unchecked")
    public DirectedGraph(int vertices) {
        this.vertices = vertices;
        this.adjacencyList = (Bag<Integer>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            this.adjacencyList[i] = new Bag<>();
        }
    }

    @Override
    public void addEdge(int v, int w) {
        this.adjacencyList[v].add(w);
        this.edges++;
    }

    @Override
    public int getVertices() {
        return this.vertices;
    }

    @Override
    public int getEdges() {
        return this.edges;
    }

    @Override
    public Iterable<Integer> getAdjacencyVertices(int v) {
        return this.adjacencyList[v];
    }

    @Override
    public int degree(int v) {
        return this.adjacencyList[v].size();
    }

    /**
     * 反向图
     *
     * @return DirectedGraph
     */
    public DirectedGraph reverse() {
        DirectedGraph reverseGraph = new DirectedGraph(this.vertices);
        for (int v = 0; v < this.vertices; v++) {
            for (int w : this.adjacencyList[v]) {
                reverseGraph.addEdge(w, v);
            }
        }
        return reverseGraph;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertices).append(" vertices, ").append(edges).append(" edges \n");
        for (int v = 0; v < vertices; v++) {
            if (adjacencyList[v].isEmpty()) {
                // 空邻接表不输出
                continue;
            }
            s.append(v).append(": ");
            for (int w : adjacencyList[v]) {
                s.append(w).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
