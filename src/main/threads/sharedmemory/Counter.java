package main.threads.sharedmemory;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter implements Runnable {


    private final AtomicInteger counter = new AtomicInteger(0);

    // private int count = 0;

    public void increment(){
        counter.getAndIncrement();

    }

    public  int getCount(){
        return counter.get();
    }

    @Override
    public void run() {
        for (int i = 0; i < 100_000; i++) {
            increment();
        }
    }
}
