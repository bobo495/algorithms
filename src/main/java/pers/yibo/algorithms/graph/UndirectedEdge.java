package pers.yibo.algorithms.graph;

import java.util.StringJoiner;

/**
 * 加权无向边表示
 *
 * @author yibo
 * @date 2021-11-23 14:30
 **/
public class UndirectedEdge implements Comparable<UndirectedEdge> {

    private final int v;
    private final int w;
    private final double weight;

    public UndirectedEdge(int v, int w, double weight) {
        if (v < 0) {
            throw new IllegalArgumentException("vertex index must be a non-negative integer");
        }
        if (w < 0) {
            throw new IllegalArgumentException("vertex index must be a non-negative integer");
        }
        if (Double.isNaN(weight)) {
            throw new IllegalArgumentException("Weight is NaN");
        }
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * 获取边的权重
     *
     * @return 权重weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * 返回边的其中一个顶点
     *
     * @return 返回顶点v
     */
    public int either() {
        return v;
    }

    /**
     * 返回边的另一个顶点
     *
     * @param s 边的其中一个顶点
     * @return 另一个顶点
     */
    public int other(int s) {
        if (s == v) {
            return w;
        } else if (s == w) {
            return v;
        } else {
            throw new IllegalArgumentException("Illegal endpoint");
        }
    }

    @Override
    public int compareTo(UndirectedEdge o) {
        return Double.compare(this.weight, o.weight);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UndirectedEdge.class.getSimpleName() + "[", "]")
                .add("v=" + v)
                .add("w=" + w)
                .add("weight=" + weight)
                .toString();
    }
}
