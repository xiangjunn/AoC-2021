package Day8.SecondPuzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Solution {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("src/Day8/input.txt");
            BufferedReader br = new BufferedReader(fr);
            HashMap<Integer, int[]> encoder = generateEncoder();
            int totalSum = 0;
            while (true) {
                String data = br.readLine();
                if (data == null) {
                    break;
                }
                String[] splitData = data.split("\\|");
                String[] signalWires = splitData[0].trim().split("\\s+");
                String[] outputs = splitData[1].trim().split("\\s+");
                ArrayList<HashSet<Character>> signalPattern = solveSignalPattern(encoder, signalWires);
                int sum = 0;
                for (String output : outputs) {
                    sum *= 10;
                    HashSet<Character> set = getSet(output);
                    for (int i = 0; i < signalPattern.size(); i++) {
                        if (set.equals(signalPattern.get(i))) {
                            sum += i;
                            break;
                        }
                    }
                }
                totalSum += sum;
            }
            System.out.println(totalSum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<HashSet<Character>> solveSignalPattern(
            HashMap<Integer, int[]> encoder, String[] signalWires) {
        ArrayList<HashSet<Character>> signalPattern = new ArrayList<>();
        for (int i = 0; i < 10; i++) { // to initialize the size to 10
            signalPattern.add(null);
        }
        for (int i = 0; i < signalWires.length; i++) {
            int len = signalWires[i].length();
            int digit = findEasyDigit(len);
            if (digit != -1) {
                signalPattern.set(digit, getSet(signalWires[i]));
            }
        }
        // at this point, digits 1, 4, 7 and 8 are filled
        // solve for remaining, using digits 4 and 7 to derive remaining digits from encoder
        for (int i = 0; i < signalWires.length; i++) {
            String signalWire = signalWires[i];
            int[] code = getCode(signalWire, signalPattern.get(4), signalPattern.get(7));
            if (signalWire.length() == 5) { // 2, 3 or 5
                int[] digits = {2, 3, 5};
                for (int digit : digits) {
                    if (Arrays.equals(encoder.get(digit), code)) {
                        signalPattern.set(digit, getSet(signalWire));
                        break;
                    }

                }
            } else if (signalWire.length() == 6) { // 0, 6 or 9
                int[] digits = {0, 6, 9};
                for (int digit : digits) {
                    if (Arrays.equals(encoder.get(digit), code)) {
                        signalPattern.set(digit, getSet(signalWire));
                        break;
                    }
                }
            } else {
                continue;
            }
        }
        return signalPattern;
    }

    public static HashSet<Character> getSet(String str) {
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            set.add(str.charAt(i));
        }
        return set;
    }

    public static int[] getCode(String signalWire, HashSet<Character> set4, HashSet<Character> set7) {
        int intersectionWith4 = 0;
        int intersectionWith7 = 0;
        for (int i = 0; i < signalWire.length(); i++) {
            char value = signalWire.charAt(i);
            if (set4.contains(value)) {
                intersectionWith4++;
            }
            if (set7.contains(value)) {
                intersectionWith7++;
            }
        }
        return new int[] {intersectionWith4, intersectionWith7};
    }

    public static int findEasyDigit(int len) {
        switch(len) {
        case 2: // digit 1
            return 1;
        case 4: // digit 4
            return 4;
        case 3: // digit 7
            return 7;
        case 7: // digit 8
            return 8;
        default:
            return -1;
        }
    }

    public static HashMap<Integer, int[]> generateEncoder() {
        HashMap<Integer, int[]> encoder = new HashMap<>();
        encoder.put(0, new int[] {3, 3});
        encoder.put(6, new int[] {3, 2});
        encoder.put(9, new int[] {4, 3});
        encoder.put(2, new int[] {2, 2});
        encoder.put(3, new int[] {3, 3});
        encoder.put(5, new int[] {3, 2});
        return encoder;
    }
}
