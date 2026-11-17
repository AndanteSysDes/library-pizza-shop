package main.threads.daytwo.ticketmaster;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        TicketOffice office = new TicketOffice();

        BuyTicketTask task1 = new BuyTicketTask(office, "Yahya");
        BuyTicketTask task2 = new BuyTicketTask(office, "Linus");

        Thread thread1 = new Thread(task1, "worker-1");
        Thread thread2 = new Thread(task2, "worker-2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Biljetter kvar " + office.getTickets());


    }

}
