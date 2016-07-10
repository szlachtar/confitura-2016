package com.szlachtar.lazy_hiker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.sort;

class LazyHiker {
    private final Integer[][] map;
    private final int maxY;
    private final int maxX;

    private long bestPathAscents = Long.MAX_VALUE;
    private long bestPathDescent = Long.MAX_VALUE;
    private final Set<String> bestPaths = new HashSet<>();

    LazyHiker(Integer[][] input) {
        this.map = input;
        maxY = input.length;
        maxX = input[0].length;
        createRepresentativeSolution();
    }

    void calculateShortestPaths() {
        int x = 0;
        int y = 0;
        int height = map[y][x];
        Solution solution = new Solution(x, y, height);

        updateBestPaths(goRight(solution, x, y));
        updateBestPaths(goDown(solution, x, y));
    }

    private void updateBestPaths(List<Solution> solutions) {
        sort(solutions);
        solutions.stream()
                .findFirst()
                .ifPresent(this::updateStatsIfBetterSolutionFound);

        solutions.stream()
            .filter(s -> s.ascents <= bestPathAscents && s.descents <= bestPathDescent)
            .forEach(s -> bestPaths.add(s.path));
    }

    private void updateStatsIfBetterSolutionFound(Solution solution) {
        if (isBetterThanCurrentBestSolution(solution)) {
            bestPathAscents = solution.ascents;
            bestPathDescent = solution.descents;
            bestPaths.clear();
        }
    }

    private void createRepresentativeSolution() {
        int height = map[0][0];
        Solution solution = new Solution(0, 0, height);
        //moving max to right
        for (int x = 1; x < maxX; x++) {
            solution = solution.addMove("R", x, 0, map[0][x]);
        }

        //moving max down
        for (int y = 1; y < maxY; y++) {
            solution = solution.addMove("D", maxX, y, map[y][maxX-1]);
        }

        bestPaths.add(solution.path);
        bestPathDescent = solution.descents;
        bestPathAscents = solution.ascents;
    }

    private List<Solution> goUp(Solution solution, int x, int y) {
        List<Solution> solutions = new ArrayList<>();
        int newY = y - 1;
        if (isOutsideTheMap(x, newY) || solution.wasOnPosition(x, newY)) {
            return solutions;
        }

        int currentHeight = map[newY][x];
        Solution nextSolution = solution.addMove("U", x, newY, currentHeight);
        if (isWorseThanCurrentBestSolution(nextSolution)) {
            return new ArrayList<>();
        }

        solutions.addAll(goUp(nextSolution, x, newY));
        solutions.addAll(goLeft(nextSolution, x, newY));
//        solutions.addAll(goDown(nextSolution, x, newY));
        solutions.addAll(goRight(nextSolution, x, newY));
        return solutions;
    }

    private List<Solution> goLeft(Solution solution, int x, int y) {
        List<Solution> solutions = new ArrayList<>();
        int newX = x - 1;
        if (isOutsideTheMap(newX, y) || solution.wasOnPosition(newX, y)) {
            return solutions;
        }

        int currentHeight = map[y][newX];
        Solution nextSolution = solution.addMove("L", newX, y, currentHeight);
        if (isWorseThanCurrentBestSolution(nextSolution)) {
            return new ArrayList<>();
        }

        solutions.addAll(goUp(nextSolution, newX, y));
        solutions.addAll(goLeft(nextSolution, newX, y));
        solutions.addAll(goDown(nextSolution, newX, y));
//        solutions.addAll(goRight(nextSolution, newX, y));
        return solutions;
    }

    private List<Solution> goDown(Solution solution, int x, int y) {
        List<Solution> solutions = new ArrayList<>();
        int newY = y + 1;
        if (isOutsideTheMap(x, newY) || solution.wasOnPosition(x, newY)) {
            return solutions;
        }

        int currentHeight = map[newY][x];

        Solution nextSolution = solution.addMove("D", x, newY, currentHeight);
        if (isWorseThanCurrentBestSolution(nextSolution)) {
            return new ArrayList<>();
        }

        if (isTargetPosition(x, newY)) {
            updateStatsIfBetterSolutionFound(solution);
            solutions.add(nextSolution);
            return solutions;
        }

//        solutions.addAll(goUp(nextSolution, x, newY));
        solutions.addAll(goLeft(nextSolution, x, newY));
        solutions.addAll(goDown(nextSolution, x, newY));
        solutions.addAll(goRight(nextSolution, x, newY));
        return solutions;
    }

    private List<Solution> goRight(Solution solution, int x, int y) {
        List<Solution> solutions = new ArrayList<>();
        int newX = x + 1;
        if (isOutsideTheMap(newX, y) || solution.wasOnPosition(newX, y)) {
            return solutions;
        }

        int currentHeight = map[y][newX];
        Solution nextSolution = solution.addMove("R", newX, y, currentHeight);

        if (isWorseThanCurrentBestSolution(nextSolution)) {
            return new ArrayList<>();
        }

        if (isTargetPosition(newX, y)) {
            updateStatsIfBetterSolutionFound(solution);
            solutions.add(nextSolution);
            return solutions;
        }
        solutions.addAll(goUp(nextSolution, newX, y));
//        solutions.addAll(goLeft(nextSolution, newX, y));
        solutions.addAll(goDown(nextSolution, newX, y));
        solutions.addAll(goRight(nextSolution, newX, y));
        return solutions;
    }

    private boolean isOutsideTheMap(int x, int y) {
        return x < 0 || x >= maxX || y < 0 || y >= maxY;
    }

    private boolean isWorseThanCurrentBestSolution(Solution solution) {
        return solution.ascents > bestPathAscents || solution.descents > bestPathDescent;
    }

    private boolean isBetterThanCurrentBestSolution(Solution solution) {
        return solution.ascents < bestPathAscents || (solution.ascents == bestPathAscents && solution.descents < bestPathDescent);
    }

    private boolean isTargetPosition(int x, int y) {
        return x == maxX-1 && y == maxY-1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        bestPaths.forEach(path -> {
            sb.append(path);
            sb.append("\n");
        });
        return sb.toString().trim();
    }
}
