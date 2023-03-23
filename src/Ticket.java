/**
 * This class is the Ticket class.
 * It contains the ticket ID, row, seat, price and person ID.
 * It also contains the constructor, getters and setters and toString method.
 * @author Imeth Pathirana
 * @version 2023.03.18
 */
public class Ticket {
    private String ticket_id;
    private int row;
    private int seat;
    private double price;
    private int person_id;
    private Person person;
    /**
     * This method is the constructor of the Ticket class.
     * @param ticket_id The ticket ID.
     * @param row The row of the ticket.
     * @param seat The seat of the ticket.
     * @param price The price of the ticket.
     * @param person_id The person ID of the ticket.
     */

    // Constructor
    public Ticket(String ticket_id, int row, int seat, double price, int person_id, Person person) {
        this.ticket_id = ticket_id;
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person_id = person_id;
        this.person = person;
    }
    // Getters and Setters
    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }
    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(String ticket_id) {
        this.ticket_id = ticket_id;
    }

    public void setRow(int row) {
        this.row = row;
    }


    public void setSeat(int seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // toString method
    public String toString(){
        return "Person : [ "+person+" ] Ticket ID : "+ticket_id+" Row : " + row +" Seat : "+seat+" Price : £"+price;
    }
}
