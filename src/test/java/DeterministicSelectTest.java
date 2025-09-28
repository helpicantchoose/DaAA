import com.helpicantchoose.DeterministicSelect;
import com.helpicantchoose.Metrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

class DeterministicSelectTest {
    @Test
    void testSelectMiddle() {
        int[] arr = {7, 2, 9, 4, 1};
        Metrics m = new Metrics();
        int k = 2; // 0-based: 3rd smallest
        int result = DeterministicSelect.select(arr.clone(), k, m);
        assertEquals(4, result);
    }

    @Test
    void testSelectMin() {
        int[] arr = {5, 3, 8, 1, 2};
        Metrics m = new Metrics();
        int result = DeterministicSelect.select(arr.clone(), 0, m);
        assertEquals(1, result);
    }

    @Test
    void testSelectMax() {
        int[] arr = {5, 3, 8, 1, 2};
        Metrics m = new Metrics();
        int result = DeterministicSelect.select(arr.clone(), arr.length - 1, m);
        assertEquals(8, result);
    }

    @Test
    void testAllEqual() {
        int[] arr = {4, 4, 4, 4};
        Metrics m = new Metrics();
        for (int k = 0; k < arr.length; k++) {
            assertEquals(4, DeterministicSelect.select(arr.clone(), k, m));
        }
    }

    @Test
    void testSingleElement() {
        int[] arr = {42};
        Metrics m = new Metrics();
        assertEquals(42, DeterministicSelect.select(arr.clone(), 0, m));
    }

    @Test
    void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        Metrics m = new Metrics();
        for (int k = 0; k < arr.length; k++) {
            assertEquals(arr[k], DeterministicSelect.select(arr.clone(), k, m));
        }
    }

    @Test
    void testMetrics() {
        int[] arr = {7, 2, 9, 4, 1};
        Metrics m = new Metrics();
        DeterministicSelect.select(arr.clone(), 2, m);
        assertTrue(m.comparisons > 0);
        assertTrue(m.maxDepth >= 0);
    }

    @Test
    void testSelectAgainstSort() {
        int[] arr = {10, 3, 5, 7, 2, 8, 6, 1, 4, 9};
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        Metrics m = new Metrics();
        for (int k = 0; k < arr.length; k++) {
            assertEquals(sorted[k], DeterministicSelect.select(arr.clone(), k, m));
        }
    }
}
