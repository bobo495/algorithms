package pers.yibo.algorithms.leetcode;

/**
 * 240. 搜索二维矩阵 II
 * <p>
 * https://leetcode-cn.com/problems/search-a-2d-matrix-ii
 * <p>
 * 编写一个高效的算法来搜索mxn矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * <p>
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *
 * @author yibo
 * @date 2021-10-25 09:39
 **/
public class Search2dMatrix2 {
    /**
     * 压缩矩阵搜索
     * <p>
     * 四角：左上为最小，右下为最大。
     * <p>
     * 搜索方法：从右上开始搜索，则row仅需增，col仅需减。
     *
     * @param matrix 矩阵
     * @param target 目标值
     * @return 是否能搜索到
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            int value = matrix[row][col];
            if (value == target) {
                return true;
            } else if (value < target) {
                // 值小，则行增
                row++;
            } else {
                // 值大，则列减
                col--;
            }
        }
        return false;
    }

    public boolean searchMatrix2(int[][] matrix, int target) {
        int maxCol = matrix[0].length - 1;
        int row;
        int l = 0;
        int lValue = matrix[l][0];
        if (lValue == target) {
            return true;
        } else if (lValue > target) {
            return false;
        } else {
            int r = matrix.length - 1;
            int rValue = matrix[r][0];
            if (rValue == target) {
                return true;
            } else {
                row = (l + r) / 2;
                while (l <= r) {
                    int rowValue = matrix[row][0];
                    if (rowValue < target) {
                        // 找到第一行比目标值小的后，开始逐列查找
                        int tmp = row;
                        while (tmp >= l && matrix[tmp][0] <= target) {
                            if (matrix[tmp][maxCol] == target) {
                                return true;
                            } else if (matrix[tmp][maxCol] > target) {
                                if (findInCol(matrix[tmp], target)) {
                                    return true;
                                }
                            }
                            tmp--;
                        }
                        tmp = row + 1;
                        while (tmp <= r && matrix[tmp][0] <= target) {
                            if (matrix[tmp][maxCol] == target) {
                                return true;
                            } else if (matrix[tmp][maxCol] > target) {
                                if (findInCol(matrix[tmp], target)) {
                                    return true;
                                }
                            }
                            tmp++;
                        }
                        return false;
                    } else if (rowValue == target) {
                        return true;
                    } else {
                        r = row - 1;
                        row = (l + r) / 2;
                    }
                }
            }
        }
        return false;
    }

    public boolean findInCol(int[] col, int target) {
        int l = 0;
        int r = col.length - 1;
        int index = (l + r) / 2;
        while (l <= r) {
            int colValue = col[index];
            if (colValue == target) {
                return true;
            } else if (colValue < target) {
                l = index + 1;
                index = (l + r) / 2;
            } else {
                r = index - 1;
                index = (l + r) / 2;
            }
        }
        return false;
    }
}
