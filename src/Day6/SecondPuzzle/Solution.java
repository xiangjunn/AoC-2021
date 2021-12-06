package Day6.SecondPuzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/Day6/input.txt"));
            String data = reader.readLine();
            List<Integer> list = Arrays.asList(data.split(","))
                .stream().map(Integer::parseInt)
                .collect(Collectors.toList());
            final int NUM_OF_DAYS = 256;
            long count = countAfterGrowth(list, NUM_OF_DAYS);
            System.out.print(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long countAfterGrowth(List<Integer> list, int days) {
        long[] timers = new long[9];
        for (int i = 0; i < list.size(); i++) {
            timers[list.get(i)] += 1;
        }
        for (int day = 0; day < days; day++) {
            timers[(day + 7) % 9] += timers[day % 9];
        }
        return LongStream.of(timers).sum();
    }
}
