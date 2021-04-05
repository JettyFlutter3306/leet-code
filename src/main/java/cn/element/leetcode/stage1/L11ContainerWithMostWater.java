package cn.element.leetcode.stage1;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。在坐标内画 n 条垂直线，垂直线 i的两个端点分别为(i,ai) 和 (i, 0) 。
 * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器。
 *
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为49。
 *
 * 示例 2：
 *
 * 输入：height = [1,1]
 * 输出：1
 *
 * 示例 3：
 *
 * 输入：height = [4,3,2,1,4]
 * 输出：16
 *
 * 示例 4：
 *
 * 输入：height = [1,2,1]
 * 输出：2
 */
public class L11ContainerWithMostWater {

    /**
     * 暴力解法,肯定pass掉,只当作一个思路
     * 面试场上用基本上是不得分的,因为时间复杂度为O(n²)
     * 不推荐!
     */
    public int maxArea1(int[] height) {

        int max = 0;

        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                max = Math.max(max,Math.min(height[i],height[j]) * (j - i));
            }
        }

        return max;
    }

    /**
     * 双指针法,推荐!
     * 移动两头的题目可以优先考虑双指针法!
     * 每次移动较短的边,不可能移动较长的边的,因为移动较长的边必定盛水量减小
     * 所以要移动较短边
     * 思考: 为何双指针法是正确的?
     *
     * 因为假设短边不动移动长边的话得到的盛水量必定比原来的小或者不变,因为盛水量取决于短板
     * 而不是长板,因此想要获得盛水量最大值显然要移动短板这样才能让盛水量有概率变大,移动长板必定变小或者不变
     * 着这样就不需要考虑移动长板的情况了,省略了好多种不必要判断的情形,时间复杂度就降低了!
     */
    public int maxArea2(int[] height) {

        int max = 0;  //最大值
        int left = 0,right = height.length - 1;  //定义左指针和右指针,分别置于数组两端

        while(left < right){
            max = Math.max(max,Math.min(height[left],height[right]) * (right - left));

            if(height[left] < height[right]){  //比较两个边的大小,谁小就移动谁
                left++;
            }else{
                right--;
            }
        }


        return max;
    }

    public static void main(String[] args) {

        L11ContainerWithMostWater a = new L11ContainerWithMostWater();

        int[] arr = {1,8,6,2,5,4,8,3,7};

        System.out.println(a.maxArea2(arr));
    }




}
