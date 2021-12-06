package pers.yibo.algorithms.graph;

/**
 * 套汇问题：完全有向图的负权重环检测
 * <p>
 * 套汇问题等价于加权有向图中的负权重环的检测问题。
 * <p>
 * 取每条边权重的自然对数并取反，这样在原始问题中所有边的权重之积的计算就转化为 了新图中所有边的权重之和的计算。
 * 任意权重之积 w 1 w 2 ... w k 即对应 -ln(w 1 )-ln(w 2 )- ... -ln(w k ) 之和。 转换后边的权重可能为正也可能为负。
 * 一条从 v 到 w 的路径表示将货币 v 兑换为货币 w。
 *
 * @author yibo
 * @date 2021-12-06 16:12
 **/
public class Arbitrage {
    public static void main(String[] args) {
        int vertices = 3;
        // 记录顶点对应的意义
        String[] name = new String[vertices];
        // 初始化name
        name[0] = "USD";
        name[1] = "EUR";
        name[2] = "CAD";

        WeightedDirectedGraph graph = new WeightedDirectedGraph(vertices);
        // 初始化图的边
        graph.addEdge(new DirectedEdge(0, 1, -Math.log(0.741)));
        graph.addEdge(new DirectedEdge(1, 0, -Math.log(1.349)));
        graph.addEdge(new DirectedEdge(1, 2, -Math.log(1.366)));
        graph.addEdge(new DirectedEdge(2, 1, -Math.log(0.732)));
        graph.addEdge(new DirectedEdge(0, 2, -Math.log(1.005)));
        graph.addEdge(new DirectedEdge(2, 0, -Math.log(0.995)));

        // 找到负权重环
        BellmanFordShortedPaths shortedPathsTree = new BellmanFordShortedPaths(graph, 0);
        if (shortedPathsTree.hasNegativeCycle()) {
            double stake = 1000;
            for (DirectedEdge edge : shortedPathsTree.negativeCycle()) {
                System.out.print(stake + " " + name[edge.from()]);
                stake *= Math.exp(-edge.getWeight());
                System.out.println(" = " + stake + " " + name[edge.to()]);
            }
        } else {
            System.out.println(("No arbitrage opportunity"));
        }
    }
}
