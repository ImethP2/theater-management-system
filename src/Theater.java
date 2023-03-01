import java.io.*;
import java.util.*;


public class Theater {
    public static int row;
    public static int seat;
    static int person_tickets;
    static int all_tickets;
    public static void main (String[] args) {
        int person_id=0;
        int[] row1 = new int[12];
        int[] row2 = new int[16];
        int[] row3 = new int[20];
        ArrayList<Person> Customers = new ArrayList<>();
        ArrayList<Ticket> Sold_Tickets = new ArrayList<>();
        ArrayList<Person> Sorted_Customers = new ArrayList<>();
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
                    System.out.println("Buy a ticket.");
                    buy_tickets(row1, row2, row3, person_id, Customers, Sold_Tickets);

                } else if (choice == 2) {
                    print_seating_area(row1, row2, row3);
                } else if (choice == 3) {
                    cancel_ticket(row1, row2, row3, person_id, Sold_Tickets);
                } else if (choice == 4) {
                    show_available(row1, row2, row3);

                } else if (choice == 5) {
                    save(row1, row2, row3, Customers, Sold_Tickets);

                } else if (choice == 6) {
                    read(row1, row2, row3, Customers, Sold_Tickets);

                } else if (choice == 7) {
                    show_tickets_info(person_id, Customers);

                } else if (choice == 8) {
                    sort_tickets(Customers, Sorted_Customers);

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

    public static void buy_tickets(int[] row1, int[] row2, int[] row3, int person_id, ArrayList<Person> Customers, ArrayList<Ticket> Sold_Tickets) {
        String name;
        String surname;
        String email;
        double full_cost = 0;
        Scanner input = new Scanner(System.in);
        int free_seats = 48 - all_tickets;
        Scanner input_person = new Scanner(System.in);
        int full_tkt ;
        int half_tkt ;
        System.out.println("How many tickets do you need ?");
        System.out.println("P.S. There are only " + free_seats + " seats left.");
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

        show_available(row1, row2, row3);
        System.out.println(" ");
        do {
            System.out.print("Enter your name: ");
            name = input_person.nextLine();
            if (!PersonValidators.NameChecker(name)) {
                System.err.println("Invalid input");
            }
        } while (!PersonValidators.NameChecker(name));


        do {
            System.out.print("Enter your surname: ");
            surname = input_person.nextLine();
            if (!PersonValidators.NameChecker(surname)) {
                System.err.println("Invalid input!");
            }
        } while (!PersonValidators.NameChecker(surname));


        do {
            System.out.print("Enter your email address: ");
            email = input_person.nextLine();
            if (!PersonValidators.EmailChecker(email)) {
                System.err.println("Invalid email address!");
            }

        } while (!PersonValidators.EmailChecker(email));


        if (full_tkt > 0) {
            System.out.println("Getting the Full Tickets.");
            for (int i = 0; i < full_tkt; i++) {
                Getting_Tickets(row1, row2, row3);
                String tkt_id = person_id + "-" + row + "-" + seat + "-F";
                int row = Theater.row;
                int seat = Theater.seat;
                double price = 20.00;
                full_cost += price;
                setting_ticket_object(tkt_id, row, seat, price, person_id, Sold_Tickets);


            }
        }
        if (half_tkt > 0) {
            System.out.println("Getting the Half Tickets.");
            for (int i = 0; i < half_tkt; i++) {
                Getting_Tickets(row1, row2, row3);
                String tkt_id = person_id + "-" + row + "-" + seat + "-H";
                int row = Theater.row;
                int seat = Theater.seat;
                double price = 10.00;
                full_cost += price;
                setting_ticket_object(tkt_id, row, seat, price, person_id, Sold_Tickets);
            }
        }
        setting_person_object(person_id, name, surname, email, full_cost, Customers);
        for (int i = all_tickets - person_tickets; i < all_tickets; i++) {
            System.out.println(Sold_Tickets.get(i));
        }
    }
    public static void setting_person_object(int person_id, String name, String surname, String email, double full_cost, ArrayList<Person> Customers){
        Person person_object = new Person(person_id, name, surname, email, full_cost);
        person_object.setPerson_id(person_id);
        person_object.setName(name);
        person_object.setSurname(surname);
        person_object.setEmail(email);
        person_object.setFull_cost(full_cost);
        Customers.add(person_object);
    }
    public static void setting_ticket_object(String tkt_id,int row, int seat, double price, int person_id, ArrayList<Ticket> Sold_Tickets){
        Ticket ticket_object = new Ticket(tkt_id, row, seat, price, person_id);
        ticket_object.setTicket_id(tkt_id);
        ticket_object.setRow(row);
        ticket_object.setSeat(seat);
        ticket_object.setPrice(price);
        ticket_object.setPerson_id(person_id);
        Sold_Tickets.add(ticket_object);
    }
    public static void Getting_Tickets(int[] row1, int[] row2, int[] row3) {
        Scanner input = new Scanner(System.in);
        while (true){
            try {
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
                        }
                    } else {
                        System.err.println("Please enter a valid seat number.");
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
                        }
                    } else {
                        System.err.println("Please enter a valid seat number.");
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
                        }
                    } else {
                        System.err.println("Please enter a valid seat number.");
                    }
                } else {
                    System.err.println("Please enter a valid row number.");
                }
            } catch (Exception e) {
                System.err.println("Please enter a valid input");
            }
        }
    }
    public static void print_seating_area(int[] row1, int[] row2, int[] row3){
        System.out.println("     ***********");
        System.out.println("     * STAGE *");
        System.out.println("     ***********");
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

    public static void cancel_ticket(int[] row1,int[] row2,int[] row3,int person_id, ArrayList<Ticket> Sold_Tickets) {
        Scanner input = new Scanner(System.in);
        int[] person_row = new int[person_tickets];
        int[] person_seat = new int[person_tickets];
        int index =0;
        System.out.println("Your tickets are : ");
        for (Ticket i:Sold_Tickets){
            if (i.getPerson_id()==person_id){
                System.out.println("Row : "+i.getRow()+" Seat : "+i.getSeat());
                person_row[index] = i.getRow();
                person_seat[index] = i.getSeat();
                index++;
            }
        }
        outer_loop:
        while (true) {
            System.out.print("Enter the row number : ");
            int row = input.nextInt();
            for (int r : person_row) {
                if (r == row) {
                    System.out.print("Enter the seat number : ");
                    int seat = input.nextInt();
                    for (int s : person_seat) {
                        if (s == seat) {
                            for (Ticket ticket :Sold_Tickets){
                                if (ticket.getRow()==row && ticket.getSeat()==seat){
                                    if (row == 1){
                                        row1[seat-1]=0;
                                    }else if(row == 2){
                                        row2[seat-1] = 0;
                                    }else {
                                        row3[seat-1] = 0;
                                    }
                                    Sold_Tickets.remove(ticket);
                                    break outer_loop;
                                }
                            }
                        }else if (r == 1 && (s<=0 || 13<=s)) {
                            System.err.println("PLease enter a valid seat number.");
                        }else if (r == 2 && (s<=0 || 17<=s)) {
                            System.err.println("PLease enter a valid seat number.");
                        }else if (r == 3 && (s<=0 || 21<=s)) {
                            System.err.println("PLease enter a valid seat number.");
                        }
                    }
                    System.err.println("PLease enter the seat number of ticket that you just bought.");
                } else if (r<1 || 3<r) {
                    System.err.println("PLease enter a valid row number.");
                }
            }System.err.println("PLease enter the row number of ticket that you just bought.");
        }
    }

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

    public static void save(int[] row1,int[] row2,int[] row3, ArrayList<Person> Customers, ArrayList<Ticket> Sold_Tickets)throws Exception {
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
            buffer_ticket.write(i.getTicket_id()+"/"+i.getRow()+"/"+i.getSeat()+"/"+ i.getPrice() +"/"+i.getPerson_id());
            buffer_ticket.newLine();
        }
        buffer_ticket.close();
        write_ticket.close();
        System.out.println("Successfully saved the data.");
    }
    public static void read(int[] row1,int[] row2,int[] row3, ArrayList<Person> Customers, ArrayList<Ticket> Sold_Tickets) throws IOException {
        BufferedReader reader_row = new BufferedReader(new FileReader("row-seat-data.txt"));
        String line_row = reader_row.readLine();
        for (int i = 0; i < line_row.length(); i++){
            row1[i] = Integer.parseInt(String.valueOf(line_row.charAt(i)));
        }
        line_row = reader_row.readLine();
        for (int i = 0; i < line_row.length(); i++){
            row2[i] = Integer.parseInt(String.valueOf(line_row.charAt(i)));
        }
        line_row = reader_row.readLine();
        for (int i = 0; i < line_row.length(); i++){
            row3[i] = Integer.parseInt(String.valueOf(line_row.charAt(i)));
        }
        reader_row.close();



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
            int person_id = Integer.parseInt(person_arr[0]);
            String name = person_arr[1];
            String surname = person_arr[2];
            String email = person_arr[3];
            double full_cost = Double.parseDouble(person_arr[4]);
            setting_person_object(person_id, name, surname, email, full_cost, Customers);
        }
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
            int person_id = Integer.parseInt(ticket_arr[4]);
            setting_ticket_object(tkt_id, row, seat, price, person_id, Sold_Tickets);
        }
        line_person.close();
        System.out.println("Successfully retrieved the data.");
    }
    public static void show_tickets_info(int person_id, ArrayList<Person> Customers){
        for (Person person : Customers){
            if (person.getPerson_id() == person_id){
                System.out.println(person);
            }else{
                System.err.println("Please buy a ticket.");
            }
        }
    }

    public static void sort_tickets(ArrayList<Person> Customers, ArrayList<Person> Sorted_Customers){
        int n = Customers.size();
        Sorted_Customers.addAll(Customers);
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (Sorted_Customers.get(j).getFull_cost() > Sorted_Customers.get(j + 1).getFull_cost()) {
                    Collections.swap(Sorted_Customers, j, j + 1);
                }
            }
        }
        for (Person person : Sorted_Customers){
            System.out.println(person);
        }
    }
}
