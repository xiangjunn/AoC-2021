package Day13.SecondPuzzle;

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
            List<HashMap<Character, Integer>> folds = new ArrayList<>();
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
                    HashMap<Character, Integer> fold = new HashMap<>();
                    fold.put(equation[0].charAt(0), Integer.parseInt(equation[1]));
                    folds.add(fold);
                }
            }
            char[][] paper = new char[maxX + 1][maxY + 1];
            for (int i = 0; i < coordinates.size(); i++) {
                int[] coordinate = coordinates.get(i);
                paper[coordinate[0]][coordinate[1]] = '#';
            }
            for (int foldNum = 0; foldNum < folds.size(); foldNum++) {
                HashMap<Character, Integer> fold = folds.get(foldNum);
                if (fold.containsKey('x')) {
                    int xValue = fold.get('x');
                    for (int i = xValue + 1; i <= maxX; i++) {
                        for (int j = 0; j <= maxY; j++) {
                            char value = paper[i][j];
                            if (value == '#') {
                                int diff = i - xValue;
                                paper[xValue - diff][j] = '#';
                            }
                        }
                    }
                    maxX = xValue - 1;
                } else {
                    int yValue = fold.get('y');
                    for (int i = 0; i <= maxX; i++) {
                        for (int j = yValue + 1; j <= maxY; j++) {
                            char value = paper[i][j];
                            if (value == '#') {
                                int diff = j - yValue;
                                paper[i][yValue - diff] = '#';
                            }
                        }
                    }
                    maxY = yValue - 1;
                }
            }
            for (int i = 0; i <= maxY; i++) {
                for (int j = 0; j <= maxX; j++) {
                    char value = paper[j][i];
                    if (value == '#') {
                        System.out.print('#');
                    } else {
                        System.out.print('.');
                    }
                }
                System.out.print("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
