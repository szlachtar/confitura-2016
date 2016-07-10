package com.szlachtar.prime_tables;

import java.util.Objects;

class Position {
    final int x;
    final int y;
    final int xLength;
    final int yLength;

    Position(int x, int y, int xLength, int yLength) {
        this.x = x;
        this.y = y;
        this.xLength = xLength;
        this.yLength = yLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position that = (Position) o;
        return x == that.x &&
                y == that.y &&
                xLength == that.xLength &&
                yLength == that.yLength;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, xLength, yLength);
    }
}
