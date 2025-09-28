import com.helpicantchoose.MergeSort;
import com.helpicantchoose.Metrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {
    @Test
    void testRandomArray() {
        int[] arr = {5, 2, 9, 1, 5, 6};
        int[] expected = {1, 2, 5, 5, 6, 9};
        Metrics m = new Metrics();
        MergeSort.sort(arr, m);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        Metrics m = new Metrics();
        MergeSort.sort(arr, m);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testAllEqual() {
        int[] arr = {7, 7, 7, 7};
        int[] expected = {7, 7, 7, 7};
        Metrics m = new Metrics();
        MergeSort.sort(arr, m);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testEmptyArray() {
        int[] arr = {};
        int[] expected = {};
        Metrics m = new Metrics();
        MergeSort.sort(arr, m);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testSingleElement() {
        int[] arr = {42};
        int[] expected = {42};
        Metrics m = new Metrics();
        MergeSort.sort(arr, m);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testMetrics() {
        int[] arr = {3, 2, 1};
        Metrics m = new Metrics();
        MergeSort.sort(arr, m);
        assertTrue(m.comparisons > 0);
        assertTrue(m.maxDepth >= 0);
        assertTrue(m.allocations >= arr.length);
    }
}
