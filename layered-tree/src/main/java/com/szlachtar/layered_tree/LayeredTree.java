package com.szlachtar.layered_tree;

import java.util.Arrays;

import static java.lang.Math.log;
import static java.util.Comparator.naturalOrder;

class LayeredTree {
    private final Integer[] tree;

    LayeredTree(Integer[] input) {
        Arrays.sort(input, naturalOrder());
        tree = buildTree(input);
    }

    private Integer[] buildTree(Integer[] input) {

        int maxTreeSize = (int) (Math.pow(2, (int)log2(input.length)+1))-1;
        Integer[] builtTree = new Integer[maxTreeSize];

        for (int i = 0; i < input.length; i++) {
            int position = calculatePosition(i);
            builtTree[position] = input[i];
        }

        return builtTree;
    }

    private int calculatePosition(int i) {
        if (i == 0) {
            return 0;
        }
        int treeLevel = (int)log2(i+1);
        if (treeLevel % 2 == 0) {
            return i;
        } else {
            return calculateForOddLevel(i, treeLevel);
        }
    }

    private int calculateForOddLevel(int i, int treeLevel) {
        int lastPositionInForPreviousLevel = (int) (Math.pow(2, treeLevel) - 1)-1;
        int nextLevelStartinPosition = (int) (Math.pow(2, treeLevel+1) - 1);
        return lastPositionInForPreviousLevel + nextLevelStartinPosition - i;
    }

    private double log2(int x) {
        return log(x) / log(2);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int level = 0;
        for (int i = 0; i < tree.length; i++) {
            Integer value = tree[i];
            if (value != null) {
                sb.append(value);
            } else {
                sb.append("_");
            }

            if (i == (int) (Math.pow(2, level+1)-2)) {
                level++;
                sb.append("\n");
            } else {
                sb.append(",");
            }
        }
        return sb.toString().replaceAll(",$|\n$", "");
    }
}
