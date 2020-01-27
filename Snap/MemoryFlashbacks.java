// # Memory Flashbacks
// # We want to generate multiple daily memory flashbacks between friends who live
// # all around the world. We need to consider a 50 hour window in order to capture
// # all the snaps which may have been created within any given day (there is a 26
// # hour difference between the earliest and latest timezones + 24 hours in one
// # day for the last timezone). Given a list of days, represented as start and end
// # hour ranges, and an unsorted list of snaps, represented by their hour
// # timestamps, return a mapping of each day to the snaps which fall within that
// # day's start and end hour ranges.
// # Note: the start hour is inclusive while the end hour is exclusive; i.e. [0, 50).
// # Inputs:
// # - days : [[0, 50], [24, 74], [48, 98]]
// # - snaps : [5, 90, 12, 50, 30, 48]
// # Output: {0 => [5, 12, 30, 48], 1 => [30, 48, 50], 2 => [48, 50, 90]}

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String args[] ) throws Exception {
        int[][] days = new int[][]{{0, 50}, {24, 74}, {48, 98}};
        int[] snaps = {5, 90, 12, 50, 30, 48, 100};
        Map<Integer, List<Integer>> map = memory(days, snaps);
        for (int key : map.keySet()) {
            System.out.print(key + " => ");
            for (int val : map.get(key)) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
    private static Map<Integer, List<Integer>> memory(int[][] days, int[] snaps) {
        Arrays.sort(snaps);
        Map<Integer, List<Integer>> map = new HashMap<>();
        int startDay = 0;
        for (int snap : snaps) {
            if (startDay >= days.length) {
                break;
            }
            for (int i = startDay; i < Math.min(startDay + 3, days.length); i++) {
                int[] day = days[i];
                if (snap >= day[0] && snap < day[1]) {
                    map.computeIfAbsent(i, dummy -> (new ArrayList<>())).add(snap);
                }
            }
            if (snap >= days[startDay][1]) {
                startDay++;
            }
        }
        return map;
    }
}
