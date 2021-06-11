package cn.element.leetcode.stage3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 * 输入:n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 */
public class L77Combinations {

    public List<List<Integer>> combine(int n, int k) {

        List<List<Integer>> lists = new ArrayList<>();

        Stack<Integer> stack = new Stack<>();

        this.backTrack(lists,stack,n,k,1);

        return lists;
    }

    /**
     * 论为什么 i <= n - (k - stack.size()) + 1
     * 举例: n = 7,k = 4
     * 当遍历到 4 的时候就可以停止了,没有继续下去的必要了,因为一个组合元素个数才是4个,就算遍历到5底下也不满足条件了,
     * 把 5 入栈底下得到的组合里面的元素只能有3个即 {5,6,7},不满足条件!
     */
    public void backTrack(List<List<Integer>> lists,Stack<Integer> stack,int n,int k,int index){

        if(stack.size() == k){
            lists.add(new ArrayList<>(stack));
        }else{
            for (int i = index; i <= n - (k - stack.size()) + 1; i++) {  //i <= n - (k - stack.size()) + 1 重要!!!
                if(!stack.empty() && stack.peek() >= i){
                    continue;
                }

                stack.push(i);

                backTrack(lists,stack,n,k,index + 1);

                stack.pop();
            }
        }
    }

    public static void main(String[] args) {

        L77Combinations a = new L77Combinations();

        List<List<Integer>> lists = a.combine(4, 2);

        lists.forEach(System.out::println);

    }
}
