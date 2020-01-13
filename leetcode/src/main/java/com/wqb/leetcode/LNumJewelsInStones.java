package com.wqb.leetcode;


/**
 * 771.宝石与石头
 *
 * @description:  给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 * <p>
 * J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
 * <p>
 * 来源：力扣（LeetCode）
 * 示例 1:
 * 输入: J = "aA", S = "aAAbbbb"
 * 输出: 3
 * <p>
 * 示例 2:
 * 输入: J = "z", S = "ZZ"
 * 输出: 0
 * @author: benwq
 * @Date: 2019/12/18
 */
public class LNumJewelsInStones {
    public int numJewelsInStones(String J, String S) {
        char[] jcharArr = J.toCharArray();
        char[] scharArr = S.toCharArray();
        int count = 0;
        int[] indexMap = new int[150];
        for (int i = 0; i < jcharArr.length; i++) {
            indexMap[jcharArr[i]] = indexMap[jcharArr[i]] + 1;
        }
        for (int i = 0; i < scharArr.length; i++) {
            count += indexMap[scharArr[i]];
        }
        return count;
    }

    public static void main(String[] args) {

    }
}
