package com.janwarlen.ac.date;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

// https://www.lintcode.com/problem/2907/
public class WeekOff {
    static String[] day = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
    static Map<String, String> map;

    static Thread[] getWeekDay() throws Exception {
        map = new ConcurrentHashMap<>(7001, 1.0f);
        LocalDate[] starts = new LocalDate[7];
        starts[0] = LocalDate.of(2021, 10, 8);
        for (int j = 1; j < 7; j++) {
            starts[j] = starts[j - 1].plusDays(1000);
        }
        Thread[] weekDay = new Thread[7];
        // write your code here
        for (int i = 0; i < 7; i++) {
            final int tmp = i;
            weekDay[i] = new Thread(() -> {
                int start = tmp * 1_000;
                int end = start + 1000;
                for (int j = start; j < end; j++) {
                    map.put(starts[tmp].getYear() + "-" + starts[tmp].getMonthValue() + "-" + starts[tmp].getDayOfMonth(), day[(j + 5) % 7]);
                    starts[tmp] = starts[tmp].plusDays(1);
                }
            });
        }
        return weekDay;
    }

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        Thread[] weekDay = getWeekDay();
        for (Thread thread : weekDay) {
            thread.start();
        }
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        TreeMap<String, String> rmap = new TreeMap<>(map);
        System.out.println("Time limit 200ms: " + ((end - start) < 240));
        System.out.println("Solution.map.size() = " + map.size());
        System.out.println("Solution.map = " + rmap);
    }
}
