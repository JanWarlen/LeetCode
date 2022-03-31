package com.janwarlen.recursion.second;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * Example 2:
 *
 * Input: n = 1
 * Output: ["()"]
 */
public class GenerateParentheses {

    /**
     * 递归回溯解法:
     * 思路有些类似于八皇后的解法
     */
    public static List<String> generateParenthesis_Backtracking(int n) {
        List<String> res = new ArrayList<>();
        res = solution(n, 0, res, new char[2 * n]);
        return res;
    }

    private static List<String> solution(int n, int now, List<String> res, char[] str) {
        for (int i = 0; i < 2; i++) {
            char ele = i == 0 ? '(' : ')';
            // 添加 (
            str[now] = ele;
            if (checkRules(str, n, now)) {
                if (now == str.length - 1) {
                    res.add(new String(str));
                } else {
                    solution(n, now + 1, res, str);
                }
                // 回溯
            }
        }
        return res;
    }

    /**
     * 校验括号规则
     * - (或)数量不超过n
     * - 符合数学规则, 即 ) 在 ( 左侧, ) 的数量必须小于等于 ( 的数量
     */
    private static boolean checkRules(char[] str, int n, int now) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < now + 1; i++) {
            if (str[i] == '(') {
                left++;
            } else if (str[i] == ')') {
                right++;
            }
            if (right > left || left > n) {
                return false;
            }
        }
        return true;
    }

    /**
     * 通过迭代的方式，即当前函数窗口内循环计算
     * 网友给出解法，个人认为是循环最优解，思路应该是基于 动态规划
     */
    public static List<String> generateParenthesis(int n) {
        List<String>[]  result = new List[n+1];
        result[0] = Arrays.asList("");
        for(int i=1; i<=n; i++){
            List<String> r = new ArrayList<String>();
            for(int j=0; j<i; j++){
                for(String sl : result[j])
                    for(String sr : result[i-j-1])
                        r.add("(" + sl + ")" + sr);
            }
            result[i] = r;
        }
        return result[n];
    }

    public static void main(String[] args) {
        generateParenthesis(3).stream().forEach(item -> {
            System.out.println(item);
        });
    }
}
