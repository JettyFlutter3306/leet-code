package cn.element.leetcode.stage2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 * 示例1：
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 *
 * 示例 2：
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10]重叠。
 *
 * 示例 3：
 * 输入：intervals = [], newInterval = [5,7]
 * 输出：[[5,7]]
 *
 * 示例 4：
 * 输入：intervals = [[1,5]], newInterval = [2,3]
 * 输出：[[1,5]]
 *
 * 示例 5：
 * 输入：intervals = [[1,5]], newInterval = [2,7]
 * 输出：[[1,7]]
 */
public class L57InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {

        int left = newInterval[0];  //获取待插入区间的左值
        int right = newInterval[1];  //获取待插入区间的右值

        boolean placed = false;  //可否放置的标记

        List<int[]> list = new ArrayList<>();  //声明一个List用于存放结果

        for (int[] interval : intervals) {  //遍历区间数组
            if(interval[0] > right){  //在插入区间的右侧且无交集
                if(!placed){
                    list.add(new int[]{left,right});

                    placed = true;
                }

                list.add(interval);
            }else if(interval[1] < left){  //在插入区间的左侧且无交集
                list.add(interval);
            }else{  //与插入区间有交集,计算它们的并集
                left = Math.min(left,interval[0]);

                right = Math.max(right,interval[1]);
            }
        }

        if(!placed){
            list.add(new int[]{left,right});  //待插入的区间比原区间数组任意一个区间都要大,那么就直接把待插入区间放入List
        }

        return list.toArray(new int[list.size()][2]);  //把list转成数组返回
    }

    public static void main(String[] args) {

        L57InsertInterval a = new L57InsertInterval();

        int[][] intervals = {
                {1,2},
                {3,5},
                {6,7},
                {8,10},
                {12,16}
        };

        int[] newInterval = {4,8};

        int[][] temp = a.insert(intervals, newInterval);

        System.out.println(Arrays.deepToString(temp));


    }
}
