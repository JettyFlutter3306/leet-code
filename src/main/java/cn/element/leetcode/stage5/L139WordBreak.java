package cn.element.leetcode.stage5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 *
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 *
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *       注意你可以重复使用字典中的单词。
 *
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 */
public class L139WordBreak {

    /**
     * 考虑使用 动态规划
     * 我们定义 dp[i] 表示字符串 s 前 i 个字符组成的字符串 s[0...i-1] 是否能被空格拆分成若干字典中出现的单词
     * 从前往后计算考虑转移方程,每次转移的时候我们需要枚举包含位置i-1的最后一个单词,看它是否出现字典中以及除去这部分的
     * 字符串是否合法即可,公式化来说,我们需要枚举s[0...i-1]中的分割点j,看s[0...j-1]组成的字符串s1(默认j=0时s1为空串)
     * 和s[j...i-1]组成的字符串s2是否合法,如果两个字符串均合法,那么按照定义s1和s2拼接成的字符串也同样合法,由于计算到
     * dp[i]时我们已经计算了dp[0...i-1]的值,因此字符串s1是否合法可以直接由dp[j]得知,剩下的我们只需要看s2是否合法即可
     * 因此得到状态转移方程:
     *              dp[i] = dp[j] && check(s[j...i-1])
     * 其中check(s[j...i-1])表示子串s[j...i-1]是否出现在字典中
     */
    public boolean wordBreak(String s, List<String> wordDict) {

        Set<String> wordDictSet = new HashSet<>(wordDict);

        boolean[] dp = new boolean[s.length() + 1];

        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
               if(dp[j] && wordDictSet.contains(s.substring(j, i))){
                   dp[i] = true;
                   break;
               }
            }
        }

        return dp[s.length()];
    }

    public static void main(String[] args) {

        L139WordBreak a = new L139WordBreak();

        String s = "leetcode";

        List<String> wordDict = new ArrayList<String>(){{
           add("leet");
           add("code");
        }};

        System.out.println(a.wordBreak(s, wordDict));
    }
}
