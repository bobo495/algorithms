package pers.yibo.algorithms.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 391. 完美矩形
 * <p>
 * https://leetcode-cn.com/problems/perfect-rectangle
 * <p>
 * 输入：rectangles = [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]
 * 输出：true
 * 解释：5 个矩形一起可以精确地覆盖一个矩形区域。
 * <p>
 * 输入：rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[2,2,4,4]]
 * 输出：false
 * 解释：因为中间有相交区域，虽然形成了矩形，但不是精确覆盖。
 *
 * @author yibo
 * @date 2021-11-16 09:18
 **/
public class PerfectRectangle {

    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    /**
     * 校验是否有重叠区域: 顶点出现在最大矩阵顶点时，仅出现一次；顶点出现在非四角时，出现2次或4次
     */
    public boolean isRectangleCover(int[][] rectangles) {
        // 计算最大矩形和面积，校验面积是否一致
        int[] maxRectangle = rectangles[0].clone();
        int area = 0;
        for (int[] rectangle : rectangles) {
            maxRectangle[0] = Math.min(maxRectangle[0], rectangle[0]);
            maxRectangle[1] = Math.min(maxRectangle[1], rectangle[1]);
            maxRectangle[2] = Math.max(maxRectangle[2], rectangle[2]);
            maxRectangle[3] = Math.max(maxRectangle[3], rectangle[3]);

            area += (rectangle[3] - rectangle[1]) * (rectangle[2] - rectangle[0]);
        }
        int maxArea = (maxRectangle[3] - maxRectangle[1]) * (maxRectangle[2] - maxRectangle[0]);

        if (maxArea != area) {
            // 面积不一致，则返回false
            return false;
        }

        Map<Point, Integer> map = new HashMap<>(16);
        for (int[] rectangle : rectangles) {
            Point p1 = new Point(rectangle[0], rectangle[1]);
            Point p2 = new Point(rectangle[0], rectangle[3]);
            Point p3 = new Point(rectangle[2], rectangle[1]);
            Point p4 = new Point(rectangle[2], rectangle[3]);

            map.put(p1, map.getOrDefault(p1, 0) + 1);
            map.put(p2, map.getOrDefault(p2, 0) + 1);
            map.put(p3, map.getOrDefault(p3, 0) + 1);
            map.put(p4, map.getOrDefault(p4, 0) + 1);
        }

        for (Map.Entry<Point, Integer> entry : map.entrySet()) {
            Point p = entry.getKey();
            int v = entry.getValue();
            if (((p.x == maxRectangle[0] || p.x == maxRectangle[2])
                    && (p.y == maxRectangle[1] || p.y == maxRectangle[3]))) {
                // 顶点
                if (v != 1) {
                    return false;
                }
                continue;
            }

            if (v % 2 == 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] rectangles = new int[][]{
                {0, 0, 1, 1}, {0, 0, 1, 1}, {1, 1, 2, 2}, {1, 1, 2, 2}};
        PerfectRectangle p = new PerfectRectangle();
        System.out.println(p.isRectangleCover(rectangles));
    }
}
