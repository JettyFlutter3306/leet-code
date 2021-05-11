package cn.element.leetcode.stage2;

import java.util.*;

/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 */
public class L49GroupAnagrams {

    /**
     * 思路:排序
     */
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String,List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] chars = str.toCharArray();

            Arrays.sort(chars);

            String key = new String(chars);

            List<String> list = map.getOrDefault(key,new ArrayList<>());

            list.add(str);

            map.put(key,list);
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {

        L49GroupAnagrams a = new L49GroupAnagrams();

        String[] strArr = {"eat", "tea", "tan", "ate", "nat", "bat"};

        List<List<String>> lists = a.groupAnagrams(strArr);

        lists.forEach(System.out::println);
    }
}
