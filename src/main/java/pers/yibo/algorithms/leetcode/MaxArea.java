package pers.yibo.algorithms.leetcode;

/**
 * 盛最多水的容器
 *
 * @author yibo
 * @date 2021-08-31 16:48
 * @link https://leetcode-cn.com/problems/container-with-most-water/
 **/
public class MaxArea {

    public static int maxArea(int[] height) {
        int maxArea = 0;
        int left=0;
        int right=height.length-1;
        while(left<right){
            int leftH=height[left];
            int rightH=height[right];
            if(leftH<rightH){
                maxArea=Math.max(maxArea,(right-left)*leftH);
                left++;
            }else{
                maxArea=Math.max(maxArea,(right-left)*rightH);
                right--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] height = new int[]{1,3,2,5,25,24,5};
        System.out.println(maxArea(height));
    }
}
