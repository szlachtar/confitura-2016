package com.szlachtar.prime_tables;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class PrimeTables {
    private final Integer[][] input;
    private final int maxX;
    private final int maxY;
    private final PrimeChecker checker = new PrimeChecker();
    private final Set<Position> primePositions = new HashSet<>();
    private final Map<Position, Long> cachedSums = new HashMap<>();

    PrimeTables(Integer[][] input) {
        this.input = input;
        this.maxY = input.length - 1;
        this.maxX = input[0].length - 1;
    }

    int countPrimeTables(){
        for(int x = 0; x +1 <= maxX; x++) {
            count(x, 0);
        }

        for(int y = 0; y+1 <= maxY; y++) {
            count(0, y);
        }

        for(int d =0; d+1 <= maxX && d+1 <= maxY; d++) {
            count(d, d);
        }

        return primePositions.size();
    }

    private void count(int startX, int startY) {
        countWithFixedXAxis(startX, startY);
        countWithFixedYAxis(startX, startY);
    }

    private void countWithFixedYAxis(int startX, int startY) {
        for (int yLength = 1; startY+yLength <= maxY; yLength++) {
            long summedNumbers = sumNumbersInInitialTable(new Position(startX, startY, 1, yLength));
            updatePrimes(summedNumbers, new Position(startX, startY, 1, yLength));

            for(int xLength = 2; startX+xLength <= maxX; xLength++) {
                summedNumbers = sumNextXColumn(summedNumbers, new Position(startX, startY, xLength, yLength));
                updatePrimes(summedNumbers, new Position(startX, startY, xLength, yLength));
            }
        }
    }

    private void countWithFixedXAxis(int startX, int startY) {
        for (int xLength = 1; startX+xLength <= maxX; xLength++) {
            long summedNumbers = sumNumbersInInitialTable(new Position(startX, startY, xLength, 1));
            updatePrimes(summedNumbers, new Position(startX, startY, xLength, 1));
            for(int yLength = 2; startY+yLength <= maxY; yLength++) {
                summedNumbers = sumNextYRow(summedNumbers, new Position(startX, startY, xLength, yLength));
                updatePrimes(summedNumbers, new Position(startX, startY, xLength, yLength));
            }
        }
    }

    private Long sumNextXColumn(final long summedNumbers, final Position position) {
        return cachedSums.computeIfAbsent(position, p ->{
            long sum = summedNumbers;
            int addedXPosition = p.x+p.xLength;
            for(int y=p.y; y <= p.y+p.yLength; y++) {
                sum += input[y][addedXPosition];
            }
            return sum;
        });
    }

    private Long sumNextYRow(final long summedNumbers, final Position position) {
        return cachedSums.computeIfAbsent(position, p ->{
            long sum = summedNumbers;
            int addedYPosition = p.y+p.yLength;
            for(int x=p.x; x <= p.x+p.xLength; x++) {
                sum += input[addedYPosition][x];
            }
            return sum;
        });
    }

    private long sumNumbersInInitialTable(Position position) {
        return cachedSums.computeIfAbsent(position, p -> {
            long sum = 0;
            for (int y = p.y; y <= p.y + p.yLength; y++) {
                for (int x = p.x; x <= p.x + p.xLength; x++) {
                    sum += input[y][x];
                }
            }
            return sum;
        });
    }

    private void updatePrimes(long sum, Position position) {
        if (checker.isPrime(sum) && !primePositions.contains(position)) {
            primePositions.add(position);
        }
    }

}
