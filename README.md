# DaAA: Divide-and-Conquer Algorithms & Analysis

## Overview

This project implements classic divide-and-conquer algorithms with safe recursion patterns and collects detailed metrics for empirical analysis. The algorithms include MergeSort, QuickSort, Deterministic Select (Median-of-Medians), and Closest Pair of Points in 2D. The codebase is structured for clean experimentation, benchmarking, and reporting.

## Code Structure

- Main algorithm implementations are in [`src/main/java/com/helpicantchoose/`](src/main/java/com/helpicantchoose/):
  - [`MergeSort`](src/main/java/com/helpicantchoose/MergeSort.java): Classic merge sort with metrics.
  - [`QuickSort`](src/main/java/com/helpicantchoose/QuickSort.java): Randomized quicksort with cutoff to insertion sort.
  - [`DeterministicSelect`](src/main/java/com/helpicantchoose/DeterministicSelect.java): Median-of-medians linear-time selection.
  - [`ClosestPair`](src/main/java/com/helpicantchoose/ClosestPair.java): Closest pair of points in 2D using divide-and-conquer.
  - [`Metrics`](src/main/java/com/helpicantchoose/Metrics.java): Tracks comparisons, allocations, and recursion depth.
  - [`Point`](src/main/java/com/helpicantchoose/Point.java): Simple 2D point class.
  - [`Main`](src/main/java/com/helpicantchoose/Main.java): Example runner and CSV metrics output.

- Unit and property-based tests are in [`src/test/java/`](src/test/java/):
  - [`MergeSortTest`](src/test/java/MergeSortTest.java)
  - [`QuickSortTest`](src/test/java/QuickSortTest.java)
  - [`DeterministicSelectTest`](src/test/java/DeterministicSelectTest.java)
  - [`ClosestPairTest`](src/test/java/ClosestPairTest.java)
  - [`Tests`](src/test/java/Tests.java): Additional randomized and property-based tests.
  - [`SelectVsSortBenchmark`](src/test/java/SelectVsSortBenchmark.java): JMH microbenchmark comparing selection vs sorting.

## How the Code Works

Each algorithm is implemented as a static class with a `sort` or `select` method that takes an array and a [`Metrics`](src/main/java/com/helpicantchoose/Metrics.java) object. The metrics object is updated on every comparison, allocation, and recursion entry/exit. This allows for detailed empirical analysis of algorithmic behavior.

- **MergeSort**: Uses an auxiliary array for merging and switches to insertion sort for small subarrays.
- **QuickSort**: Uses random pivot selection and tail recursion elimination for efficiency.
- **DeterministicSelect**: Implements the median-of-medians algorithm for $O(n)$ selection.
- **ClosestPair**: Sorts points by $x$-coordinate, recursively finds closest pairs, and checks a strip for cross-boundary pairs.

## Running the Code

To run the main experiments and generate metrics:

```sh
mvn compile
mvn exec:java -Dexec.mainClass="com.helpicantchoose.Main"
```

To run all tests:

```sh
mvn test
```

To run the JMH benchmark (requires JMH plugin):

```sh
mvn test -Dtest=SelectVsSortBenchmark
```

## Architecture Notes

All algorithms use the [`Metrics`](src/main/java/com/helpicantchoose/Metrics.java) class to track:
- Number of comparisons
- Number of allocations (e.g., auxiliary arrays)
- Recursion depth (current and maximum)

Recursion depth is managed by calling `enterRecursion()` and `exitRecursion()` at each recursive call/return. Allocations are tracked by calling `addAlloc()` when new arrays are created.

## Recurrence Analysis

- **MergeSort** ([`MergeSort`](src/main/java/com/helpicantchoose/MergeSort.java)): $T(n) = 2T(n/2) + \Theta(n)$, so $T(n) = \Theta(n \log n)$ by the Master Theorem. Depth is $O(\log n)$.
- **QuickSort** ([`QuickSort`](src/main/java/com/helpicantchoose/QuickSort.java)): Expected $T(n) = 2T(n/2) + \Theta(n)$ (balanced), so $T(n) = \Theta(n \log n)$. Worst-case $O(n^2)$, but rare with random pivots. Depth is $O(\log n)$ on average.
- **DeterministicSelect** ([`DeterministicSelect`](src/main/java/com/helpicantchoose/DeterministicSelect.java)): $T(n) = T(n/5) + T(7n/10) + \Theta(n)$, which solves to $T(n) = \Theta(n)$ (Akra–Bazzi). Depth is $O(\log n)$.
- **Closest Pair** ([`ClosestPair`](src/main/java/com/helpicantchoose/ClosestPair.java)): $T(n) = 2T(n/2) + \Theta(n)$, so $T(n) = \Theta(n \log n)$. Depth is $O(\log n)$.

## Plots and Empirical Results

- **Time vs n**: All algorithms show expected scaling: $n \log n$ for MergeSort, QuickSort, Closest Pair; linear for Deterministic Select.
- **Depth vs n**: Maximum recursion depth grows as $\log n$ for all except QuickSort worst-case.
- **Constant-factor effects**: For small $n$, insertion sort cutoff improves cache locality. For large $n$, memory allocation and garbage collection can affect timings, especially for MergeSort.

## Summary

Theoretical and empirical results are closely aligned. Minor mismatches are due to constant factors (cache, branch prediction, GC). MergeSort and QuickSort both scale as $n \log n$, but MergeSort's extra allocations can increase memory usage. Deterministic Select achieves linear time as predicted. The metrics and tests confirm correctness and performance.

## References

- [src/main/java/com/helpicantchoose/](src/main/java/com/helpicantchoose/)
- [src/test/java/](src/test/java/)