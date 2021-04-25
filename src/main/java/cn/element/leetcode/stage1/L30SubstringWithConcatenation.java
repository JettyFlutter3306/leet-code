package cn.element.leetcode.stage1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个字符串s和一些长度相同的单词words。找出 s 中恰好可以由words 中所有单词串联形成的子串的起始位置。
 *
 * 注意子串要与words 中的单词完全匹配，中间不能有其他字符，但不需要考虑words中单词串联的顺序。
 *
 * 示例 1：
 * 输入：
 *   s = "barfoothefoobarman",
 *   words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 *
 * 示例 2：
 * 输入：
 *   s = "wordgoodgoodgoodbestword",
 *   words = ["word","good","best","word"]
 * 输出：[]
 */
public class L30SubstringWithConcatenation {

    public List<Integer> findSubstring(String s, String[] words) {

        List<Integer> res = new ArrayList<>();

        Map<String, Integer> wordsMap = new HashMap<>();

        if (s.length() == 0 || words.length == 0) {
            return res;
        }

        for (String word: words) {
            if (s.indexOf(word) < 0){  // 主串s中没有这个单词，直接返回空
                return res;
            }

            wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);// map中保存每个单词，和它出现的次数
        }

        // 每个单词的长度， 总长度
        int oneLen = words[0].length(), wordsLen = oneLen * words.length;

        // 主串s长度小于单词总和，返回空
        if (wordsLen > s.length()) {
            return res;
        }

        // 只讨论从0，1，...， oneLen-1 开始的子串情况，
        // 每次进行匹配的窗口大小为 wordsLen，每次后移一个单词长度，由左右窗口维持当前窗口位置
        for (int i = 0; i < oneLen; ++i) {

            int left = i, right = i, count = 0;  // 左右窗口

            Map<String, Integer> subMap = new HashMap<>();  // 统计每个符合要求的word


            while (right + oneLen <= s.length()) {  // 右窗口不能超出主串长度
                String word = s.substring(right, right + oneLen);  // 得到一个单词

                right += oneLen;  //右窗口右移

                // words[]中没有这个单词，那么当前窗口肯定匹配失败，直接右移到这个单词后面
                if (!wordsMap.containsKey(word)) {
                    left = right;

                    subMap.clear(); // 窗口内单词统计map清空，重新统计

                    count = 0;  // 符合要求的单词数清0
                } else {
                    subMap.put(word, subMap.getOrDefault(word, 0) + 1);  // 统计当前子串中这个单词出现的次数

                    count++;

                    /*
                     * 如果这个单词出现的次数大于words[]中它对应的次数，又由于每次匹配和words长度相等的子串
                     * 如 ["foo","bar","foo","the"]  "| foobarfoobar| foothe"
                     * 第二个bar虽然是words[]中的单词，但是次数抄了，那么右移一个单词长度后 "|barfoobarfoo|the"
                     * bar还是不符合，所以直接从这个不符合的bar之后开始匹配，也就是将这个不符合的bar和它之前的单词(串)全移出去
                     */
                    while(subMap.getOrDefault(word, 0) > wordsMap.getOrDefault(word, 0)) {
                        String w = s.substring(left, left + oneLen); // 从当前窗口字符统计map中删除从左窗口开始到数量超限的所有单词(次数减一)

                        subMap.put(w, subMap.getOrDefault(w, 0) - 1);

                        count--;  // 符合的单词数减一


                        left += oneLen;  // 左窗口位置右移
                    }

                    // 当前窗口字符串满足要求
                    if (count == words.length) res.add(left);
                }
            }
        }

        return res;
    }


    public static void main(String[] args) {

        L30SubstringWithConcatenation a = new L30SubstringWithConcatenation();

        String s = "barfoothefoobarman";

        String[] words = {"foo","bar"};

        System.out.println(a.findSubstring(s,words));

    }
}
