package cn.element.leetcode.stage3;

import java.util.ArrayList;
import java.util.List;

/**
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。
 * 即使有多个不同答案，你也只需要返回其中一种。
 * 格雷编码序列必须以 0 开头。
 *
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,3,2]
 * 解释:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 *
 * 对于给定的n，其格雷编码序列并不唯一。
 * 例如，[0,2,3,1]也是一个有效的格雷编码序列。
 *
 * 00 - 0
 * 10 - 2
 * 11 - 3
 * 01 - 1
 *
 * 示例2:
 * 输入: 0
 * 输出: [0]
 * 解释: 我们定义格雷编码序列必须以 0 开头。
 *       给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
 *       因此，当 n = 0 时，其格雷编码序列为 [0]。
 */
public class L89GrayCode {

    public List<Integer> grayCode(int n) {

        List<Integer> list = new ArrayList<Integer>(){{  //声明一个List集合,初始化添加元素0
            add(0);
        }};

        int head = 1;  //定义增量初始化为1

        for (int i = 0; i < n; i++) {
            for (int j = list.size() - 1; j >= 0; j--) {  //倒序遍历List
                list.add(head + list.get(j));  //给集合里面的每个元素加上head然后再加入到集合,等于是原有的元素只加了0,后来加入的元素都加上了head,进行移位
            }

            head <<=  1;  //每次循环都要扩大2倍
        }

        return list;
    }

    public static void main(String[] args) {

        L89GrayCode a = new L89GrayCode();

        System.out.println(a.grayCode(3));
    }
}
