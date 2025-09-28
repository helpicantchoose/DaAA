package com.helpicantchoose;

public class Metrics {
    public long comparisons = 0;
    public long allocations = 0;
    public int depth = 0;
    public int maxDepth = 0;

    public void countComparison() { comparisons++; }
    public void addAlloc(int n) { allocations += n; }

    public void enterRecursion() {
        depth++;
        if (depth > maxDepth) maxDepth = depth;
    }
    public void exitRecursion() {
        depth--;
    }

    public void reset() {
        comparisons = 0;
        allocations = 0;
        depth = 0;
        maxDepth = 0;
    }

    public void writeCSV(String filename, String label) {
        java.io.File file = new java.io.File(filename);
        boolean writeHeader = !file.exists();
        try (java.io.FileWriter fw = new java.io.FileWriter(file, true)) {
            if (writeHeader) {
                fw.write("label,comparisons,allocations,maxDepth\n");
            }
            fw.write(String.format("%s,%d,%d,%d\n",
                    label == null ? "" : label,
                    comparisons, allocations, maxDepth));
        } catch (java.io.IOException e) {
            throw new RuntimeException("Failed to write metrics CSV", e);
        }
    }
}