package com.janwarlen.ac.arrayAndStrings;

public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            // 针对长度为奇数的回文子串
            int len1 = expandAroundCenter(s, i, i);
            // 针对长度为偶数的回文子串
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    /**
     * 动态规划
     */
    public String longestPalindromeDy(String s) {
        if (s == null || "".equals(s)) {
            return s;
        }
        int len = s.length();
        String ans = "";
        int max = 0;
        boolean[][] dp = new boolean[len][len];
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                boolean judge = s.charAt(i) == s.charAt(j);
                dp[i][j] = j - i > 2 ? dp[i + 1][j - 1] && judge : judge;
                if (dp[i][j] && j - i + 1 > max) {
                    max = j - i + 1;
                    ans = s.substring(i, j + 1);
                }
            }
        }
        return ans;
    }

    // 预处理字符串，在两个字符之间加上#
    private static String preHandleString(String s) {
        StringBuffer sb = new StringBuffer();
        int len = s.length();
        sb.append('#');
        for(int i = 0; i < len; i++) {
            sb.append(s.charAt(i));
            sb.append('#');
        }
        return sb.toString();
    }

    // 寻找最长回文字串
    // manacher
    public static String findLongestPlalindromeString(String s) {
        // 先预处理字符串
        String str = preHandleString(s);
        // 处理后的字串长度
        int len = str.length();
        // 右边界
        int rightSide = 0;
        // 右边界对应的回文串中心
        int rightSideCenter = 0;
        // 保存以每个字符为中心的回文长度一半（向下取整）
        int[] halfLenArr = new int[len];
        // 记录回文中心
        int center = 0;
        // 记录最长回文长度
        int longestHalf = 0;
        for(int i = 0; i < len; i++) {
            // 是否需要中心扩展
            boolean needCalc = true;
            // 如果在右边界的覆盖之内
            if(rightSide > i) {
                // 计算相对rightSideCenter的对称位置
                int leftCenter = 2 * rightSideCenter - i;
                // 根据回文性质得到的结论
                halfLenArr[i] = halfLenArr[leftCenter];
                // 如果超过了右边界，进行调整
                if(i + halfLenArr[i] > rightSide) {
                    halfLenArr[i] = rightSide - i;
                }
                // 如果根据已知条件计算得出的最长回文小于右边界，则不需要扩展了
                if(i + halfLenArr[leftCenter] < rightSide) {
                    // 直接推出结论
                    needCalc = false;
                }
            }
            // 中心扩展
            if(needCalc) {
                while(i - 1 - halfLenArr[i] >= 0 && i + 1 + halfLenArr[i] < len) {
                    if(str.charAt(i + 1 + halfLenArr[i]) == str.charAt(i - 1 - halfLenArr[i])) {
                        halfLenArr[i]++;
                    } else {
                        break;
                    }
                }
                // 更新右边界及中心
                rightSide = i + halfLenArr[i];
                rightSideCenter = i;
                // 记录最长回文串
                if(halfLenArr[i] > longestHalf) {
                    center = i;
                    longestHalf = halfLenArr[i];
                }
            }
        }
        // 去掉之前添加的#
        StringBuffer sb = new StringBuffer();
        for(int i = center - longestHalf + 1; i <= center + longestHalf; i += 2) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String test = "babad";
        String test2 = "ac";
        String test3 = "abb";
        String test4 = "ccd";
        System.out.println(findLongestPlalindromeString(test));
        System.out.println(findLongestPlalindromeString(test2));
        System.out.println(findLongestPlalindromeString(test3));
        System.out.println(findLongestPlalindromeString(test4));
    }
}
