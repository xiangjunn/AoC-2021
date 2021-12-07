package Day7.SecondPuzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("src/Day7/input.txt");
            BufferedReader br = new BufferedReader(fr);
            String[] data = br.readLine().split(",");
            int[] positions = Arrays.stream(data).mapToInt(Integer::parseInt).toArray();
            mergeSort(positions, 0, positions.length - 1); // better solution is to use quickSelect
            double average = findAverage(positions);
            int totalCostRoundDown = 0;
            int totalCostRoundUp = 0;
            for (int position : positions) {
                int roundDownAverage = (int) Math.floor(average);
                int roundUpAverage = (int) Math.ceil(average);
                double nDown = Math.abs(position - roundDownAverage);
                double nUp = Math.abs(position - roundUpAverage);
                totalCostRoundDown += (nDown * (nDown + 1)) / 2; // formula to find sum from 1 to n
                totalCostRoundUp += (nUp * (nUp + 1)) / 2;
            }
            System.out.println(Math.min(totalCostRoundDown, totalCostRoundUp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mergeSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        } else {
            int mid = start + (end - start) / 2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
    }

    public static void merge(int[] arr, int start, int mid, int end) {
        int size1 = mid - start + 1;
        int size2 = end - mid;
        int[] temp1 = new int[size1];
        int[] temp2 = new int[size2];
        for (int i = 0; i < size1; i++) {
            temp1[i] = arr[start + i];
        }
        for (int i = 0; i < size2; i++) {
            temp2[i] = arr[mid + 1 + i];
        }
        int idx1 = 0;
        int idx2 = 0;
        int mergeIdx = start;
        while (idx1 < size1 && idx2 < size2) {
            if (temp1[idx1] < temp2[idx2]) {
                arr[mergeIdx] = temp1[idx1];
                idx1++;
            } else {
                arr[mergeIdx] = temp2[idx2];
                idx2++;
            }
            mergeIdx++;
        }
        while (idx1 < size1) {
            arr[mergeIdx] = temp1[idx1];
            idx1++;
            mergeIdx++;
        }

        while (idx2 < size2) {
            arr[mergeIdx] = temp2[idx2];
            idx2++;
            mergeIdx++;
        }
    }

    public static double findAverage(int[] arr) {
        int len = arr.length;
        double sum = 0;
        for (int i : arr) {
            sum += i;
        }
        return sum / len;
    }
}
