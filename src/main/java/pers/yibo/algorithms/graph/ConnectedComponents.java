package pers.yibo.algorithms.graph;

import pers.yibo.algorithms.fundamentals.Bag;

/**
 * 使用深度优先搜索找出图中的所有连通分量
 * <p>
 * 深度优先搜索的预处理使用的时间和空间与 V+E 成正比且可以在常数时间内处理关于图的连通性查询。
 *
 * @author yibo
 * @date 2021-11-18 14:54
 **/
public class ConnectedComponents {
    /**
     * 记录搜索过的点
     */
    private final boolean[] marked;

    /**
     * 记录每个点所在的连通分量
     */
    private final int[] id;

    /**
     * 记录连通分量数
     */
    private int count;

    public ConnectedComponents(Graph graph) {
        this.marked = new boolean[graph.getVertices()];
        this.id = new int[graph.getVertices()];

        for (int v = 0; v < graph.getVertices(); v++) {
            if (!marked[v]) {
                dfs(graph, v);
                // 每次dfs搜索结束，说明该连通分量搜索完成，因此count + 1
                count++;
            }
        }
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : graph.getAdjacencyVertices(v)) {
            if (!marked[w]) {
                dfs(graph, w);
            }
        }
    }

    /**
     * 判断两个顶点是否连通
     *
     * @param v 顶点
     * @param w 顶点
     * @return true-连通，false-不连通
     */
    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    /**
     * 获取顶点v的连通分量id
     *
     * @param v 顶点
     * @return 顶点v的连通分量id
     */
    public int getId(int v) {
        return id[v];
    }

    /**
     * 获取图的连通分量数
     *
     * @return 图的连通分量数
     */
    public int getCount() {
        return count;
    }

    /**
     * 打印所有连通分量
     */
    @SuppressWarnings("unchecked")
    public void print() {
        Bag<Integer>[] bags = (Bag<Integer>[]) new Bag[count];
        // 初始化bag，每个bag为一个连通分量
        for (int i = 0; i < count; i++) {
            bags[i] = new Bag<>();
        }
        // 将所有顶点放入对应的bag中
        for (int v = 0; v < id.length; v++) {
            bags[id[v]].add(v);
        }
        for (int i = 0; i < count; i++) {
            bags[i].forEach(v -> System.out.print(v + " "));
        }
        System.out.println();
    }
}
