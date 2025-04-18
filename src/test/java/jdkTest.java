public class jdkTest {

    public static void main(String[] args) {
        // Pin the producer thread to CPU 2
        Affinity.setAffinity(2);

        try (ChronicleQueue cq = SingleChronicleQueueBuilder.binary(tmp)
                .blockSize(blocksize)
                .rollCycle(ROLL_CYCLE)
                .build()) {

            ExcerptAppender appender = cq.acquireAppender();
            final long nano_delay = 1_000_000_000L/MSGS_PER_SECOND;

            for (int i = -WARMUP; i < COUNT; ++i) {

                long startTime = System.nanoTime();
                try (DocumentContext dc = appender.writingDocument()) {

                    Bytes bytes = dc.wire().bytes();

                    data.writeLong(0, startTime);
                    bytes.write(data,0, MSGSIZE);
                }
                long delay = nano_delay - (System.nanoTime() - startTime);
                spin_wait(delay);
            }
        }
    }
}
