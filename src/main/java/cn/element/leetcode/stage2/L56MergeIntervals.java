package cn.element.leetcode.stage2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 *
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 示例2：
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class L56MergeIntervals {

    /**
     * 我们用数组 merged 存储最终的答案。
     * 首先，我们将列表中的区间按照左端点升序排序。然后我们将第一个区间加入 merged 数组中，并按顺序依次考虑之后的每个区间：
     * 如果当前区间的左端点在数组 merged 中最后一个区间的右端点之后，那么它们不会重合，
     * 我们可以直接将这个区间加入数组 merged 的末尾；
     * 否则，它们重合，我们需要用当前区间的右端点更新数组 merged 中最后一个区间的右端点，将其置为二者的较大值。
     */
    public int[][] merge(int[][] intervals) {

        if(intervals.length == 0){
            return new int[0][2];
        }

        Arrays.sort(intervals,(o1,o2) -> o1[0] - o2[0]);

        List<int[]> merged = new ArrayList<>();

        for (int[] interval : intervals) {
            int l = interval[0];
            int r = interval[1];

            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < l) {
                merged.add(new int[]{l, r});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], r);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {

        L56MergeIntervals a = new L56MergeIntervals();

        int[][] arr = {{1,3},{2,6},{8,10},{15,18}};

        int[][] merge = a.merge(arr);

        System.out.println(Arrays.deepToString(merge));

    }
}
