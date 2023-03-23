import java.io.*;
import java.util.*;

/**
 * This class is the main class of the "Theater" application.
 * "Theater" is a very simple, text based theater booking system.
 *
 * @author imeth hansidu pathirana
 * @version 2023.03.18
 */
public class Theater {

    public static int row;
    public static int seat;
    static int person_tickets;
    private static int person_id;
    static int all_tickets;

    // The Main Method
    public static void main (String[] args) {
        person_id=0;
        int[] row1 = new int[12];
        int[] row2 = new int[16];
        int[] row3 = new int[20];
        ArrayList<Person> Customers = new ArrayList<>();
        ArrayList<Ticket> Sold_Tickets = new ArrayList<>();
        ArrayList<Person> Sorted_Tickets = new ArrayList<>();
        Scanner menu_input = new Scanner(System.in);

        while (true){
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("Welcome to the New Theatre");
            System.out.println(" ");
            System.out.println("""
                                      Menu
                    -------------------------------------------
                    1) Buy a ticket
                    2) Print seating area
                    3) Cancel ticket
                    4) List available seats
                    5) Save to file
                    6) Load from file
                    7) Print ticket information and total price
                    8) Sort tickets by price
                    0) Quit""");

            System.out.print("Enter the menu item number : ");
            try {
                int choice = menu_input.nextInt();
                if (choice == 0){
                    System.out.println("Exiting the program.");
                    break;
                } else if (choice == 1) {
                    person_id += 1;
                    buy_tickets(row1, row2, row3, person_id, Customers, Sold_Tickets);

                } else if (choice == 2) {
                    print_seating_area(row1, row2, row3);
                } else if (choice == 3) {
                    cancel_ticket(row1, row2, row3, Sold_Tickets, Customers);
                } else if (choice == 4) {
                    show_available(row1, row2, row3);

                } else if (choice == 5) {
                    save(row1, row2, row3, Customers, Sold_Tickets);

                } else if (choice == 6) {
                    read(row1, row2, row3, Customers, Sold_Tickets);

                } else if (choice == 7) {
                    show_tickets_info( Sold_Tickets);

                } else if (choice == 8) {
                    sort_tickets(Customers, Sorted_Tickets);

                } else {
                    System.err.println("Enter a valid choice!");
                }
            } catch (Exception e) {
                System.err.println("Please enter a valid input");
                System.err.println("Quitting....");
                break;
            }
        }
    }

    /**
     * This method is used to sort the tickets by price
     * @param row1 row 1 of the theater
     * @param row2 row 2 of the theater
     * @param row3 row 3 of the theater
     * @param person_id person id
     * @param Customers list of customers
     * @param Sold_Tickets list of sold tickets
     */

    // Buy Tickets Method
    public static void buy_tickets(int[] row1, int[] row2, int[] row3, int person_id, ArrayList<Person> Customers, ArrayList<Ticket> Sold_Tickets) {
        // Variables
        String name;
        String surname;
        String email;
        double full_cost = 0;
        Scanner input = new Scanner(System.in);
        int free_seats = 48 - all_tickets;
        Scanner input_person = new Scanner(System.in);
        int full_tkt ;
        int half_tkt ;
        // Asking the user to enter the number of tickets
        System.out.println("""
                                 <--Price Table-->
                ---------------------------------------------------
                |  Full Ticket  |  Half Ticket  | Row No. | Price |
                ---------------------------------------------------
                |       1       |       0       |    1    |  £10  |
                |       1       |       0       |    2    |  £20  |
                |       1       |       0       |    3    |  £30  |
                |       0       |       1       |    1    |  £5   |
                |       0       |       1       |    2    |  £10  |
                |       0       |       1       |    3    |  £15  |
                ---------------------------------------------------""");
        System.out.println("How many tickets do you need ?");
        System.out.println("P.S. There are only " + free_seats + " seats left.");
        System.out.println("(If you don't need any tickets, enter 0 for both adults and kids.)");
        // Getting the number of tickets
        do {
            System.out.println(" ");
            System.out.print("Adults : ");
            full_tkt = input.nextInt();
            System.out.print("Kids : ");
            half_tkt = input.nextInt();
            person_tickets = full_tkt + half_tkt;
            if (free_seats < person_tickets){
                System.err.println("Please note that there are only "+free_seats+" available.");
                System.err.println("Please enter the number of seats you need again.");
                System.out.println(" ");
            }
        }while (free_seats < person_tickets);
        all_tickets += person_tickets;
        System.out.println(" ");
        System.out.println("Available seats: ");
        show_available(row1, row2, row3);
        System.out.println(" ");

        // Asking the user to enter the name, surname and email address
        if (person_tickets > 0) {
            do {
                System.out.print("Enter your name: ");
                name = input_person.nextLine();
                if (!PersonValidators.NameChecker(name)) {
                    System.err.println("Invalid input");
                    System.out.println(" ");
                }
            } while (!PersonValidators.NameChecker(name));


            do {
                System.out.print("Enter your surname: ");
                surname = input_person.nextLine();
                if (!PersonValidators.NameChecker(surname)) {
                    System.err.println("Invalid input!");
                    System.out.println(" ");
                }
            } while (!PersonValidators.NameChecker(surname));


            do {
                System.out.print("Enter your email address: ");
                email = input_person.nextLine();
                if (!PersonValidators.EmailChecker(email)) {
                    System.err.println("Invalid email address!");
                    System.out.println(" ");
                }
            } while (!PersonValidators.EmailChecker(email));

            setting_person_object(person_id, name, surname, email);

            // Getting the full tickets
            if (full_tkt > 0) {
                System.out.println("Getting the Full Tickets.");
                for (int i = 0; i < full_tkt; i++) {
                    // Getting the tickets
                    Getting_Tickets(row1, row2, row3);
                    //setting the ticket id
                    String tkt_id = person_id + "-" + row + "-" + seat + "-F";
                    // no need to set the row and seat again
                    double price = 0;
                    if (row == 1) {
                        price = 10.00;
                    } else if (row == 2) {
                        price = 20.00;
                    } else if (row == 3) {
                        price = 30.00;
                    }
                    // Adding the price to the full cost
                    full_cost += price;
                    // Setting the ticket object
                    setting_ticket_object(tkt_id, row, seat, price, person_id, Sold_Tickets, setting_person_object(person_id, name, surname, email));
                }
            }
            // Getting the half tickets
            if (half_tkt > 0) {
                System.out.println("Getting the Half Tickets.");
                for (int i = 0; i < half_tkt; i++) {
                    // Getting the tickets
                    Getting_Tickets(row1, row2, row3);
                    //setting the ticket id
                    String tkt_id = person_id + "-" + row + "-" + seat + "-H";
                    // no need to set the row and seat again
                    double price = 0;
                    if (row == 1) {
                        price = 5.00;
                    } else if (row == 2) {
                        price = 10.00;
                    } else if (row == 3) {
                        price = 15.00;
                    }
                    // Adding the price to the full cost
                    full_cost += price;
                    // Setting the ticket object
                    setting_ticket_object(tkt_id, row, seat, price, person_id, Sold_Tickets, setting_person_object(person_id, name, surname, email));
                }
            }
            // Setting the person object
            setting_person_object(person_id, name, surname, email, full_cost, Customers);
            System.out.println(" ");
            System.out.println("Your tickets are ready.");
            // Printing the tickets
            for (int i = all_tickets - person_tickets; i < all_tickets; i++) {
                System.out.println(Sold_Tickets.get(i).print());
            }
        }
    }

    //this is an overloaded method of the setting_person_object method
    public static Person setting_person_object(int person_id, String name, String surname, String email){
        // Setting the person object
        Person person_object = new Person(person_id, name, surname, email);
        person_object.setPerson_id(person_id);
        person_object.setName(name);
        person_object.setSurname(surname);
        person_object.setEmail(email);
        return person_object;
    }

    public static Person setting_person_object(int person_id, String name, String surname, String email, double full_cost){
        // Setting the person object
        Person person_object = new Person(person_id, name, surname, email, full_cost);
        person_object.setPerson_id(person_id);
        person_object.setName(name);
        person_object.setSurname(surname);
        person_object.setEmail(email);
        person_object.setFull_cost(full_cost);
        return person_object;
    }

    //setting the person object
    public static void setting_person_object(int person_id, String name, String surname, String email, double full_cost, ArrayList<Person> Customers){
        Person person_object = new Person(person_id, name, surname, email, full_cost);
        person_object.setPerson_id(person_id);
        person_object.setName(name);
        person_object.setSurname(surname);
        person_object.setEmail(email);
        person_object.setFull_cost(full_cost);
        // Adding the person object to the array list
        Customers.add(person_object);
    }

    //Setting the ticket object
    public static void setting_ticket_object(String tkt_id,int row, int seat, double price, int person_id, ArrayList<Ticket> Sold_Tickets, Person person_object){
        Ticket ticket_object = new Ticket(tkt_id, row, seat, price, person_id,person_object);
        ticket_object.setTicket_id(tkt_id);
        ticket_object.setRow(row);
        ticket_object.setSeat(seat);
        ticket_object.setPrice(price);
        ticket_object.setPerson_id(person_id);
        ticket_object.setPerson(person_object);
        // Adding the ticket object to the array list
        Sold_Tickets.add(ticket_object);
    }

    // Getting the tickets
    public static void Getting_Tickets(int[] row1, int[] row2, int[] row3) {
        Scanner input = new Scanner(System.in);
        while (true){
            try {
                // Getting the row and seat number
                System.out.print("Please enter the row number : ");
                row = input.nextInt();
                if (row == 1) {
                    System.out.print("Please enter the seat number : ");
                    seat = input.nextInt();
                    if (seat > 0 && seat < 13) {
                        if (row1[seat - 1] == 0) {
                            row1[seat - 1] = 1;
                            break;
                        } else {
                            System.err.println("This seat is already booked. Please choose another seat.");
                            System.out.println( " ");
                        }
                    } else {
                        System.err.println("Please enter a valid seat number.");
                        System.out.println( " ");
                    }
                } else if (row == 2) {
                    System.out.print("Please enter the seat number : ");
                    seat = input.nextInt();
                    if (seat > 0 && seat < 17) {
                        if (row2[seat - 1] == 0) {
                            row2[seat - 1] = 1;
                            break;
                        } else {
                            System.err.println("This seat is already booked. Please choose another seat.");
                            System.out.println( " ");
                        }
                    } else {
                        System.err.println("Please enter a valid seat number.");
                        System.out.println( " ");
                    }
                } else if (row == 3) {
                    System.out.print("Please enter the seat number : ");
                    seat = input.nextInt();
                    if (seat > 0 && seat < 21) {
                        if (row3[seat - 1] == 0) {
                            row3[seat - 1] = 1;
                            break;
                        } else {
                            System.err.println("This seat is already booked. Please choose another seat.");
                            System.out.println( " ");
                        }
                    } else {
                        System.err.println("Please enter a valid seat number.");
                        System.out.println( " ");
                    }
                } else {
                    System.err.println("Please enter a valid row number.");
                    System.out.println( " ");
                }
            } catch (Exception e) {
                System.err.println("Please enter a valid input");
                System.out.println( " ");
            }
        }
    }
    // Printing the seating area
    public static void print_seating_area(int[] row1, int[] row2, int[] row3){
        System.out.println("    ***************");
        System.out.println("     * S T A G E *");
        System.out.println("    ***************");
        System.out.println(" ");
        System.out.print("    ");
        for(int ir1 =1; ir1<13;ir1++){
            if (ir1==6){
                if (row1[ir1-1] == 0) {
                    System.out.print("O");
                } else {
                    System.out.print("X");
                }
                System.out.print("  ");
            }else{
                if (row1[ir1-1] == 0) {
                    System.out.print("O");
                } else {
                    System.out.print("X");
                }
            }
        }
        System.out.println(" ");
        System.out.print("  ");
        for(int ir2 =1; ir2<17;ir2++){
            if (ir2==8){
                if (row2[ir2-1] == 0) {
                    System.out.print("O");
                } else {
                    System.out.print("X");
                }
                System.out.print("  ");
            }else{
                if (row2[ir2-1] == 0) {
                    System.out.print("O");
                } else {
                    System.out.print("X");
                }
            }
        }
        System.out.println(" ");
        for(int ir3 =1; ir3<21;ir3++){
            if (ir3==10){
                if (row3[ir3-1] == 0) {
                    System.out.print("O");
                } else {
                    System.out.print("X");
                }
                System.out.print("  ");
            }else{
                if (row3[ir3-1] == 0) {
                    System.out.print("O");
                } else {
                    System.out.print("X");
                }
            }
        }
    }

    // Canceling the tickets
    public static void cancel_ticket(int[] row1,int[] row2,int[] row3, ArrayList<Ticket> Sold_Tickets, ArrayList<Person> Customers){
        int person_tickets = 0;
        int index = 0;
        int[] cancel_row_seat = new int[2];
        boolean is_his_ticket = false;
        Scanner input = new Scanner(System.in);
        Scanner get_answer = new Scanner(System.in);
        System.out.println("--Only 50% of the ticket price will be refunded--");
        most_outer:
        while (true) {
            try{
                // Getting the person id
                System.out.print("Please enter your Person-ID : ");
                int person_id = input.nextInt();
                System.out.print("Please enter your email address : ");
                String person_email = get_answer.nextLine();
                // Checking if the person id is valid
                for (Person person : Customers) {
                    if ((person.getPerson_id() == person_id) && (person.getEmail().equals(person_email))) {
                        //make sure that the person is the owner of the ticket
                        System.out.println("Is your name " + person.getName() + " " + person.getSurname() + "? (Y/N)");
                        String answer = get_answer.nextLine();
                        answer = answer.toUpperCase();
                        if (answer.equals("Y")) {
                            // Getting the number of tickets that the person has
                            for (Ticket i : Sold_Tickets) {
                                if (i.getPerson_id() == person_id) {
                                    person_tickets++;
                                }
                            }
                            // creating a 2D array to store the row and seat number of the tickets that the person has
                            int[][] person_row_seat = new int[person_tickets][2];
                            System.out.println("Your tickets are : ");
                            // Storing the row and seat number of the tickets that the person has
                            for (Ticket i : Sold_Tickets) {
                                if (i.getPerson_id() == person_id) {
                                    person_row_seat[index][0] = i.getRow();
                                    person_row_seat[index][1] = i.getSeat();
                                    System.out.println("Row : " + i.getRow() + " Seat : " + i.getSeat());
                                    index++;
                                }
                            }
                            while (true) {
                                // Getting the row and seat number of the ticket that the person wants to cancel
                                Scanner input_row_seat = new Scanner(System.in);
                                System.out.print("Enter the row number : ");
                                int row = input_row_seat.nextInt();
                                System.out.print("Enter the seat number : ");
                                int seat = input_row_seat.nextInt();
                                // Checking if the row and seat number is valid
                                if ((row == 1 && (seat > 0 && seat < 13)) || (row == 2 && (seat > 0 && seat < 17)) || (row == 3 && (seat > 0 && seat < 21))) {
                                    // Checking if the person is the owner of the ticket
                                    cancel_row_seat[0] = row;
                                    cancel_row_seat[1] = seat;
                                    for (int[] row_seat : person_row_seat) {
                                        if (Arrays.equals(row_seat, cancel_row_seat)) {
                                            is_his_ticket = true;
                                            break;
                                        }
                                    }
                                    // Canceling the ticket from rows and seats
                                    if (is_his_ticket) {
                                        if (row == 1) {
                                            row1[seat - 1] = 0;
                                        } else if (row == 2) {
                                            row2[seat - 1] = 0;
                                        } else if (row == 3) {
                                            row3[seat - 1] = 0;
                                        }
                                        for (Ticket i : Sold_Tickets) {
                                            if (i.getPerson_id() == person_id && i.getRow() == row && i.getSeat() == seat) {
                                                // removing the ticket from the sold tickets array list
                                                Sold_Tickets.remove(i);
                                                // Updating the number of tickets
                                                all_tickets--;
                                                double deducting_price = i.getPrice();
                                                //Updating the total cost of the person
                                                for (Person customer : Customers) {
                                                    if (customer.getPerson_id() == person_id) {
                                                        String name = customer.getName();
                                                        String surname = customer.getSurname();
                                                        String email = customer.getEmail();
                                                        double new_total = customer.getFull_cost() - deducting_price;
                                                        Person new_person = setting_person_object(person_id, name, surname, email, new_total);
                                                        Customers.set(person_id - 1, new_person);
                                                        System.out.println("Your ticket has been cancelled.");
                                                        System.out.println("Your new total is : " + Customers.get(person_id - 1).getFull_cost());
                                                    }
                                                }
                                                break;
                                            }
                                        }
                                        break most_outer;
                                    } else {
                                        System.err.println("You don't have a ticket in this row and seat number.");
                                        System.out.println(" ");
                                    }
                                } else {
                                    System.err.println("Please enter a valid row and seat number.");
                                    System.out.println(" ");
                                }
                            }
                        } else if (answer.equals("N")) {
                            continue most_outer;
                        } else {
                            System.err.println("Please enter a valid answer.");
                            System.out.println(" ");
                        }
                    } else {
                        System.err.println("Please enter a valid Person-ID or Email.");
                        System.out.println(" ");
                        break;
                    }
                }
            }catch (Exception e){
                System.err.println("Please enter a valid Person-ID or Email.");
                System.out.println(" ");
            }
        }

    }


    // Method to show the available seats
    public static void show_available(int[] row1,int[] row2,int[] row3){
        System.out.print("Seats available in row 1 : ");
        for (int i =0; i<12;i++){
            row_seats(row1, i);
        }
        System.out.println(" ");
        System.out.print("Seats available in row 2 : ");
        for (int i =0; i<16;i++){
            row_seats(row2, i);
        }
        System.out.println(" ");
        System.out.print("Seats available in row 3 : ");
        for (int i =0; i<20;i++){
            row_seats(row3, i);
        }
    }

    public static void row_seats(int[] row, int i) {
        if (row[i]==0){
            try{
                if (row[i+1] == 0) {
                    System.out.print(i+1 + ", ");
                }
            }catch (Exception e){
                System.out.print(i+1 + ".");
            }
        }
    }

    // Method to save the data
    public static void save(int[] row1,int[] row2,int[] row3, ArrayList<Person> Customers, ArrayList<Ticket> Sold_Tickets)throws Exception {
        // Saving the row and seat data
        FileWriter writer_row = new FileWriter("row-seat-data.txt");
        BufferedWriter buffer_row = new BufferedWriter(writer_row);
        for (int j : row1) {
            String rowS_1s = Integer.toString(j);
            buffer_row.write(rowS_1s);
        }
        buffer_row.newLine();
        for (int j : row2) {
            String rowS_2s = Integer.toString(j);
            buffer_row.write(rowS_2s);
        }
        buffer_row.newLine();
        for (int i : row3) {
            String rowS_3s = Integer.toString(i);
            buffer_row.write(rowS_3s);
        }
        buffer_row.close();
        writer_row.close();

        // Saving the person and ticket data
        FileWriter write_person = new FileWriter("people-data.txt");
        BufferedWriter buffer_person = new BufferedWriter(write_person);
        for (Person i: Customers){
            buffer_person.write(i.getPerson_id()+"-"+i.getName()+"-"+i.getSurname()+"-"+i.getEmail()+"-"+i.getFull_cost());
            buffer_person.newLine();
        }
        buffer_person.close();
        write_person.close();

        FileWriter write_ticket = new FileWriter("ticket-data.txt");
        BufferedWriter buffer_ticket = new BufferedWriter(write_ticket);
        for (Ticket i: Sold_Tickets){
            buffer_ticket.write(i.getTicket_id()+"/"+i.getRow()+"/"+i.getSeat()+"/"+ i.getPrice() +"/"+i.getPerson_id()+"/"+Customers.get(i.getPerson_id()-1).getName()+"/"+Customers.get(i.getPerson_id()-1).getSurname()+"/"+Customers.get(i.getPerson_id()-1).getEmail());
            buffer_ticket.newLine();
        }
        buffer_ticket.close();
        write_ticket.close();
        System.out.println("Successfully saved the data.");
    }
    // Method to read the data
    public static void read(int[] row1,int[] row2,int[] row3, ArrayList<Person> Customers, ArrayList<Ticket> Sold_Tickets) throws IOException {
        // Reading the row and seat data
        BufferedReader reader_row = new BufferedReader(new FileReader("row-seat-data.txt"));
        String line_row = reader_row.readLine();
        for (int i = 0; i < line_row.length(); i++){
            row1[i] = Integer.parseInt(String.valueOf(line_row.charAt(i)));
            if (line_row.charAt(i)=='1'){
                all_tickets++;
            }
        }
        line_row = reader_row.readLine();
        for (int i = 0; i < line_row.length(); i++){
            row2[i] = Integer.parseInt(String.valueOf(line_row.charAt(i)));
            if (line_row.charAt(i)=='1'){
                all_tickets++;
            }
        }
        line_row = reader_row.readLine();
        for (int i = 0; i < line_row.length(); i++){
            row3[i] = Integer.parseInt(String.valueOf(line_row.charAt(i)));
            if (line_row.charAt(i)=='1'){
                all_tickets++;
            }
        }
        reader_row.close();

        // Reading the person and ticket data
        BufferedReader line_person = new BufferedReader(new FileReader("people-data.txt"));

        int line_count_person = 0;

        try {
            File person = new File("people-data.txt");
            Scanner person_scanner = new Scanner(person);
            while(person_scanner.hasNextLine()) {
                person_scanner.nextLine();
                line_count_person++;
            }
            // close scanner
            person_scanner.close();
        } catch (Exception e) {
            e.getStackTrace();
        }

        for (int i =0; i<line_count_person ;i++){
            String line_people = line_person.readLine();
            String[] person_arr = line_people.split("-");
            person_id = Integer.parseInt(person_arr[0]);
            String name = person_arr[1];
            String surname = person_arr[2];
            String email = person_arr[3];
            double full_cost = Double.parseDouble(person_arr[4]);
            setting_person_object(person_id, name, surname, email, full_cost, Customers);
        }
        person_id=line_count_person;
        line_person.close();


        BufferedReader line_ticket = new BufferedReader(new FileReader("ticket-data.txt"));

        int line_count_ticket = 0;

        try {
            File ticket = new File("ticket-data.txt");
            Scanner ticket_scanner = new Scanner(ticket);
            while(ticket_scanner.hasNextLine()) {
                ticket_scanner.nextLine();
                line_count_ticket++;
            }
            ticket_scanner.close();
        } catch (Exception e) {
            e.getStackTrace();
        }

        for (int i =0; i<line_count_ticket ;i++){
            String lines_tkt = line_ticket.readLine();
            String[] ticket_arr = lines_tkt.split("/");
            String tkt_id = ticket_arr[0];
            int row = Integer.parseInt(ticket_arr[1]);
            int seat = Integer.parseInt(ticket_arr[2]);
            double price = Double.parseDouble(ticket_arr[3]);
            person_id = Integer.parseInt(ticket_arr[4]);
            String name = ticket_arr[5];
            String surname = ticket_arr[6];
            String email = ticket_arr[7];
            setting_ticket_object(tkt_id, row, seat, price, person_id, Sold_Tickets, setting_person_object(person_id, name, surname, email));
        }
        line_person.close();
        System.out.println("Successfully retrieved the data.");
    }

    // Method to show the tickets info
    public static void show_tickets_info( ArrayList<Ticket> Sold_Tickets){
        int total_cost = 0;
        System.out.println("Tickets that have been issued are : ");
        for (Ticket tickets : Sold_Tickets){
            System.out.println(tickets.print());
            total_cost += tickets.getPrice();
            System.out.println(" ");
        }
        System.out.println(" ");
        System.out.println("Total number of tickets sold is : " + Sold_Tickets.size());
        System.out.println("Total income is : £" + total_cost);
    }

    // Method to sort the tickets
    public static void sort_tickets(ArrayList<Person> Customers, ArrayList<Person> Sorted_Tickets){
        int n = Customers.size();
        Sorted_Tickets.addAll(Customers);
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (Sorted_Tickets.get(j).getFull_cost() < Sorted_Tickets.get(j+1).getFull_cost())
                {
                    Person temp = Sorted_Tickets.get(j);
                    Sorted_Tickets.set(j, Sorted_Tickets.get(j+1));
                    Sorted_Tickets.set(j+1, temp);
                }
        for (Person person : Sorted_Tickets){
            System.out.println(person.toStringFull());
        }
        // Clearing the arraylist
        //if not cleared, the arraylist will keep adding the sorted customers
        Sorted_Tickets.clear();
    }
}
