### Solution
Program goes through the matrix in 3 directions. Along X axis, Y axis and diagonal.
For each starting point it checks every subtable if sum of numbers is prime number.
If so, it adds it to current solutions collection.
Program uses cache for computing prime nubmers and for sums of subtables