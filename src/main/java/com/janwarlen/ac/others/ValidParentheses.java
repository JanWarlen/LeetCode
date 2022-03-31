package com.janwarlen.ac.others;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ValidParentheses {
    public boolean isValid(String s) {
        if (s.length() % 2 == 1) {
            return false;
        }
        Map<Character, Character> dic = new HashMap<>();
        dic.put(')', '(');
        dic.put(']', '[');
        dic.put('}', '{');
        Deque<Character> leftStack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ('(' == c || '[' == c || '{' == c) {
                leftStack.push(c);
            } else {
                // ) ] }
                if (leftStack.isEmpty() || leftStack.pop() != dic.get(c)) {
                    return false;
                }
            }
        }
        return leftStack.isEmpty();
    }
}
