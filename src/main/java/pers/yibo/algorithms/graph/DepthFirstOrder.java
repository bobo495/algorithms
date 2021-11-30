package pers.yibo.algorithms.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 有向图中基于深度优先搜索的顶点排序
 *
 * @author yibo
 * @date 2021-11-18 16:35
 **/
public class DepthFirstOrder {
    /**
     * 标记顶点是否被遍历
     */
    private final boolean[] marked;

    /**
     * 顶点的前序排列
     * <p>
     * 在递归调用之前将顶点加入队列。
     */
    private final Queue<Integer> pre;

    /**
     * 顶点的后序排列
     * <p>
     * 在递归调用之后将顶点加入队列。
     */
    private final Queue<Integer> post;

    /**
     * 顶点的逆后序排列
     * <p>
     * 在递归调用之后将顶点压入栈。
     */
    private final Stack<Integer> reversePost;

    public DepthFirstOrder(Graph graph) {
        if (!(graph instanceof DirectedGraph)) {
            throw new IllegalArgumentException("graph is not DirectedGraph!");
        }

        this.marked = new boolean[graph.getVertices()];
        this.pre = new LinkedList<>();
        this.post = new LinkedList<>();
        this.reversePost = new Stack<>();

        for (int v = 0; v < graph.getVertices(); v++) {
            if (!marked[v]) {
                dfs(graph, v);
            }
        }
    }

    private void dfs(Graph graph, int v) {
        // 遍历顺序
        pre.add(v);

        marked[v] = true;
        for (int w : graph.getAdjacencyVertices(v)) {
            if (!marked[w]) {
                dfs(graph, w);
            }
        }

        // 每条路径的末尾
        post.add(v);
        reversePost.push(v);
    }

    public DepthFirstOrder(WeightedDirectedGraph graph) {

        this.marked = new boolean[graph.getVertices()];
        this.pre = new LinkedList<>();
        this.post = new LinkedList<>();
        this.reversePost = new Stack<>();

        for (int v = 0; v < graph.getVertices(); v++) {
            if (!marked[v]) {
                dfs(graph, v);
            }
        }
    }

    private void dfs(WeightedDirectedGraph graph, int v) {
        // 遍历顺序
        pre.add(v);

        marked[v] = true;
        for (DirectedEdge edge : graph.getAdjacencyVertices(v)) {
            if (!marked[edge.to()]) {
                dfs(graph, edge.to());
            }
        }

        // 每条路径的末尾
        post.add(v);
        reversePost.push(v);
    }


    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}
