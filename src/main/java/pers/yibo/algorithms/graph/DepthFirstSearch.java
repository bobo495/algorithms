package pers.yibo.algorithms.graph;

/**
 * 深度优先搜索
 * <p>
 * 深度优先搜索标记与起点连通的所有顶点所需的时间和顶点的度数之和成正比。
 *
 * @author yibo
 * @date 2021-11-18 14:11
 **/
public class DepthFirstSearch {
    /**
     * 顶点是否被搜索过
     */
    private final boolean[] marked;
    /**
     * 被搜索过的顶点数量
     */
    private int count;

    /**
     * DFS方法搜索图
     *
     * @param graph 指定图
     * @param s     搜索起点
     */
    public DepthFirstSearch(Graph graph, int s) {
        marked = new boolean[graph.getVertices()];
        dfs(graph, s);
    }

    /**
     * 多点搜索（多点可达性问题）
     *
     * @param graph   指定图
     * @param sources 搜索起点的集合
     */
    public DepthFirstSearch(Graph graph, Iterable<Integer> sources) {
        marked = new boolean[graph.getVertices()];
        for (int s : sources) {
            if (!marked[s]) {
                dfs(graph, s);
            }
        }
    }

    /**
     * 递归地访问它的所有没有被标记过的邻居顶点
     *
     * @param graph 指定图
     * @param v     当前搜索点
     */
    public void dfs(Graph graph, int v) {
        // 新搜索的点标记为true，并更新count
        marked[v] = true;
        count++;
        // 检索该点的邻接点集
        for (int w : graph.getAdjacencyVertices(v)) {
            if (!marked[v]) {
                // 找到未被标记的邻接点，继续往下搜索
                dfs(graph, w);
            }
        }
    }

    public boolean marked(Graph graph, int v) {
        return marked[v];
    }

    public int count() {
        return count;
    }
}
