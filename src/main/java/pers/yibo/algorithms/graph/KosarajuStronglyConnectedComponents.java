package pers.yibo.algorithms.graph;

import pers.yibo.algorithms.fundamentals.Bag;

/**
 * Kosaraju 算法计算有向图的强连通分量
 * <p>
 * 在给定的一幅有向图 G 中，使用 DepthFirstOrder 来计算它的反向图 G^R 的逆后序排列。
 * <p>
 * 在 G 中进行标准的深度优先搜索，但是要按照刚才计算得到的顺序而非标准的顺序来访问
 * 所有未被标记的顶点。
 * <p>
 * 使用深度优先搜索查找给定有向图 G 的反向图 G R ，根据由此得到的所有顶点的逆后 序再次用深度优先搜索处理有向图 G（Kosaraju 算法），
 * 其构造函数中的每一次递归调用所标 记的顶点都在同一个强连通分量之中。
 *
 * @author yibo
 * @date 2021-11-23 11:25
 **/
public class KosarajuStronglyConnectedComponents {
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

    public KosarajuStronglyConnectedComponents(DirectedGraph graph) {
        this.marked = new boolean[graph.getVertices()];
        this.id = new int[graph.getVertices()];
        DepthFirstOrder dfo = new DepthFirstOrder(graph.reverse());
        for (int v : dfo.reversePost()) {
            if (!marked[v]) {
                dfs(graph, v);
                // 每次dfs搜索结束，说明该连通分量搜索完成，因此count + 1
                count++;
            }
        }
    }

    public void dfs(Graph graph, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : graph.getAdjacencyVertices(v)) {
            if (!marked[w]) {
                dfs(graph, w);
            }
        }
    }


    /**
     * 判断两个顶点是否强连通
     *
     * @param v 顶点
     * @param w 顶点
     * @return true-连通，false-不连通
     */
    public boolean stronglyConnected(int v, int w) {
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
