package pers.yibo.algorithms.fundamentals;

/**
 * 连通图算法
 * <p>
 * 问题的输入是一列整数 对，其中每个整数都表示一个某种类型的对象，一对整数 p q 可以被理解为“ p 和 q 是相连的”。我们假设“相连”是 一种等价关系。
 * <p>
 * 自反性： p 和 p 是相连的；
 * <p>
 * 对称性：如果 p 和 q 是相连的，那么 q 和 p 也是相连的；
 * <p>
 * 传递性：如果 p 和 q 是相连的且 q 和 r 是相连的，那么 p 和 r 也是相连的
 * <p>
 * 输入说明：假设有N个顶点，则顶点id为[0, N-1]
 *
 * @author yibo
 * @date 2021-11-25 16:35
 **/
public class UnionFind {
    /**
     * 连通分量id的父节点，即与该节点连通的另一个节点
     */
    private int[] parent;

    /**
     * 当前节点距离根节点的级别
     */
    private byte[] rank;

    /**
     * 连通分量数量
     */
    private int count;

    /**
     * 输入图大小（顶点数量）
     *
     * @param size 顶点数量
     */
    public UnionFind(int size) {
        if (size < 0) {
            throw new IllegalArgumentException();
        }
        this.parent = new int[size];
        this.rank = new byte[size];
        // 初始化连通图，每个id均与自己相连，每个id都没有父节点
        for (int i = 0; i < size; i++) {
            this.parent[i] = i;
            this.rank[i] = 0;
        }
        // 此时每个id均是一个连通分量
        this.count = size;
    }

    /**
     * 返回图的连通分量数量
     *
     * @return 连通分量数量
     */
    public int count() {
        return this.count;
    }

    /**
     * 找到id所在的连通分量
     *
     * @param id 输入id
     * @return 连通分量id
     */
    private int find(int id) {
        validate(id);
        while (parent[id] != id) {
            // 路径压缩，每次查找将路径减半
            parent[id] = parent[parent[id]];
            id = parent[id];
        }
        return id;
    }

    /**
     * 两个节点是否输入同一个分量
     *
     * @param id1 节点1
     * @param id2 节点2
     * @return true-属于，false-不属于
     */
    public boolean connected(int id1, int id2) {
        return find(id1) == find(id2);
    }

    /**
     * 连通两个节点
     *
     * @param id1 节点1
     * @param id2 节点2
     */
    public void union(int id1, int id2) {
        // 找到根节点
        int root1 = find(id1);
        int root2 = find(id2);
        if (root1 == root2) {
            // 已经连通，则返回
            return;
        }
        if (rank[id1] < rank[id2]) {
            // id1对应的子树更小，则将增加id1对应子树的高度
            parent[id1] = parent[id2];
        } else if (rank[id1] > rank[id2]) {
            parent[id2] = parent[id1];
        } else {
            // 两个节点对应子树高度相同，则增加对应rank级别
            parent[id1] = parent[id2];
            rank[id1]++;
        }
        // 连通分量-1
        count--;
    }

    /**
     * 判断输入id的有效性
     *
     * @param id 输入id
     */
    private void validate(int id) {
        int size = parent.length;
        if (id < 0 || id >= size) {
            throw new IllegalArgumentException("index " + id + " is not between 0 and " + (size - 1));
        }
    }

}
