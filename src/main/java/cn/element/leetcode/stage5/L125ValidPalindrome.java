package cn.element.leetcode.stage5;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 解释："amanaplanacanalpanama" 是回文串
 *
 * 示例 2:
 * 输入: "race a car"
 * 输出: false
 * 解释："raceacar" 不是回文串
 */
public class L125ValidPalindrome {

    /**
     * 筛选 + 判断
     */
    public boolean isPalindrome(String s) {

        StringBuilder sb = new StringBuilder();

        int len = s.length();

        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);

            if(Character.isLetterOrDigit(ch)){
                sb.append(Character.toLowerCase(ch));
            }
        }

        StringBuilder sbRev = new StringBuilder(sb).reverse();

        return sb.toString().equals(sbRev.toString());
    }

    /**
     * 使用双指针
     */
    public boolean isPalindrome1(String s) {

        int n = s.length();

        int left = 0;
        int right = n - 1;

        while(left < right){
            while(left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            while(left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            if(left < right){
                if(Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))){
                    return false;
                }

                left++;
                right--;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        L125ValidPalindrome a = new L125ValidPalindrome();

        String s = "race a car";

        System.out.println(a.isPalindrome(s));
    }
}
