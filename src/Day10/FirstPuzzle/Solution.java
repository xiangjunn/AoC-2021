package Day10.FirstPuzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("src/Day10/input.txt");
            BufferedReader br = new BufferedReader(fr);
            int sum = 0;
            HashMap<Character, Character> map = new HashMap<>();
            map.put('(', ')');
            map.put('[', ']');
            map.put('{', '}');
            map.put('<', '>');
            HashMap<Character, Integer> points = new HashMap<>();
            points.put(')', 3);
            points.put(']', 57);
            points.put('}', 1197);
            points.put('>', 25137);
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                int len = line.length();
                Stack<Character> stack = new Stack<>();
                for (int i = 0; i < len; i++) {
                    char value = line.charAt(i);
                    if (")]}>".indexOf(value) != -1) { // contains ) or ] or } or >
                        char latest = stack.pop();
                        if (map.get(latest) != value) {
                            sum += points.get(value);
                            break;
                        }
                    } else {
                        stack.push(value);
                    }
                }
            }
            System.out.println(sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
