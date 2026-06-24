import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BankStatementBatchProcessor {

    //  AtomicInteger prevents race conditions when multiple threads update the counter
    private final AtomicInteger processedCount = new AtomicInteger(0);

    public void process(List<StatementRecord> records) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (StatementRecord record : records) {

            executor.submit(() -> {
                processRecord(record);

                //  Atomic increment is thread-safe
                processedCount.incrementAndGet();
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.MINUTES);
    }

    public int getProcessedCount() {
        return processedCount.get();
    }

    private void processRecord(StatementRecord record) {
        // existing implementation
    }
}


// BUG EXPLANATION 

// processedCount++ is not atomic.
// Multiple threads can read the same value simultaneously,
// increment it, and overwrite each other's updates, causing
// lost increments and an incorrect final count.
