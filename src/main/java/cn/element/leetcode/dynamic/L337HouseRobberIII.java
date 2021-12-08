package cn.element.leetcode.dynamic;

import cn.element.leetcode.common.TreeNode;
import cn.element.leetcode.common.TreeNodes;

import java.util.HashMap;
import java.util.Map;

/**
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
 * 这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。
 * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 示例 1:
 * 输入: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * 输出: 7
 * 解释:小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 *
 * 示例 2:
 * 输入: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * 输出: 9
 * 解释:小偷一晚能够盗取的最高金额= 4 + 5 = 9.
 */
public class L337HouseRobberIII {

    private final Map<TreeNode, Integer> f = new HashMap<>();
    private final Map<TreeNode, Integer> g = new HashMap<>();

    /**
     * 简化一下这个问题: 一颗二叉树,树上的每个点都有对应的权值,每个点
     * 有两种状态(选中和不选中),问在不能同事选中有父子关系的点的情况下
     * 能选中的点的最大权值是多少?
     *
     * 使用f(O)表示选择O节点的情况下,O节点的子树上被选择的节点的最大权值
     * 和;g(O)表示不选择O节点的情况下,O节点的子树上被选择的节点的最大权值和
     * l和r代表O的左右孩子
     *
     * 当O被选中时,O的左右孩子都不能选中,故O被选中的情况下树上被选中的最大权值
     * 和为l和r不被选中的最大权值和相加,即f(O) = g(l) + g(r)
     * 当O不被选中时,O的左右孩子可以被选中,也可以不被选中,对于O的某个具体的孩子x
     * 它对O的贡献是x被选中和不被选中情况下的权值和的较大值,故g(O) = max{f(l), g(l)} + max{f(r), g(r)}
     */
    public int rob(TreeNode root) {
        dfs(root);

        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        dfs(node.left);
        dfs(node.right);

        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0))
                + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }

    public static void main(String[] args) {
        L337HouseRobberIII a = new L337HouseRobberIII();

        Integer[] values = {3,2,3,null,3,null,1};

        TreeNode root = TreeNodes.createTreeNodeByLevel(values);

        TreeNodes.show(root);

        System.out.println(a.rob(root));
    }
}
