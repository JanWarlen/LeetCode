package com.janwarlen.recursion.second;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string containing digits from 2-9 inclusive,
 * return all possible letter combinations that the number could represent.
 * Return the answer in any order.
 * <p>
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * 2-a,b,c
 * 3-d,e,f
 * 4-g,h,i
 * 5-j,k,l
 * 6-m,n,o
 * 7-p,q,r,s
 * 8-t,u,v
 * 9-w,x,y,z
 * <p>
 * Example 1:
 * <p>
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * <p>
 * Example 2:
 * <p>
 * Input: digits = ""
 * Output: []
 * <p>
 * Example 3:
 * <p>
 * Input: digits = "2"
 * Output: ["a","b","c"]
 * <p>
 * Constraints:
 * <p>
 * 0 <= digits.length <= 4
 * digits[i] is a digit in the range ['2', '9'].
 */
public class LetterCombinationsOfAPhoneNumber {

    public static List<String> letterCombinations(String digits) {
        if (null == digits || digits.length() == 0) {
            return new ArrayList<>();
        }
        List<String[]> borad = init();
        List<String> res = new ArrayList<>();
        solution(borad, res, digits, 0, new StringBuilder());
        return res;
    }

    private static void solution(List<String[]> borad, List<String> res, String digits, int i, StringBuilder comb) {
        if (i == digits.length()) {
            // 结束组合
            res.add(comb.toString());
            return;
        }
        int idx = digits.codePointAt(i) - 50;
        String[] strings = borad.get(idx);
        for (int j = 0; j < strings.length; j++) {
            comb.append(strings[j]);
            solution(borad, res, digits, i + 1, comb);
            comb.deleteCharAt(i);
        }
    }

    /**
     * 2-a,b,c
     * 3-d,e,f
     * 4-g,h,i
     * 5-j,k,l
     * 6-m,n,o
     * 7-p,q,r,s
     * 8-t,u,v
     * 9-w,x,y,z
     */
    private static List<String[]> init() {
        List<String[]> integers = new ArrayList<>();
        integers.add(new String[]{"a", "b", "c"});
        integers.add(new String[]{"d", "e", "f"});
        integers.add(new String[]{"g", "h", "i"});
        integers.add(new String[]{"j", "k", "l"});
        integers.add(new String[]{"m", "n", "o"});
        integers.add(new String[]{"p", "q", "r", "s"});
        integers.add(new String[]{"t", "u", "v"});
        integers.add(new String[]{"w", "x", "y", "z"});
        return integers;
    }

    public static void main(String[] args) {
        letterCombinations("23").stream().forEach(e -> System.out.println(e));
    }
}
