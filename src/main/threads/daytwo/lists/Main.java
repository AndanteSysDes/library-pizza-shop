package main.threads.daytwo.lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        List<Integer> numbers = new ArrayList<>();

       // List<Integer> numbers = Collections.synchronizedList(new ArrayList<>());

       // List<Integer> numbers = new CopyOnWriteArrayList<>();

        Thread[] threads = new Thread[10000];

        for (int i = 0; i < threads.length; i++) {
            ListWriter listWriter = new ListWriter(numbers);
            threads[i] = new Thread(listWriter);
            threads[i].start();
        }

        for (Thread worker:  threads) {
            worker.join();
        }

        System.out.println("Förväntat värde 5000");
        System.out.println("Faktiskta värder:  " + numbers.size());


    }
}
