package pers.yibo.algorithms.test;


/**
 * @author yibo
 * @date 2021-07-26 17:21
 **/
public class Test {
    public static void main(String[] args) {
        double[] a={3.0,4.0};
        Vector vector = new Vector(a);
        System.out.println(vector);
        a[0]=1.0;
        System.out.println(vector);
    }
}
