package cn.element.leetcode.stage4;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。
 * 你可以按任何顺序返回答案。
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，
 * 但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 *
 * 示例 1：
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 *
 * 示例 2：
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 *
 * 示例 3：
 * 输入：s = "1111"
 * 输出：["1.1.1.1"]
 *
 * 示例 4：
 * 输入：s = "010010"
 * 输出：["0.10.0.10","0.100.1.0"]
 *
 * 示例 5：
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 */
public class L93RestoreIpAddresses {

    /**
     * 递归 + 深度遍历
     */
    public List<String> restoreIpAddresses(String s) {

        int len = s.length();

        List<String> result = new ArrayList<>();

        if(len < 4 || len > 12){  //如果长度不够,那么就不搜索
            return result;
        }

        Deque<String> path = new ArrayDeque<>(4);

        int splitTimes = 0;

        dfs(s, len, splitTimes, 0, path, result);

        return result;
    }

    /**
     * 判断s的子区间[left,right]能否成为一个ip段
     * 判断的同时顺便把类型转换一下
     */
    private int judgeIpSegment(String s, int left, int right) {

        int len = right - left + 1;

        if(len > 1 && s.charAt(left) == '0'){  //大于1的时候,不能以0开头
            return -1;
        }

        int res = 0;

        for (int i = left; i <= right; i++) {
            res = res * 10 + s.charAt(i) - '0';
        }

        if(res > 255){
            return -1;
        }

        return res;
    }

    private void dfs(String s, int len, int split, int begin, Deque<String> path, List<String> res) {

        if(begin == len){
            if(split == 4){
                res.add(String.join(".",path));
            }

            return;
        }

        //因为ip地址至少得要是4个数字,最多只有12个数字
        if(len - begin < (4 - split) || len - begin > 3 * (4 - split)){  //看到剩下的不够了,就退出(剪枝),len-begin表示剩余的还未分割的字符串数量
            return;
        }

        for (int i = 0; i < 3; i++) {
            if(begin + i >= len){
                break;
            }

            int ipSegment = judgeIpSegment(s, begin, begin + i);

            if(ipSegment != -1){
                path.addLast(ipSegment + "");  //在判断ip分段情况下,采取做截取

                dfs(s, len, split + 1, begin + i + 1, path, res);

                path.removeLast();
            }
        }
    }

    public static void main(String[] args) {

        L93RestoreIpAddresses a = new L93RestoreIpAddresses();

        System.out.println(a.restoreIpAddresses("25525511135"));
    }
}