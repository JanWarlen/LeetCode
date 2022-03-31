package com.janwarlen.ac.math;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {
    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            boolean none = false;
            StringBuilder tmp = new StringBuilder();
            if (0 == (i + 1) % 3) {
                none = true;
                tmp.append("Fizz");
            }
            if (0 == (i + 1) % 5) {
                none = true;
                tmp.append("Buzz");
            }
            if (!none) {
                tmp.append(i + 1);
            }
            res.add(i, tmp.toString());
        }
        return res;
    }
}
