package pers.yibo.algorithms.test;

import java.util.Arrays;

/**
 * 向量类
 *
 * @author yibo
 * @date 2021-07-26 17:20
 **/
public class Vector {
    private final double[] coordinates;

    public Vector(double[] a) {
        this.coordinates = a;
    }

    @Override
    public String toString() {
        return "{" + "\"coordinates\":" +
                Arrays.toString(coordinates) +
                '}';
    }
}