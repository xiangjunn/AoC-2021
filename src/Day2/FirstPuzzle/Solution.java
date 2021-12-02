package Day2.FirstPuzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/Day2/input.txt"));
            int horizontalPos = 0;
            int depth = 0;
            while (true) {
                String content = reader.readLine();
                if (content == null) {
                    break;
                }
                String[] arr = content.split(" ");
                String command = arr[0];
                int unit = Integer.parseInt(arr[1]);
                switch (command) {
                case "forward":
                    horizontalPos += unit;
                    break;
                case "down":
                    depth += unit;
                    break;
                case "up":
                    depth -= unit;
                    break;
                default:
                    System.out.println("No change");
                }
            }
            System.out.println(horizontalPos * depth);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
