package pers.yibo.algorithms.graph;

/**
 * 有向无环图的拓扑排序
 * <p>
 * {@link DepthFirstOrder#reversePost()}的逆后序排序
 *
 * @author yibo
 * @date 2021-11-18 16:47
 **/
public class Topological {
    /**
     * 顶点的拓扑顺序
     */
    private final Iterable<Integer> order;

    public Topological(Graph graph) {
        DirectedCycle cycle = new DirectedCycle(graph);
        if (cycle.hasCycle()) {
            throw new IllegalArgumentException("Graph is not DAG, cycle is: " + cycle.getCycle());
        }

        DepthFirstOrder dfo = new DepthFirstOrder(graph);
        order = dfo.reversePost();
    }

    public Topological(WeightedDirectedGraph graph) {
        WeightedDirectedCycle cycle = new WeightedDirectedCycle(graph);
        if (cycle.hasCycle()) {
            throw new IllegalArgumentException("Graph is not DAG, cycle is: " + cycle.getCycle());
        }

        DepthFirstOrder dfo = new DepthFirstOrder(graph);
        order = dfo.reversePost();
    }

    public Iterable<Integer> order() {
        return order;
    }
}
