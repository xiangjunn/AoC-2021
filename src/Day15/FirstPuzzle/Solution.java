package Day15.FirstPuzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("src/Day15/input.txt");
            BufferedReader br = new BufferedReader(fr);
            List<List<Integer>> cave = new ArrayList<>();
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                List<Integer> row = new ArrayList<>();
                for (int i = 0; i < line.length(); i++) {
                    int risk = line.charAt(i) - '0';
                    row.add(risk);
                }
                cave.add(row);
            }
            int[][] dist = new int[cave.size()][cave.get(0).size()];
            for (int i = 0; i < dist.length; i++) {
                for (int j = 0; j < dist[i].length; j++) {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
            PriorityQueue<Node>pq = new PriorityQueue<>();
            int[] direction = new int[] {-1, 0, 1, 0, -1};
            Node node = new Node(0, 0, 0);
            pq.add(node);
            while (!pq.isEmpty()) {
                Node head = pq.poll();
                if (head.row == dist.length - 1 && head.col == dist[0].length - 1) {
                    break;
                }
                for (int i = 0; i < 4; i++) {
                    int nextRow = head.row + direction[i];
                    int nextCol = head.col + direction[i + 1];
                    if (nextRow < 0 || nextCol < 0 || nextRow >= dist.length || nextCol >= dist[0].length) {
                        continue;
                    }
                    int currDist = head.value + cave.get(nextRow).get(nextCol);
                    if (currDist < dist[nextRow][nextCol]) {
                        dist[nextRow][nextCol] = currDist;
                        pq.add(new Node(nextRow, nextCol, currDist));
                    }
                }
            }
            System.out.println(dist[dist.length - 1][dist[0].length - 1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Node implements Comparable<Node> {
        private int row;
        private int col;
        private int value;

        public Node(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }

        @Override
        public int compareTo(Node node) {
            if (this.value < node.value) {
                return -1;
            } else if (this.value > node.value) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
