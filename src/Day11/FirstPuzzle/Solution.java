package Day11.FirstPuzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("src/Day11/input.txt");
            BufferedReader br = new BufferedReader(fr);
            List<List<Integer>> list = new ArrayList<>();
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                List<Integer> row = new ArrayList<>();
                for (int i = 0; i < line.length(); i++) {
                    int energy = Integer.parseInt(String.valueOf(line.charAt(i)));
                    row.add(energy);
                }
                list.add(row);
            }
            final int STEPS = 100;
            long count = countFlashes(list, STEPS);
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long countFlashes(List<List<Integer>> list, int steps) {
        if (steps <= 0) {
            return 0;
        }
        long count = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(0).size(); j++) {
                int val = list.get(i).get(j);
                if (val > 9) {
                    continue;
                } else if (val == 9) {
                    list.get(i).set(j, val + 1);
                    count++;
                    count += increaseAdjacent(i, j, list);
                } else {
                    list.get(i).set(j, val + 1);
                }
            }
        }
        // loop again to change all energies more than 9 to 0
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(0).size(); j++) {
                int val = list.get(i).get(j);
                if (val > 9) {
                    list.get(i).set(j, 0);
                }
            }
        }
        return count + countFlashes(list, steps - 1);
    }

    public static long increaseAdjacent(int row, int col, List<List<Integer>> list) {
        int rowLen = list.size();
        int colLen = list.get(0).size();
        long count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int currRow = row + i;
                int currCol = col + j;
                if (currRow < 0 || currCol < 0 || currRow >= rowLen || currCol >= colLen
                        || (i == 0 && j == 0)) {
                    continue;
                }
                int val = list.get(currRow).get(currCol);
                if (val > 9) {
                    continue;
                } else if (val == 9) {
                    list.get(currRow).set(currCol, val + 1);
                    count++;
                    count += increaseAdjacent(currRow, currCol, list);
                } else {
                    list.get(currRow).set(currCol, val + 1);
                }
            }
        }
        return count;
    }

}
