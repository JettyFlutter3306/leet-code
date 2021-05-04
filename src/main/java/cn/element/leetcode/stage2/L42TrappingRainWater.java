package cn.element.leetcode.stage2;

import java.util.Stack;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，
 * 可以接 6 个单位的雨水（蓝色部分表示雨水）。
 *
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 */
public class L42TrappingRainWater {

    /**
     * 1.暴力解法:
     * 初始化ans = 0
     * 从左向右扫描数组
     * 初始化maxLeft = 0 & maxRight = 0
     * 从当前元素像左扫描并更新 maxLeft = max(maxLeft,height[j])
     * 从当前元素向右扫描并更新 maxRight = max(maxRight,height[j])
     * 将 min(maxLeft,right) - height[i] 累加到 ans
     */
    public int trap(int[] height) {

        int ans = 0;  //最终结果

        int size = height.length;  //数组长度

        for (int i = 1; i < size - 1; i++) {  //从 i = 1 开始遍历到 i = size - 1 也就是第二个和倒数第二个
            int maxLeft = 0;
            int maxRight = 0;

            for (int j = i; j >= 0; j--) {
                maxLeft = Math.max(maxLeft,height[j]);
            }

            for (int j = i; j < size; j++) {
                maxRight = Math.max(maxRight,height[j]);
            }

            ans += Math.min(maxLeft,maxRight) - height[i];
        }

        return ans;
    }

    /**
     * 2.动态编程法:
     * 直观想法,在暴力解法中,仅仅是为了找到最大值每次都要向左和向右扫描一次,但是我们可以存储这个值,因此可以用动态编程方法解决
     * 从数组中从下标 i 到最左端最高的条形块高度 leftMax
     * 从数组中从下标 i 到最右端最高的条形块高度 rightMax
     * 扫描数组 height 并更新答案:
     * 累加 min(maxLeft[i],maxRight[i]) - height[i] 到 ans 上
     */
    public int trap1(int[] height){

        if(height == null || height.length == 0){
            return 0;
        }

        int ans = 0;  //最终累加结果

        int size = height.length;  //数组长度

        int[] leftMax = new int[size];
        int[] rightMax = new int[size];

        leftMax[0] = height[0];

        for (int i = 1; i < size; i++) {
            leftMax[i] = Math.max(height[i],leftMax[i-1]);
        }

        rightMax[size-1] = height[size-1];

        for (int i = size - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i],rightMax[i+1]);
        }

        for (int i = 1; i < size - 1; i++) {
            ans += Math.min(leftMax[i],rightMax[i]) - height[i];
        }

        return ans;
    }

    /**
     * 3.使用栈:
     * 直观想法,我们可以不用像动态编程那样存储最大高度,而是使用栈来跟踪可能储水的最长的条形块,使用栈可以在一次遍历内
     * 完成计算
     * 使用栈来存储条形快的索引下标
     * 遍历数组:
     *      当栈非空且height[current] > height[st.peek()]
     *      意味着栈中元素可以被弹出,弹出栈顶元素 top
     *      计算当前元素和栈顶元素的距离,准备进行填充 distance = current - st.peek() - 1
     *      找出界定高度 boundedHeight = min(height[current],height[st.peek()]) = height[top]
     *      往答案中累加积水量 ans += distance * boundedHeight
     *      将当期索引下标入栈
     *      将current移动到下个位置
     */
    public int trap2(int[] height){

        int ans = 0,current = 0;

        Stack<Integer> stack = new Stack<>();

        while(current < height.length){
            while(!stack.empty() && height[current] > height[stack.peek()]){
                int top = stack.pop();

                if(stack.empty()){
                    break;
                }

                int distance = current - stack.peek() - 1;

                int boundedHeight = Math.min(height[current],height[stack.peek()]) - height[top];

                ans += distance * boundedHeight;
            }

            stack.push(current++);
        }

        return ans;
    }

    public int trap3(int[] height) {
        int left = 0, right = height.length - 1;

        int ans = 0;

        int left_max = 0, right_max = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= left_max) {
                    left_max = height[left];
                } else {
                    ans += (left_max - height[left]);
                }

                left++;
            } else {
                if (height[right] >= right_max) {
                    right_max = height[right];
                } else {
                    ans += (right_max - height[right]);
                }

                right--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {

        L42TrappingRainWater a = new L42TrappingRainWater();

        int[] arr = {0,1,0,2,1,0,1,3,2,1,2,1};

        System.out.println(a.trap3(arr));
    }

}
