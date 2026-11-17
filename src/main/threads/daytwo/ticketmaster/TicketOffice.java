package main.threads.daytwo.ticketmaster;

public class TicketOffice {

    private int tickets = 1;
    private final Object lock = new Object();

    public boolean buyTicket() {
        // logik här// mer logik här

            synchronized (lock){
                if (tickets == 0){
                    return false;
                }
                tickets--;
                return true;
            }
        }

        public int getTickets(){
            synchronized (lock){
                return tickets;
            }
        }
}



