package cn.element.leetcode.stage5;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 * 输入: [2,2,1]
 * 输出: 1
 *
 * 示例2:
 * 输入: [4,1,2,1,2]
 * 输出: 4
 */
public class L136SingleNumber {

    /**
     * 法一: 借助额外内存空间
     */
    public int singleNumber(int[] nums) {

        Map<Integer,Integer> map = new HashMap<>();

        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() == 1){
                return entry.getKey();
            }
        }

        return 0;
    }

    /**
     * 法二: 不使用额外内存空间
     * 使用 异或 运算
     *
     * a ⊕ a = 0;
     * a ⊕ 0 = a;
     *
     * 不同顺序的异或可以改变顺序,因为这是同级运算
     * a ⊕ b ⊕ c ⊕ b ⊕ c = a ⊕ (b ⊕ b) ⊕ (c ⊕ c) = a;
     */
    public int singleNumber1(int[] nums) {

        int single = 0;

        for (int i : nums) {
            single ^= i;
        }

        return single;
    }

    public static void main(String[] args) {

        L136SingleNumber a = new L136SingleNumber();

        int[] nums = {4,1,2,1,2};

        System.out.println(a.singleNumber1(nums));
    }
}
