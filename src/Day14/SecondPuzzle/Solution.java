package Day14.SecondPuzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("src/Day14/input.txt");
            BufferedReader br = new BufferedReader(fr);
            HashMap<String, Long> pairs = new HashMap<>();
            List<String[]> rules = new ArrayList<>();
            boolean isRule = false;
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                if (line.equals("")) {
                    isRule = true;
                    continue;
                }
                if (isRule) {
                    String[] arr = line.split(" -> ");
                    rules.add(arr);
                } else {
                    for (int i = 0; i < line.length() - 1; i++) {
                        String pair = line.substring(i, i + 2);
                        if (pairs.containsKey(pair)) {
                            pairs.put(pair, pairs.get(pair) + 1);
                        } else {
                            pairs.put(pair, 1L);
                        }
                    }
                }
            }
            final int STEPS = 40;
            for (int i = 1; i <= STEPS; i++) {
                insert(pairs, rules);
            }

            HashMap<Character, Long> countMap = new HashMap<>();
            for (Map.Entry entry : pairs.entrySet()) {
                String key = (String) entry.getKey();
                long value = (Long) entry.getValue();
                char first = key.charAt(0);
                char second = key.charAt(1);
                if (countMap.containsKey(first)) {
                    countMap.put(first, countMap.get(first) + value);
                } else {
                    countMap.put(first, value);
                }
                if (countMap.containsKey(second)) {
                    countMap.put(second, countMap.get(second) + value);
                } else {
                    countMap.put(second, value);
                }
            }
            long largest = 0;
            long smallest = Long.MAX_VALUE;
            for (long value : countMap.values()) {
                // odd number means the element appears in first or last position hence not double counted
                // not possible for both first and last position to be same element according to input
                // hence this formula can be used to get the actual count
                long actualCount = (value + 1) / 2;
                largest = Math.max(largest, actualCount);
                smallest = Math.min(smallest, actualCount);
            }
            long diff = largest - smallest;
            System.out.println(diff);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void insert(HashMap<String, Long> pairs, List<String[]> rules) {
        HashMap<String, Long> temp = new HashMap<>();
        for (int i = 0; i < rules.size(); i++) {
            String[] rule = rules.get(i);
            String pair = rule[0];
            char toInsert = rule[1].charAt(0);
            if (pairs.containsKey(pair)) {
                long count = pairs.get(pair);
                pairs.put(pair, 0L);
                String newPair1 = String.format("%c%c", pair.charAt(0), toInsert);
                String newPair2 = String.format("%c%c", toInsert, pair.charAt(1));
                if (temp.containsKey(newPair1)) {
                    temp.put(newPair1, temp.get(newPair1) + count);
                } else {
                    temp.put(newPair1, count);
                }
                if (temp.containsKey(newPair2)) {
                    temp.put(newPair2, temp.get(newPair2) + count);
                } else {
                    temp.put(newPair2, count);
                }
            }
        }
        for (Map.Entry entry : temp.entrySet()) {
            String key = (String) entry.getKey();
            long value = (Long) entry.getValue();
            if (pairs.containsKey(key)) {
                pairs.put(key, pairs.get(key) + value);
            } else {
                pairs.put(key, value);
            }
        }
    }
}
