package cn.element.leetcode.stage5;

import java.util.HashMap;
import java.util.Map;

/**
 * 难度: HARD
 *
 * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
 *
 * 示例 1：
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：3
 *
 * 示例 2：
 * 输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * 输出：4
 */
public class L149MaxPointsOnALine {

    /**
     * 考虑使用哈希表法
     * 枚举所有的点,假设该直线经过该点时,该直线所能经过的最多的点数
     * 假设我们当前枚举到点i,如果直线同时经过另外两个不同的点j和k,那么可以发现点i和j所连直线的
     * 斜率恰等于点i和点k所连直线的斜率
     *
     * 于是我们可以统计其他所有点与点i所连直线的斜率,出现次数最多的斜率即为经过点数最多的直线的斜率
     * 其经过的点数为该斜率出现的次数加1
     */
    public int maxPoints(int[][] points) {

        int n = points.length;

        if (n <= 2) {
            return n;
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (ans >= n - i || ans > n / 2) {
                break;
            }

            Map<Integer, Integer> map = new HashMap<>();

            for (int j = i + 1; j < n; j++) {
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];

                if (x == 0) {
                    y = 1;
                } else if (y == 0) {
                    x = 1;
                } else {
                    if (y < 0) {
                        x = -x;
                        y = -y;
                    }

                    int gcdXY = gcd(Math.abs(x), Math.abs(y));

                    x /= gcdXY;
                    y /= gcdXY;
                }

                int key = y + x * 20001;

                map.put(key, map.getOrDefault(key, 0) + 1);
            }

            int max = 0;

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int num = entry.getValue();

                max = Math.max(max, num + 1);
            }

            ans = Math.max(ans, max);
        }

        return ans;
    }

    public int gcd(int a, int b) {

        return b != 0 ? gcd(b, a % b) : a;
    }

    public static void main(String[] args) {

        L149MaxPointsOnALine a = new L149MaxPointsOnALine();

        int[][] points = {
                {1,1},
                {2,2},
                {3,3}
        };

        System.out.println(a.maxPoints(points));
    }

}
