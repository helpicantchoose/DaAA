import com.helpicantchoose.Metrics;
import com.helpicantchoose.QuickSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {
    @Test
    void testRandomArray() {
        int[] arr = {8, 3, 7, 4, 9, 2};
        int[] expected = {2, 3, 4, 7, 8, 9};
        Metrics m = new Metrics();
        QuickSort.sort(arr, m);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        Metrics m = new Metrics();
        QuickSort.sort(arr, m);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testAllEqual() {
        int[] arr = {5, 5, 5, 5};
        int[] expected = {5, 5, 5, 5};
        Metrics m = new Metrics();
        QuickSort.sort(arr, m);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testEmptyArray() {
        int[] arr = {};
        int[] expected = {};
        Metrics m = new Metrics();
        QuickSort.sort(arr, m);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testSingleElement() {
        int[] arr = {42};
        int[] expected = {42};
        Metrics m = new Metrics();
        QuickSort.sort(arr, m);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testMetrics() {
        int[] arr = {4, 1, 3};
        Metrics m = new Metrics();
        QuickSort.sort(arr, m);
        assertTrue(m.comparisons > 0);
        assertTrue(m.maxDepth >= 0);
        assertTrue(m.allocations >= 0);
    }
}
