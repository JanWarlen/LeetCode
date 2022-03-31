package com.janwarlen.ac.arrayAndStrings;

import java.util.*;

public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> record = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
//            String s = Arrays.toString(chars);
            // String.valueOf 比 Arrays.toString 快是因为前者直接将String内部储存的char数组进行了赋值
            // 而后者是通过StringBuilder进行拼装转换的，存在运行效率差距
            String s = String.valueOf(chars);
            if (!record.containsKey(s)) {
                record.put(s, new ArrayList<>());
                res.add(record.get(s));
            }
            record.get(s).add(str);
        }
        return res;
    }
}
