package Day3.FirstPuzzle;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/Day3/input.txt"));
            ArrayList<Integer> numOfOnes = new ArrayList<>();
            int numOfLines = 0;
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                numOfLines++;
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) != '1') {
                        continue;
                    }
                    if (i < numOfOnes.size()) {
                        numOfOnes.set(i, numOfOnes.get(i) + 1);
                    } else {
                        numOfOnes.add(1);
                    }
                }
            }
            int finalNumOfLines = numOfLines;
            String gammaRateBin = numOfOnes.stream().map(
                    x -> x > finalNumOfLines / 2 ? "1" : "0").collect(Collectors.joining(""));
            int gammaRate = Integer.parseInt(gammaRateBin, 2);
            int epsilonRate = ((int) Math.pow(2, gammaRateBin.length()) - 1) - gammaRate;
            int power = gammaRate * epsilonRate;
            System.out.print(power);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
