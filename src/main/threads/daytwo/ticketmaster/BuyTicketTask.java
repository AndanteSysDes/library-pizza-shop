package main.threads.daytwo.ticketmaster;

public class BuyTicketTask implements Runnable{

    private TicketOffice office;
    private String customer;

    public BuyTicketTask(TicketOffice office, String customer) {
        this.office = office;
        this.customer = customer;
    }

    @Override
    public void run(){
        boolean success = office.buyTicket();
        if (success){
            System.out.println(customer + " köpte biljetten");
        } else {
            System.out.println( customer + " fick ingen biljett!");
        }
    }
}
