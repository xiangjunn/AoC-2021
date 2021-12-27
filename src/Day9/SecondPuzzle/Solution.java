package Day9.SecondPuzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

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
            PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> Integer.compare(y, x));
            int result = 1;

            for (int i = 0; i < list.size(); i++) {
                ArrayList<Integer> row = list.get(i);
                for (int j = 0; j < row.size(); j++) {
                    int point = row.get(j);
                    if (point == 9) {
                        continue;
                    }
                    boolean[][] visited = new boolean[list.size()][list.get(0).size()];
                    int size = calculateSize(i, j, Integer.MIN_VALUE, list, visited);
                    pq.add(size);
                }
            }
            for (int i = 0; i < 3; i++) {
                int value = pq.poll();
                result *= value;
            }
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int calculateSize(int row, int col, int prevPoint,
            ArrayList<ArrayList<Integer>> list, boolean[][] visited) {
        int rowLen = list.size();
        int colLen = list.get(0).size();
        if (row < 0 || col < 0 || row >= rowLen || col >= colLen) {
            return 0;
        }
        int value = list.get(row).get(col);
        if (visited[row][col] || value <= prevPoint || value == 9) {
            return 0;
        }
        visited[row][col] = true;
        int left = calculateSize(row, col - 1, value, list, visited);
        int right = calculateSize(row, col + 1, value, list, visited);
        int up = calculateSize(row - 1, col, value, list, visited);
        int down = calculateSize(row + 1, col, value, list, visited);
        return 1 + left + right + up + down;
    }
}
