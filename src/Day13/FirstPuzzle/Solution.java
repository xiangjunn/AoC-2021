package Day13.FirstPuzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("src/Day13/input.txt");
            BufferedReader br = new BufferedReader(fr);
            boolean isCoordinate = true;
            List<int[]> coordinates = new ArrayList<>();
            HashMap<Character, Integer> fold = new HashMap<>();
            int maxX = 0;
            int maxY = 0;

            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                if (line.equals("")) {
                    isCoordinate = false;
                    continue;
                }
                if (isCoordinate) {
                    String[] arr = line.split(",");
                    int currX = Integer.parseInt(arr[0]);
                    int currY = Integer.parseInt(arr[1]);
                    int[] coordinate = new int[] {currX, currY};
                    maxX = Math.max(maxX, currX);
                    maxY = Math.max(maxY, currY);
                    coordinates.add(coordinate);
                } else {
                    String[] arr = line.split("\\s+");
                    String[] equation = arr[2].split("=");
                    fold.put(equation[0].charAt(0), Integer.parseInt(equation[1]));
                    break;
                }
            }
            char[][] paper = new char[maxX + 1][maxY + 1];
            for (int i = 0; i < coordinates.size(); i++) {
                int[] coordinate = coordinates.get(i);
                paper[coordinate[0]][coordinate[1]] = '#';
            }
            if (fold.containsKey('x')) {
                int xValue = fold.get('x');
                for (int i = xValue; i <= maxX; i++) {
                    for (int j = 0; j <= maxY; j++) {
                        char value = paper[i][j];
                        if (value == '#') {
                            paper[Math.abs(i - maxX)][j] = '#';
                        }
                    }
                }
                maxX = xValue;
            } else {
                int yValue = fold.get('y');
                for (int i = 0; i <= maxX; i++) {
                    for (int j = yValue; j <= maxY; j++) {
                        char value = paper[i][j];
                        if (value == '#') {
                            paper[i][Math.abs(j - maxY)] = '#';
                        }
                    }
                }
                maxY = yValue;
            }
            int count = 0;
            for (int i = 0; i <= maxX; i++) {
                for (int j = 0; j <= maxY; j++) {
                    char value = paper[i][j];
                    if (value == '#') {
                        count++;
                    }
                }
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
