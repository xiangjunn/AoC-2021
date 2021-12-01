package Day1.SecondPuzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Solution {
    public static void main(String[] var0) {
        countNumberOfIncrease();
    }

    public static void countNumberOfIncrease() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/Day1/SecondPuzzle/input.txt"));
            int count = 0;
            int lineNumber = 0;
            int[] groups = new int[3];
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                int value = Integer.parseInt(line);
                lineNumber++;
                int pos = lineNumber % 3;
                int temp = groups[pos]; // to store the sum of previous before resetting to 0
                groups[pos] = 0;
                for (int i = 0; i < groups.length; i++) {
                    groups[i] += value;
                }
                if (lineNumber > 3 && groups[(pos + 1) % 3] > temp) {
                    count++;
                }
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
