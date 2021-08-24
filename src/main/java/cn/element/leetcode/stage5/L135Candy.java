package cn.element.leetcode.stage5;

/**
 * 老师想给孩子们分发糖果，有 N个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 * 每个孩子至少分配到 1 个糖果。
 * 评分更高的孩子必须比他两侧的邻位孩子获得更多的糖果。
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 *
 * 示例1：
 * 输入：[1,0,2]
 * 输出：5
 * 解释：你可以分别给这三个孩子分发 2、1、2 颗糖果。
 *
 * 示例2：
 * 输入：[1,2,2]
 * 输出：4
 * 解释：你可以分别给这三个孩子分发 1、2、1 颗糖果。
 *      第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
 */
public class L135Candy {

    /**
     * 两次遍历
     * 我们可以将相邻的孩子中,评分高的孩子必须获得更多的糖果,这句话拆分为两个规则,分别处理
     * 左规则: 当ratings[i - 1] < ratings[i],i号学生的糖果数量将比i - 1号孩子的糖果数量多
     * 右规则: 当ratings[i] > ratings[i + 1],i号学生的糖果数量将比i + 1号孩子的糖果数量多
     * 我们遍历该数组两次,处理出每一个学生分别满足左规则或右规则时,最少需要被分得的糖果数量,每个人
     * 最终分得的糖果数量即为这两个数量的最大值
     *
     * 具体地,以左规则为例,我们从左到右遍历数组,假设当前遍历到位置i,如果有ratings[i - 1] < ratings[i]
     * 那么i号学生的糖果数量将比i - 1号孩子的糖果数量多,我们令left[i] = left[i - 1] + 1即可,否则我们令
     * left[i] = 1
     */
    public int candy(int[] ratings) {

        int n = ratings.length;
        int[] left = new int[n];

        for (int i = 0; i < n; i++) {
            if(i > 0 && ratings[i] > ratings[i - 1]){
                left[i] = left[i - 1] + 1;
            }else{
                left[i] = 1;
            }
        }

        int right = 0;
        int ret = 0;

        for (int i = n - 1; i >= 0; i--) {
            if(i < n - 1 && ratings[i] > ratings[i + 1]){
                right++;
            }else{
                right = 1;
            }

            ret += Math.max(left[i], right);
        }

        return ret;
    }

    public static void main(String[] args) {

        L135Candy a = new L135Candy();

        int[] ratings = {1,0,2};

        System.out.println(a.candy(ratings));
    }
}
