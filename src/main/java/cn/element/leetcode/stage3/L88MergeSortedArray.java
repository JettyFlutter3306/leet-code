package cn.element.leetcode.stage3;

import java.util.Arrays;

/**
 * 给你两个有序整数数组nums1 和 nums2，
 * 请你将 nums2 合并到nums1中，使 nums1 成为一个有序数组。
 * 初始化nums1 和 nums2 的元素数量分别为m 和 n 。
 * 你可以假设nums1 的空间大小等于m + n，这样它就有足够的空间保存来自 nums2 的元素。
 *
 * 示例 1：
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 *
 * 示例 2：
 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
 * 输出：[1]
 */
public class L88MergeSortedArray {

    /**
     * 法一: 先拷贝后排序  时间复杂度 O((m + n) * log(m + n))
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        System.arraycopy(nums2,0,nums1,m,n);

        Arrays.sort(nums1);
    }

    /**
     * 法二: 逆向双指针
     */
    public void merge1(int[] nums1, int m, int[] nums2, int n) {

        int p1 = m - 1;  //定义指针p1指向nums1[m - 1]
        int p2 = n - 1;  //定义指针p2指向nums2[n - 1];

        int tail = m + n - 1;  //临时索引,指向nums1末尾
        int cur;  //临时结果变量

        while(p1 >= 0 || p2 >= 0){
            if(p1 == -1){
                cur = nums2[p2--];
            }else if(p2 == -1){
                cur = nums1[p1--];
            }else if(nums1[p1] > nums2[p2]){
                cur = nums1[p1--];
            }else{
                cur = nums2[p2--];
            }

            nums1[tail--] = cur;
        }

    }

    public static void main(String[] args) {

        L88MergeSortedArray a = new L88MergeSortedArray();

        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};

        a.merge1(nums1,3,nums2,3);

        System.out.println(Arrays.toString(nums1));

    }
}
