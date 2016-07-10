### Solution
At the beginning it calculates solution for simple route (go max right and max down).
After that it goes every route using recursion and stops when solution is worse than current best solution.
If solution reaches bottom-right corner it checks if this is at least as good as current solution.
If it is better it updates stats and clears current best paths.

