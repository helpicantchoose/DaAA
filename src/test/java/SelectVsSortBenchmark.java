import com.helpicantchoose.DeterministicSelect;
import com.helpicantchoose.Metrics;
import org.openjdk.jmh.annotations.*;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class SelectVsSortBenchmark {
    @Param({"1000", "10000", "100000"})
    public int n;

    public int[] data;
    public int k;

    @Setup(Level.Invocation)
    public void setUp() {
        Random rnd = new Random(42);
        data = rnd.ints(n, 0, 1000000).toArray();
        k = rnd.nextInt(n);
    }

    @Benchmark
    public int select() {
        Metrics m = new Metrics();
        return DeterministicSelect.select(data.clone(), k, m);
    }

    @Benchmark
    public int sortAndPick() {
        int[] arr = data.clone();
        Arrays.sort(arr);
        return arr[k];
    }
}