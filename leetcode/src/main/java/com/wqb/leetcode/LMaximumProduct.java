package com.wqb.leetcode;

import java.util.Arrays;

/**
 * @description: 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 * 注意:
 * 给定的整型数组长度范围是[3,104]，数组中所有的元素范围是[-1000, 1000]。
 * 输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。
 * @author: benwq
 * @Date: 2019/12/23
 */
public class LMaximumProduct {

    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        return Math.max(nums[0]*nums[1]*nums[nums.length - 1],nums[nums.length - 1]*nums[nums.length - 2]*nums[nums.length - 3]);
    }

    public static void main(String[] args) {
        int[] nums = new int[3];
        nums[0] = 2;
        nums[1] = -3;
        nums[2] = 1;
        LMaximumProduct lMaximumProduct = new LMaximumProduct();
        lMaximumProduct.maximumProduct(nums);
    }
}
