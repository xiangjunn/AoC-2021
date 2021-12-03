package Day3.SecondPuzzle;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/Day3/input.txt"));
            ArrayList<String> contents = new ArrayList<>();
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                contents.add(line);
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) != '1') {
                        continue;
                    }
                }
            }
            int len = contents.get(0).length(); // assuming the input file is not empty, which is the case
            List<String> ogrBin = contents;
            List<String> csrBin = contents;
            for (int i = 0; i < len; i++) {
                char forOxygen = countOnes(ogrBin, i) >= (double) ogrBin.size() / 2 ? '1' : '0';
                int finalI = i;
                if (ogrBin.size() != 1) {
                    ogrBin = ogrBin.stream().filter(
                        x -> x.charAt(finalI) == forOxygen).collect(Collectors.toList());
                } else {
                    break;
                }
            }
            for (int i = 0; i < len; i++) {
                char forCo2 = countOnes(csrBin, i) >= (double)  csrBin.size() / 2 ? '0' : '1';
                int finalI = i;
                if (csrBin.size() != 1) {
                    csrBin = csrBin.stream().filter(
                        x -> x.charAt(finalI) == forCo2).collect(Collectors.toList());
                } else {
                    break;
                }
            }

            int ogr = Integer.parseInt(ogrBin.get(0), 2);
            int csr = Integer.parseInt(csrBin.get(0), 2);
            int supportRating = ogr * csr;
            System.out.print(supportRating);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int countOnes(List<String> lst, int index) {
        int count = 0;
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).charAt(index) == '1') {
                count++;
            }
        }
        return count;
    }
}
