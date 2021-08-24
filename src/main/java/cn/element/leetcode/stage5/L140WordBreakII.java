package cn.element.leetcode.stage5;

import java.util.*;

/**
 * 难度: HARD
 *
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，
 * 在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 * 说明：
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 *
 * 示例 1：
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 *  "cats and dog",
 *  "cat sand dog"
 * ]
 *
 * 示例 2：
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 *  "pine apple pen apple",
 *  "pineapple pen apple",
 *  "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 *
 * 示例3：
 * 输入:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出:
 * []
 */
public class L140WordBreakII {

    /**
     * 记忆化搜索
     */
    public List<String> wordBreak(String s, List<String> wordDict) {

        Map<Integer, List<List<String>>> map = new HashMap<>();

        List<List<String>> wordBreaks = backTrack(s, s.length(), new HashSet<>(wordDict), 0, map);

        List<String> breakList = new LinkedList<>();

        for (List<String> wordBreak : wordBreaks) {
            breakList.add(String.join(" ", wordBreak));
        }

        return breakList;
    }

    private List<List<String>> backTrack(String s, int length, HashSet<String> wordSet, int index, Map<Integer, List<List<String>>> map) {

        if(!map.containsKey(index)){
            List<List<String>> wordBreaks = new LinkedList<>();

            if(index == length){
                wordBreaks.add(new LinkedList<>());
            }

            for (int i = index + 1; i <= length; i++) {
                String word = s.substring(index, i);

                if(wordSet.contains(word)){
                    List<List<String>> nextWordBreaks = backTrack(s, length, wordSet, i, map);

                    for (List<String> nextWordBreak : nextWordBreaks) {
                        LinkedList<String> wordBreak = new LinkedList<>(nextWordBreak);

                        wordBreak.offerFirst(word);
                        wordBreaks.add(wordBreak);
                    }
                }
            }

            map.put(index, wordBreaks);
        }

        return map.get(index);
    }

    public static void main(String[] args) {

        L140WordBreakII a = new L140WordBreakII();

        String s = "pineapplepenapple";

        List<String> wordDict = new ArrayList<String>(){{
           add("apple");
           add("pen");
           add("applepen");
           add("pine");
           add("pineapple");
        }};

        System.out.println(a.wordBreak(s, wordDict));
    }
}
