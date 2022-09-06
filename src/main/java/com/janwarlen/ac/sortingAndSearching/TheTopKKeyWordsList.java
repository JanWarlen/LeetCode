package com.janwarlen.ac.sortingAndSearching;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

// https://www.lintcode.com/problem/1883
public class TheTopKKeyWordsList {
    /**
     * @param k:        an integer
     * @param keywords: a list of string
     * @param reviews:  a list of string
     * @return: return the top k keywords list
     */
    public static List<String> topkKeywords(int k, String[] keywords, String[] reviews) {
        // write your code here
        if (null == reviews) {
            return new ArrayList<>();
        }
        HashMap<String, Integer> map = new HashMap<>();
        Pattern compile = Pattern.compile("[a-z]+");
        for (String review : reviews) {
            Matcher matcher = compile.matcher(review.toLowerCase());
            HashSet<String> strings = new HashSet<>();
            while (matcher.find()) {
                strings.add(matcher.group());
            }
            for (String string : strings) {
                map.put(string, map.getOrDefault(string, 0) + 1);
            }
        }
        HashMap<String, Integer> res = new HashMap<>();
        for (String keyword : keywords) {
            res.put(keyword, map.getOrDefault(keyword, 0));
        }

        return res.entrySet().stream()
                .sorted(Collections.reverseOrder((o1, o2) -> {
                    int i = o1.getValue().compareTo(o2.getValue());
                    if (i == 0) {
                        // 同等次数还需要根据关键字的字典排序
                        String key1 = o1.getKey();
                        String key2 = o2.getKey();
                        int com = 0;
                        int index = 0;
                        while (com == 0) {
                            com = key2.charAt(index) - key1.charAt(index++);
                        }
                        return com;
                    }
                    return i;
                }))
                .limit(k).map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int k = 12;
        String[] keywords = {"qebqhd","mferd","glhc","axos","hxxsx","xvtj","dajth","slokx"};
        String[] reviews = {
                "YlCtq uFC Dajth hxxsx WUgjg dajth qlOi VqVQQN Slokx Axos COLKc ESXLTt","SJhv KonM PDkiEc ZKoQIt slokx AlmbD xvtj HhOGAc cYXC JCFYo Glhc","qebqhd viy xvtj NTnL FMT axos UZx dajth HKa Qebqhd","JsrgPD qGGD byBTA jJMV axos vtL MNE axos qWkYI jsj qebqhd Slokx axos ucD","vhQjyf qfoAM bZY FxgnF axos Bojq Axos lbmH HsxboA NBpig MOlKR zml dajth KFYw Xvtj Dajth xvtj rTZVYU JMgBfc","RtojO Xvtj BQbtL LDYaM Glhc","glhc Zltfa hxxsx xrDTd Htg TrJ Glhc Xvtj hxxsx UUImeD Hxxsx pRLw","BSKIM slokx ety Qebqhd LkgiA UEo oxwBc NpjO ELruV fETjzs KIeSH dMRZq slokx bjwL","mferd aAbncH bic tqEk Mferd","vlku Glhc slokx dDO Xvtj QlCEOd"
        };
        List<String> strings = topkKeywords(k, keywords, reviews);
        strings.forEach(System.out::println);
    }
}
