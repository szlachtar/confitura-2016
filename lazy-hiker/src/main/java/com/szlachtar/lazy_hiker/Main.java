package com.szlachtar.lazy_hiker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class Main {

    public static void main(String[] args) {
        try {
            Integer[][] input = readInput();
            LazyHiker lazyHiker = new LazyHiker(input);
            lazyHiker.calculateShortestPaths();
            System.out.print(lazyHiker.toString());
        } catch (Exception e) {
            System.out.print("Error: " + e.getMessage());
        }
    }

    private static Integer[][] readInput() {
        Scanner scanner = new Scanner(System.in);
        List<List<Integer>> input = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] stringHights = line.split(",");
            List<Integer> hights = stream(stringHights)
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
            input.add(hights);
        }

        Integer[][] map = new Integer[input.size()][];

        Iterator<List<Integer>> iterator = input.iterator();
        for (int y = 0; y < map.length; y++) {
            List<Integer> xValues = iterator.next();
            Integer[] x = xValues.toArray(new Integer[xValues.size()]);
            map[y] = x;
        }
        return map;
    }
}
