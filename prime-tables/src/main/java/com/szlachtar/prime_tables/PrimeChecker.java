package com.szlachtar.prime_tables;

import java.util.HashSet;
import java.util.Set;

class PrimeChecker {
    private Set<Long> cachedPrimeNumbers = new HashSet<>();

    boolean isPrime(long number) {
        if (cachedPrimeNumbers.contains(number)) {
            return true;
        }

        if (number <= 1) {
            return false;
        }

        if (number == 2) {
            cachedPrimeNumbers.add(number);
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }

        for (int i = 3; i <= Math.sqrt(number) + 1; i = i + 2) {
            if (number % i == 0) {
                return false;
            }
        }
        cachedPrimeNumbers.add(number);
        return true;
    }
}
