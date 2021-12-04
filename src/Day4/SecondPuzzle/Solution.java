package Day4.SecondPuzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/Day4/input.txt"));
            int[] drawnNumbers = Arrays.stream(reader.readLine().split(","))
                .mapToInt(Integer::parseInt).toArray();
            List<Board> boards = getListOfBoards(reader);
            for (int i = 0; i < drawnNumbers.length; i++) {
                mark(boards, drawnNumbers[i], i);
                if (Board.numberOfWins == boards.size()) { // last board to win
                    int sum = Board.latestBoardWin.sumOfUnmarked();
                    System.out.println(sum * drawnNumbers[i]);
                    return;
                }
            }
            System.out.println("NO WINNER");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the index of the winner board if it wins after the mark, -1 otherwise.
     */
    public static void mark(List<Board> boards, int num, int index) {
        for (int i = 0; i < boards.size(); i++) {
            Board board = boards.get(i);
            board.mark(num, index);
        }
    }

    public static List<Board> getListOfBoards(BufferedReader reader) throws IOException {
        List<Board> list = new ArrayList<>();
        List<List<Integer>> temp = new ArrayList<>();
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            } else {
                if (line.equals("")) {
                    if (temp.size() != 0) {
                        list.add(new Board(temp));
                        temp = new ArrayList<>();
                    }
                } else {
                    temp.add(Arrays.stream(line.trim().split("\\s+"))
                        .mapToInt(num -> Integer.parseInt(num.trim())).boxed().collect(Collectors.toList()));
                }
            }
        }
        return list;
    }

    public static class Board {
        public static int numberOfWins = 0;
        public static Board latestBoardWin = null;
        private List<List<Integer>> listOfRows;
        private boolean hasWon = false;


        public Board(List<List<Integer>> listOfRows) {
            this.listOfRows = listOfRows;
        }

        public boolean mark(int num, int index) {
            for (int i = 0; i < listOfRows.size(); i++) {
                for (int j = 0; j < listOfRows.size(); j++) {
                    if (listOfRows.get(i).get(j) == num) {
                        listOfRows.get(i).set(j ,-1);
                        // impossible to have winner if less than 5 drawn
                        return index >= listOfRows.size() && checkWinner(i, j);
                    }
                }
            }
            return false;
        }

        public boolean checkWinner(int row, int col) {
            boolean isRowWinner = true;
            boolean isColWinner = true;
            for (int i = 0; i < listOfRows.size(); i++) {
                if (listOfRows.get(row).get(i) != -1) {
                    isRowWinner = false;
                    break;
                }
            }
            for (int i = 0; i < listOfRows.size(); i++) {
                if (listOfRows.get(i).get(col) != -1) {
                    isColWinner = false;
                    break;
                }
            }
            boolean isWinner = isRowWinner || isColWinner;
            if (isWinner && !hasWon) {
                hasWon = true;
                latestBoardWin = this;
                numberOfWins++;
                return true;
            }
            return false;
        }

        public int sumOfUnmarked() {
            int sum = 0;
            for (int i = 0; i < listOfRows.size(); i++) {
                for (int j = 0; j < listOfRows.size(); j++) {
                    int value = listOfRows.get(i).get(j);
                    if (value != -1) {
                        sum += value;
                    }
                }
            }
            return sum;
        }
    }
}
