package pers.yibo.algorithms.graph;

/**
 * 图的通用API
 * <p>
 * 定义顶点id为[0,顶点数-1]
 *
 * @author aoyb
 * 2021/11/18 10:19 上午
 */
public interface Graph {
    /**
     * 向图中添加一条边 v-w
     *
     * @param v 边的起点
     * @param w 边的终点
     */
    void addEdge(int v, int w);

    /**
     * 获取图的顶点数量
     *
     * @return 图的顶点数量
     */
    int getVertices();

    /**
     * 获取图的边数量
     *
     * @return 图的边数量
     */
    int getEdges();

    /**
     * 获取节点v的所有邻接节点
     *
     * @param v 目标节点v
     * @return 节点v的所有邻接节点
     */
    Iterable<Integer> getAdjacencyVertices(int v);

    /**
     * 计算节点v的度
     *
     * @param v 目标节点v
     * @return 节点v的度
     */
    int degree(int v);
}
