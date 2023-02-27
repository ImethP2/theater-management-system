public class Ticket<person_id> {
    private String ticket_id;
    private int row;
    private int seat;
    private double price;
    private int person_id;
    //public Person.person_object;

    public Ticket(String ticket_id, int row, int seat, double price, int person_id) {
        this.ticket_id = ticket_id;
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person_id = person_id;
    }

    public Ticket() {

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

    //Ticket ticket_object = new Ticket(ticket_id, row, seat, price);

    public String toString(){
        return "Ticket ID : "+ticket_id+" Row : " + row +" Seat : "+seat+" Price : £"+price;
    }
}
