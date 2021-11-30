package pers.yibo.algorithms.graph;

import pers.yibo.algorithms.fundamentals.Bag;

/**
 * 加权无向图
 *
 * @author yibo
 * @date 2021-11-23 14:30
 **/
public class WeightedUndirectedGraph {

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
    private final Bag<UndirectedEdge>[] adjacencyList;

    @SuppressWarnings("unchecked")
    public WeightedUndirectedGraph(int vertices) {
        this.vertices = vertices;
        adjacencyList = (Bag<UndirectedEdge>[]) new Bag[vertices];
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
    public Iterable<UndirectedEdge> getAllEdges() {
        Bag<UndirectedEdge> edges = new Bag<>();
        for (int v = 0; v < vertices; v++) {
            for (UndirectedEdge edge : adjacencyList[v]) {
                if (edge.other(v) > v) {
                    // 每个顶点的邻接边中，仅添加另一个顶点比该顶点更大的边（无向图每条边会添加两次）
                    edges.add(edge);
                }
            }
        }
        return edges;
    }

    public void addEdge(UndirectedEdge edge) {
        int v = edge.either();
        int w = edge.other(v);
        adjacencyList[v].add(edge);
        adjacencyList[w].add(edge);
        edges++;
    }

    public Iterable<UndirectedEdge> getAdjacencyVertices(int v) {
        return adjacencyList[v];
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertices).append(" vertices, ").append(edges).append(" edges \n");
        for (int v = 0; v < vertices; v++) {
            s.append(v).append(": ");
            for (UndirectedEdge e : adjacencyList[v]) {
                s.append(e).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
