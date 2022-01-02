package Day14.FirstPuzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("src/Day14/input.txt");
            BufferedReader br = new BufferedReader(fr);
            List<Character> polymer = new ArrayList<>();
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
                    for (int i = 0; i < line.length(); i++) {
                        polymer.add(line.charAt(i));
                    }
                }
            }
            final int STEPS = 10;
            for (int i = 1; i <= STEPS; i++) {
                insert(polymer, rules);
            }

            HashMap<Character, Integer> elementsCount = new HashMap<>();
            for (int i = 0; i < polymer.size(); i++) {
                char element = polymer.get(i);
                if (elementsCount.containsKey(element)) {
                    elementsCount.put(element, elementsCount.get(element) + 1);
                } else {
                    elementsCount.put(element, 1);
                }
            }
            int largest = 0;
            int smallest = Integer.MAX_VALUE;
            for (int value : elementsCount.values()) {
                largest = Math.max(largest, value);
                smallest = Math.min(smallest, value);
            }
            int diff = largest - smallest;
            System.out.println(diff);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void insert(List<Character> polymer, List<String[]> rules) {
        List<Integer> indexes = new ArrayList<>();
        HashMap<Integer, Character> map = new HashMap<>();
        for (int i = 0; i < rules.size(); i++) {
            String[] rule = rules.get(i);
            int curr = 0;
            final int CURR_SIZE = polymer.size();
            while (curr + 1 < CURR_SIZE) {
                char first = rule[0].charAt(0);
                char second = rule[0].charAt(1);
                char toInsert = rule[1].charAt(0);
                int next = curr + 1;
                if (polymer.get(curr) == first && polymer.get(next) == second) {
                    indexes.add(next);
                    map.put(next, toInsert);
                }
                curr++;
            }
        }
        Collections.sort(indexes);
        for (int i = indexes.size() - 1; i >= 0; i--) {
            int index = indexes.get(i);
            polymer.add(index, map.get(index));
        }
    }
}
