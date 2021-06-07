package cn.element.leetcode.stage3;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个单词数组和一个长度maxWidth，重新排版单词，使其成为每行恰好有maxWidth个字符，且左右两端对齐的文本。
 * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格' '填充，使得每行恰好有 maxWidth个字符。
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 *
 * 说明:
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于maxWidth。
 * 输入单词数组 words至少包含一个单词。
 *
 * 示例1:
 * 输入:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * 输出:
 * [
 *  "This is an",
 *  "example of text",
 *  "justification. "
 * ]
 * 示例2:
 *
 * 输入:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * 输出:
 * [
 *  "What  must  be",
 *  "acknowledgment ",
 *  "shall be    "
 * ]
 * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
 *      因为最后一行应为左对齐，而不是左右两端对齐。
 *      第二行同样为左对齐，这是因为这行只包含一个单词。
 *
 * 示例3:
 * 输入:
 * words = ["Science","is","what","we","understand","well","enough","to","explain",
 *         "to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * 输出:
 * [
 *  "Science is what we",
 *   "understand well",
 *  "enough to explain to",
 *  "a computer. Art is",
 *  "everything else we",
 *  "do "
 * ]
 */
public class L68TextJustification {

    public List<String> fullJustify(String[] words, int maxWidth) {

        StringBuilder s = new StringBuilder();  // 先缓存好0 - maxWidth 个空格的空格字符串

        final String[] spaces = new String[maxWidth];

        for(int i = 0; i < maxWidth; i ++) {
            spaces[i] = s.toString();

            s.append(" ");
        }

        List<String> ans = new LinkedList();

        int currentLen = 0; // 当前长度

        List<String> currentLineWords = new LinkedList(); // 当前行的单词

        for (int i = 0; i < words.length; i ++) {
            String word = words[i];

            int currenWordWithSingleSpaceLength = word.length() + 1;  // 当前单词和一个空格的长度，因为至少一个空格

            if (currenWordWithSingleSpaceLength + currentLen - 1 > maxWidth) {  // 这里 -1 因为最后一个单词不需要空格
                processCurrentLine(ans, currentLineWords, spaces, maxWidth);  // 处理当前行的单词

                currentLineWords.clear();   // 清空，即转到下一行

                currentLen = 0; // 清空当前长度
            }

            currentLen += currenWordWithSingleSpaceLength;  // 累计单词长度

            currentLineWords.add(word); // 添加单词
        }

        processCurrentLine(ans, currentLineWords, spaces, maxWidth);  // 当前 List 里面可能还有剩余的单词

        String ss = ans.get(ans.size() - 1);  // 处理最后一行

        ans.remove(ans.size() - 1);  // 先删除

        ss = ss.replaceAll("\\s+", " ");    // 将空格改成单空格，

        ans.add(ss + spaces[maxWidth - ss.length()]);   // 添加，但不要忘记补齐

        return ans;
    }

    public void processCurrentLine(List<String> ans, List<String> currentLineWords, String[] spaces, int maxWidth) {

        if (currentLineWords.size() == 1) {
            String word = currentLineWords.get(0);

            ans.add(word + spaces[maxWidth - word.length()]);
        } else {
            int wordsLength = 0;    // 单词长度

            for(String word: currentLineWords) {  // 累计当前行长度
                wordsLength += word.length();
            }

            int spaceNumber = maxWidth - wordsLength;   // 空格数

            int gap = (spaceNumber) / (currentLineWords.size() - 1);    // 间隙

            String[] spaceWords = new String[currentLineWords.size() - 1];

            int left = spaceNumber - (gap * (currentLineWords.size() - 1));  // 多余的空格数

            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < currentLineWords.size(); j ++) {
                String word = currentLineWords.get(j);

                if (j == currentLineWords.size() - 1) {
                    sb.append(word); // 最后一行不需要空格
                } else {
                    sb.append(word).append((j < left ? spaces[gap + 1] : spaces[gap]));  // 如果在多余的空格下标前就 + 1
                }
            }

            ans.add(sb.toString());
        }
    }

    public static void main(String[] args) {

        L68TextJustification a = new L68TextJustification();

        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};

        int maxWidth = 16;

        List<String> list = a.fullJustify(words, maxWidth);

        list.forEach(System.out::println);

    }
}
