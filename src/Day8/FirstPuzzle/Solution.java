package Day8.FirstPuzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Solution {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("src/Day8/input.txt");
            BufferedReader br = new BufferedReader(fr);
            HashMap<Integer, Integer> map = new HashMap<>();
            while (true) {
                String data = br.readLine();
                if (data == null) {
                    break;
                }
                String[] outputs = data.split("\\|")[1].trim().split("\\s+");
                for (int i = 0; i < outputs.length; i++) {
                    int len = outputs[i].length();
                    if (map.containsKey(len)) {
                        map.put(len, map.get(len) + 1);
                    } else {
                        map.put(len, 1);
                    }
                }
            }
            int[] segmentCounts = { 2, 4, 3, 7 }; // the number of segments for 1, 4, 7 and 8 respectively
            int result = 0;
            for (int count : segmentCounts) {
                if (map.containsKey(count)) {
                    result += map.get(count);
                }
            }
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
