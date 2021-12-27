package Day9.FirstPuzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("src/Day9/input.txt");
            BufferedReader br = new BufferedReader(fr);
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                ArrayList<Integer> row = new ArrayList<>();
                for (int i = 0; i < line.length(); i++) {
                    char value = line.charAt(i);
                    int num = Integer.parseInt(String.valueOf(value));
                    row.add(num);
                }
                list.add(row);
            }
            int sum = 0;
            for (int i = 0; i < list.size(); i++) {
                ArrayList<Integer> row = list.get(i);
                for (int j = 0; j < row.size(); j++) {
                    int curr = row.get(j);
                    int left = getPoint(i, j - 1, list);
                    int right = getPoint(i, j + 1, list);
                    int up = getPoint(i - 1, j, list);
                    int down = getPoint(i + 1, j, list);
                    if (curr < left && curr < right && curr < up && curr < down) {
                        sum += curr + 1;
                    }
                }
            }
            System.out.println(sum);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the point if it is valid, else the maximum integer value
     */
    public static int getPoint(int row, int col, ArrayList<ArrayList<Integer>> list) {
        int rowLen = list.size();
        int colLen = list.get(0).size();
        if (row < 0 || col < 0 || row >= rowLen || col >= colLen) {
            return Integer.MAX_VALUE;
        } else {
            return list.get(row).get(col);
        }
    }
}
