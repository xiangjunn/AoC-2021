package Day5.SecondPuzzle;

import java.util.HashMap;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/Day5/input.txt"));
            HashMap<Pair<Integer>, Integer> map = new HashMap<>();
            while (true) {
                String line = reader.readLine();
                if (line == null)  {
                    break;
                } else {
                    String[] data = line.trim().split("\\s+");
                    String[] pair1 = data[0].split(",");
                    String[] pair2 = data[data.length - 1].split(",");
                    assert pair1.length == 2 && pair2.length == 2;
                    int x1 = Integer.parseInt(pair1[0]);
                    int y1 = Integer.parseInt(pair1[1]);
                    int x2 = Integer.parseInt(pair2[0]);
                    int y2 = Integer.parseInt(pair2[1]);
                    if (x1 == x2) {
                        int min = Math.min(y1, y2);
                        int max = Math.max(y1, y2);
                        for (int i = min; i <= max; i++) {
                            Pair<Integer> pair = new Pair<>(x1, i);
                            addToMap(map, pair);
                        }
                    } else if (y1 == y2) {
                        int min = Math.min(x1, x2);
                        int max = Math.max(x1, x2);
                        for (int i = min; i <= max; i++) {
                            Pair<Integer> pair = new Pair<>(i, y1);
                            addToMap(map, pair);
                        }
                    } else {
                        boolean is45Degree = Math.abs(x1 - x2) == Math.abs(y1 - y2);
                        boolean isPositiveGradient = (x1 - x2) == (y1 - y2);
                        if (!is45Degree) {
                            continue;
                        } else {
                            int diff = Math.abs(x1 - x2);
                            if (isPositiveGradient) {
                                int minX = Math.min(x1, x2);
                                int minY = Math.min(y1, y2);
                                for (int i = 0; i <= diff; i++) {
                                    Pair<Integer> pair = new Pair<>(minX + i, minY + i);
                                    addToMap(map, pair);
                                }

                            } else {
                                int minX = Math.min(x1, x2);
                                int maxY = Math.max(y1, y2);
                                for (int i = 0; i <= diff; i++) {
                                    Pair<Integer> pair = new Pair<>(minX + i, maxY - i);
                                    addToMap(map, pair);
                                }
                            }
                        }

                    }
                }
            }
            int count = 0;
            Iterator<Integer> iterator = map.values().iterator();
            while (iterator.hasNext()) {
                Integer numOfOverlaps = iterator.next();
                if (numOfOverlaps >= 2) {
                    count++;
                }

            }
            System.out.print(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addToMap(HashMap<Pair<Integer>, Integer> map, Pair<Integer> pair) {
        if (map.containsKey(pair)) {
            map.put(pair, map.get(pair) + 1);
        } else {
            map.put(pair, 1);
        }
    }

    public static class Pair<T> {
        private final T first;
        private final T second;

        public Pair(T first, T second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Pair) {
                Pair pair = (Pair) o;
                return this.first.equals(pair.first) && this.second.equals(pair.second);
            } else {
                return false;
            }
        }

        @Override
        public String toString() {
            return String.format("%s, %s", this.first, this.second);
        }

        @Override
        public int hashCode() {
            return this.toString().hashCode();
        }
    }
}
