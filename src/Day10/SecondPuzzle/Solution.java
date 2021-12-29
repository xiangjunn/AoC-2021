package Day10.SecondPuzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("src/Day10/input.txt");
            BufferedReader br = new BufferedReader(fr);
            HashMap<Character, Character> map = new HashMap<>();
            map.put('(', ')');
            map.put('[', ']');
            map.put('{', '}');
            map.put('<', '>');
            HashMap<Character, Integer> points = new HashMap<>();
            points.put('(', 1);
            points.put('[', 2);
            points.put('{', 3);
            points.put('<', 4);
            List<Long> list = new ArrayList<>();
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                int len = line.length();
                Stack<Character> stack = new Stack<>();
                boolean isCorrupted = false;
                for (int i = 0; i < len; i++) {
                    char value = line.charAt(i);
                    if (")]}>".indexOf(value) != -1) { // contains ) or ] or } or >
                        char latest = stack.pop();
                        if (map.get(latest) != value) {
                            isCorrupted = true;
                            break;
                        }
                    } else {
                        stack.push(value);
                    }
                }
                if (isCorrupted) {
                    continue;
                }
                // at this stage, the line must be incomplete. Remaining opening characters in stack must have
                // its correspond closing characters
                long score = 0;
                while (!stack.isEmpty()) {
                    char latest = stack.pop();
                    score = (score * 5) + points.get(latest);
                }
                list.add(score);
            }
            // use quickselect to find middle score
            int len = list.size();
            long midScore = quickSelect(list, 0, len - 1, len / 2);
            System.out.println(midScore);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getPartition(List<Long> list, int low, int high) {
        // consider first element as pivot
        long pivot = list.get(low);
        int pointer = high;
        for (int i = high; i >= low; i--) {
            if (list.get(i) > pivot) {
                long temp = list.get(i);
                list.set(i, list.get(pointer));
                list.set(pointer, temp);
                pointer--;
            }
        }
        // swap pivot to the position of pointer
        list.set(low, list.get(pointer));
        list.set(pointer, pivot);
        return pointer;
    }

    public static long quickSelect(List<Long> list, int low, int high, int mid) {
        int partition = getPartition(list, low, high);
        if (partition == mid) {
            return list.get(partition);
        } else if (partition < mid) {
            return quickSelect(list, partition + 1, high, mid);
        } else {
            return quickSelect(list, low, partition - 1, mid);
        }
    }
}
