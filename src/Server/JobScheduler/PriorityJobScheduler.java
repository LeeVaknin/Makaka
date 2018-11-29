package Server.JobScheduler;

import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityJobScheduler {


    // Compare between boards size
    public static Comparator<ClientHandlerJob> Comparator = (leftClientHandler, rightClientHandler) -> {
        int leftSize = leftClientHandler.getRequestSize();
        int rightSize = rightClientHandler.getRequestSize();
        return leftSize - rightSize;
    };

    // Variables
    private ExecutorService jobExecutor;
    private ExecutorService jobScheduler = Executors.newSingleThreadExecutor();
    private PriorityBlockingQueue<ClientHandlerJob> priorityQueue;

    // C-TOR
    public PriorityJobScheduler(Integer poolSize, Integer queueSize) {
        jobExecutor = Executors.newFixedThreadPool(poolSize);
        priorityQueue = new PriorityBlockingQueue<>(queueSize, Comparator);
        jobScheduler.execute(() -> {
            while (true) {
                try {
                    if (!priorityQueue.isEmpty())
                        jobExecutor.execute(priorityQueue.take());
                } catch (InterruptedException exception) {
                    System.out.println(String.join(": ", "PriorityJobScheduler.PriorityJobScheduler()", exception.toString()));
                    break;
                }
            }
        });

    }

    // Methods

    public void scheduleJob(ClientHandlerJob job) {
        priorityQueue.add(job);
    }

    public void close() {
        jobExecutor.shutdown();
    }

}
