package cn.element.leetcode.stage5;

import java.util.*;

/**
 * 字典wordList 中从单词 beginWord和 endWord 的 转换序列 是一个按下述规格形成的序列：
 *
 * 序列中第一个单词是 beginWord 。
 * 序列中最后一个单词是 endWord 。
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典wordList 中的单词。
 * 给你两个单词 beginWord和 endWord 和一个字典 wordList ，
 * 找到从beginWord 到endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0。
 *
 * 示例 1：
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：5
 * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
 *
 * 示例 2：
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * 输出：0
 * 解释：endWord "cog" 不在字典中，所以无法进行转换。
 */
public class L127WordLadder {

    Map<String, Integer> wordId = new HashMap<>();

    List<List<Integer>> edge = new ArrayList<>();

    int nodeNum = 0;

    /**
     * 广度优先搜索 + 优化建图
     * 本题要求的是最短转换序列的长度,看到最短肯定得想到的就是广度优先搜索,想到广度优先搜索,那么自然就是想到图结构
     * 本题并没有直接了当的给出图的模型,因此我们必须的把它想象成图模型
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        for (String word : wordList) {
            addEdge(word);
        }

        addEdge(beginWord);

        if (!wordId.containsKey(endWord)) {
            return 0;
        }

        int[] dis = new int[nodeNum];

        Arrays.fill(dis, Integer.MAX_VALUE);

        int beginId = wordId.get(beginWord), endId = wordId.get(endWord);

        dis[beginId] = 0;

        Queue<Integer> que = new LinkedList<Integer>();

        que.offer(beginId);

        while (!que.isEmpty()) {
            int x = que.poll();

            if (x == endId) {
                return dis[endId] / 2 + 1;
            }

            for (int it : edge.get(x)) {
                if (dis[it] == Integer.MAX_VALUE) {
                    dis[it] = dis[x] + 1;
                    que.offer(it);
                }
            }
        }

        return 0;
    }

    public void addEdge(String word) {

        addWord(word);

        int id1 = wordId.get(word);

        char[] array = word.toCharArray();

        int length = array.length;

        for (int i = 0; i < length; ++i) {
            char tmp = array[i];

            array[i] = '*';

            String newWord = new String(array);

            addWord(newWord);

            int id2 = wordId.get(newWord);

            edge.get(id1).add(id2);
            edge.get(id2).add(id1);

            array[i] = tmp;
        }
    }

    public void addWord(String word) {

        if (!wordId.containsKey(word)) {
            wordId.put(word, nodeNum++);
            edge.add(new ArrayList<Integer>());
        }
    }

    public static void main(String[] args) {

        L127WordLadder a = new L127WordLadder();

        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<String>(){{
           add("hot");
           add("dot");
           add("dog");
           add("lot");
           add("log");
        }};

        System.out.println(a.ladderLength(beginWord, endWord, wordList));
    }
}
