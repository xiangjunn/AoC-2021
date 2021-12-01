package Day1.FirstPuzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) {
        countNumberOfIncrease();
    }

    public static void countNumberOfIncrease() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/Day1/FirstPuzzle/input.txt"));
            int count = 0;
            int prev = Integer.MAX_VALUE;
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                int curr = Integer.parseInt(line);
                if (curr > prev) {
                    count++;
                }
                prev = curr;
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
