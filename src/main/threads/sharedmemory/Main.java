package main.threads.sharedmemory;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Counter counter = new Counter();
        ExecutorService pool = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 10; i++) {
            pool.submit(counter);
        }

        pool.shutdown();

        boolean finished = pool.awaitTermination(1, TimeUnit.MINUTES);

        if (!finished){
            pool.shutdownNow();
            System.out.println("Alla uppgifter hann inte bli klara");
            return;
        }


        System.out.println("Förväntat: 1000000");
        System.out.println("Faktiskt " + counter.getCount());












    }
}

//int cores = Runtime.getRuntime().availableProcessors();
//System.out.println("Cores: " + cores);


        /*

        Counter counter = new Counter();
        counter.increment();

        ArrayList<Thread> threads = new ArrayList<>();

        int numberOfThreads = 10;
        int increasePerThread = 100_000;

        for (int i = 0; i < numberOfThreads; i++) {
            CounterTask task = new CounterTask(counter,increasePerThread);
            Thread worker = new Thread(task, "worker-" + i);
            threads.add(worker);
        }

        for(Thread worker: threads){
            worker.start();
        }

        for (Thread worker: threads){
            worker.join();
        }

        int expected = numberOfThreads * increasePerThread;
        int actual = counter.getCount();

        System.out.println("Förväntat: " + expected);
        System.out.println("Faktiskt: " + actual);






        */

