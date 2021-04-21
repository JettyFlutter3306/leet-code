package cn.element.leetcode.stage1;

/**
 * 实现strStr()函数。
 *
 * 给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。
 * 如果不存在，则返回 -1 。
 *
 * 说明：
 * 当needle是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当needle是空字符串时我们应当返回 0 。这与 C 语言的strstr()以及 Java 的indexOf()定义相符。
 *
 * 示例 1：
 * 输入：haystack = "hello", needle = "ll"
 * 输出：2
 *
 * 示例 2：
 * 输入：haystack = "aaaaa", needle = "bba"
 * 输出：-1
 *
 * 示例 3：
 * 输入：haystack = "", needle = ""
 * 输出：0
 */
public class L28ImplementStr {

    /**
     * BF算法
     * 前提条件 haystack.length() > needle.length()
     */
    public int strStr(String haystack, String needle) {

        int n1 = haystack.length();
        int n2 = needle.length();

        for (int i = 0; i <= n1 - n2; i++) {
            boolean flag = true;

            for (int j = 0; j < n2; j++) {
                if(haystack.charAt(i+j) != needle.charAt(j)){
                    flag = false;

                    break;
                }
            }

            if(flag){
                return i;
            }
        }

        return -1;
    }

    /**
     * KMP算法
     * Knuth-Morris-Pratt 算法的核心为前缀函数，记作 π(i)，其定义如下：
     * 对于长度为 mm 的字符串 ss，其前缀函数 π(i)(0 <= i < m)π(i)(0 ≤ i < m) 表示 ss 的子串 s[0:i]s[0:i] 的最长的相等的真前缀与真后缀的长度。
     * 特别地，如果不存在符合条件的前后缀，那么 π(i) = 0π(i)=0。其中真前缀与真后缀的定义为不等于自身的的前缀与后缀。
     * 我们举个例子说明：字符串 aabaaabaabaaab 的前缀函数值依次为 0,1,0,1,2,2,30,1,0,1,2,2,3。
     * π(0) = 0π(0)=0，因为 aa 没有真前缀和真后缀，根据规定为 00（可以发现对于任意字符串 \π(0)=0π(0)=0 必定成立）；
     * π(1) = 1π(1)=1，因为 aaaa 最长的一对相等的真前后缀为 aa，长度为 11；
     * π(2) = 0π(2)=0，因为 aabaab 没有对应真前缀和真后缀，根据规定为 00；
     * π(3) = 1π(3)=1，因为 aabaaaba 最长的一对相等的真前后缀为 aa，长度为 11；
     * π(4) = 2π(4)=2，因为 aabaaaabaa 最长的一对相等的真前后缀为 aaaa，长度为 22；
     * π(5) = 2π(5)=2，因为 aabaaaaabaaa 最长的一对相等的真前后缀为 aaaa，长度为 22；
     * π(6) = 3π(6)=3，因为 aabaaabaabaaab 最长的一对相等的真前后缀为 aabaab，长度为 33。
     * 有了前缀函数，我们就可以快速地计算出模式串在主串中的每一次出现。
     * @param haystack      模式串
     * @param needle        目标串
     * @return              索引
     */
    public int strStr1(String haystack, String needle) {

        int n = haystack.length();  //模式串的长度
        int m = needle.length();  //目标串的长度

        if(m == 0){
            return 0;
        }

        int[] pi = new int[m];  //定义存放前缀函数结果的数组

        for (int i = 1,j = 0; i < m; i++) {
            while(j > 0 && needle.charAt(i) != needle.charAt(j)){
                j = pi[j-1];
            }

            if(needle.charAt(i) == needle.charAt(j)){
                j++;
            }

            pi[i] = j;
        }

        for (int i = 0,j = 0; i < n; i++) {
            while(j > 0 && haystack.charAt(i) != needle.charAt(j)){
                j= pi[j-1];
            }

            if(haystack.charAt(i) == needle.charAt(j)){
                j++;
            }

            if(j == m){
                return i - m + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        L28ImplementStr a = new L28ImplementStr();

        System.out.println(a.strStr1("aaaaa","bba"));
        System.out.println(a.strStr1("hello","ll"));

    }
}
