package pers.yibo.algorithms.graph;

import java.util.StringJoiner;

/**
 * 加权有向边
 *
 * @author aoyb
 * 2021/11/30 20:00
 */
public class DirectedEdge implements Comparable<DirectedEdge> {
    private int from;
    private int to;
    private double weight;

    public DirectedEdge(int from, int to, double weight) {
        if (from < 0) {
            throw new IllegalArgumentException("vertex index must be a non-negative integer");
        }
        if (to < 0) {
            throw new IllegalArgumentException("vertex index must be a non-negative integer");
        }
        if (Double.isNaN(weight)) {
            throw new IllegalArgumentException("Weight is NaN");
        }
        this.from = from;
        this.to = to;
        this.weight = weight;
    }


    /**
     * 获取边的权重
     *
     * @return 权重weight
     */
    public double getWeight() {
        return this.weight;
    }

    /**
     * 返回边的起点
     *
     * @return 边的起点
     */
    public int from() {
        return this.from;
    }

    /**
     * 返回边的终点
     *
     * @return 边的终点
     */
    public int to() {
        return this.to;
    }

    @Override
    public int compareTo(DirectedEdge o) {
        return Double.compare(this.weight, o.weight);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DirectedEdge.class.getSimpleName() + "[", "]")
                .add("from=" + this.from)
                .add("to=" + this.to)
                .add("weight=" + this.weight)
                .toString();
    }
}
