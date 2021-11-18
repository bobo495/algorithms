package pers.yibo.algorithms.graph;

import pers.yibo.algorithms.fundamentals.Bag;

/**
 * 无向图
 *
 * @author yibo
 * @date 2021-11-18 11:38
 **/
public class UndirectedGraph implements Graph {

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
    public UndirectedGraph(int vertices) {
        this.vertices = vertices;
        adjacencyList = (Bag<Integer>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adjacencyList[i] = new Bag<>();
        }
    }

    @Override
    public void addEdge(int v, int w) {
        adjacencyList[v].add(w);
        adjacencyList[w].add(v);
        edges++;
    }

    @Override
    public int getVertices() {
        return vertices;
    }

    @Override
    public int getEdges() {
        return edges;
    }

    @Override
    public Iterable<Integer> getAdjacencyVertices(int v) {
        return adjacencyList[v];
    }

    @Override
    public int degree(int v) {
        return adjacencyList[v].size();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertices).append(" vertices, ").append(edges).append(" edges \n");
        for (int v = 0; v < vertices; v++) {
            s.append(v).append(": ");
            for (int w : adjacencyList[v]) {
                s.append(w).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        Graph graph = new UndirectedGraph(5);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        System.out.println(graph);
        System.out.println(graph.degree(2));
    }
}
