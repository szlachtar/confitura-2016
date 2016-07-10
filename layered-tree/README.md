### solution
Layered tree is build upon an array.
As a first step all numbers are sorted in natural order.
After sorting, numbers are iterated and for each of them it calculates position in the tree.
First it calculates in which layer this number should be.
If this is even layer (0 based) then position in tree is the same as in sorted table.
For odd layer it deducts position in sorted table from starting position for next layer and adds last position in previous layer.
