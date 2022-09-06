package com.janwarlen.ac.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Tmp {

    public static void main(String[] args) {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        try {
            String s = br.readLine();
            int lines = Integer.parseInt(s);
            HashSet<Integer> ints = new HashSet<>();
            for (int i = 0; i < lines; i++) {
                ints.add(Integer.parseInt(br.readLine()));
            }
            List<Integer> collect = new ArrayList<>(ints);
            Collections.sort(collect);
            collect.stream().forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
