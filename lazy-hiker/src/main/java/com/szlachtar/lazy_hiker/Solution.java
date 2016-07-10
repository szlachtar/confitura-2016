package com.szlachtar.lazy_hiker;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static java.lang.Long.compare;
import static java.util.Collections.singleton;

class Solution implements Comparable<Solution> {
    final long ascents;
    final long descents;
    private final int currentHeight;
    final String path;
    private final Set<Position> visitedPositions;

    Solution(int x, int y, int height) {
        this.visitedPositions = new HashSet<>(singleton(new Position(x,y)));
        this.currentHeight = height;
        this.ascents = 0;
        this.descents = 0;
        this.path = "";
    }

    private Solution(long ascents, long descents, int currentHeight, String path, Set<Position> visitedPositions) {
        this.ascents = ascents;
        this.descents = descents;
        this.currentHeight = currentHeight;
        this.path = path;
        this.visitedPositions = visitedPositions;
    }

    Solution addMove(String move, int x, int y, int newPositionHeight) {
        Set<Position> positions = new HashSet<>(visitedPositions);
        positions.add(new Position(x, y));
        long newAscents = ascents;
        long newDescents = descents;
        if (currentHeight > newPositionHeight) {
            newDescents += currentHeight - newPositionHeight;
        } else {
            newAscents += newPositionHeight - currentHeight;
        }
        String newPath = path + move;
        return new Solution(newAscents, newDescents, newPositionHeight, newPath, positions);
    }

    boolean wasOnPosition(int x, int y) {
        return visitedPositions.contains(new Position(x, y));
    }

    @Override
    public int compareTo(Solution s) {
        return compare(ascents, s.ascents) == 0 ? compare(descents, s.descents) : compare(ascents, s.ascents);
    }

    private class Position {
        final int x;
        final int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return x == position.x &&
                    y == position.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
