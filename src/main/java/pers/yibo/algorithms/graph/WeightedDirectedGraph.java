package pers.yibo.algorithms.graph;

import pers.yibo.algorithms.fundamentals.Bag;

/**
 * 加权有向图
 *
 * @author yibo
 * 2021/11/30 20:17
 */
public class WeightedDirectedGraph {


    /**
     * 顶点数
     */
    private final int vertices;

    /**
     * 边数
     */
    private int edges;


    /**
     * 邻接表，第i个Bag表示顶点i的所有边
     */
    private final Bag<DirectedEdge>[] adjacencyList;

    @SuppressWarnings("unchecked")
    public WeightedDirectedGraph(int vertices) {
        this.vertices = vertices;
        adjacencyList = (Bag<DirectedEdge>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adjacencyList[i] = new Bag<>();
        }
    }

    public int getVertices() {
        return vertices;
    }

    public int getEdges() {
        return edges;
    }


    /**
     * 遍历图的所有边
     *
     * @return 所有边
     */
    public Iterable<DirectedEdge> getAllEdges() {
        Bag<DirectedEdge> edges = new Bag<>();
        for (int v = 0; v < vertices; v++) {
            for (DirectedEdge edge : adjacencyList[v]) {
                edges.add(edge);
            }
        }
        return edges;
    }

    public void addEdge(DirectedEdge edge) {
        adjacencyList[edge.from()].add(edge);
        edges++;
    }

    public Iterable<DirectedEdge> getAdjacencyVertices(int v) {
        return adjacencyList[v];
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertices).append(" vertices, ").append(edges).append(" edges \n");
        for (int v = 0; v < vertices; v++) {
            s.append(v).append(": ");
            for (DirectedEdge e : adjacencyList[v]) {
                s.append(e).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
