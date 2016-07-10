package com.szlachtar.layered_tree;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Integer[] input = readInput();
            LayeredTree tree = new LayeredTree(input);
            System.out.print(tree.toString());
        } catch (Exception e) {
            System.out.print("Error: " + e.getMessage());
        }

    }

    private static Integer[] readInput() {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] split = s.trim().split(",");
        Integer[] provided = new Integer[split.length];
        for (int i = 0; i < split.length; i++) {
            provided[i] = Integer.valueOf(split[i]);
        }
        return provided;
    }
}
