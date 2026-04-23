import java.util.*;
class Bus {
 private String busName;
   private String busNumber;
 private String source;
  private String destination;
  private int totalSeats;
private int availableSeats;
private int costPerTicket;

    public Bus(String busName, String busNumber, String source, String destination,
               int totalSeats, int availableSeats, int costPerTicket) {
     this.busName = busName;
 this.busNumber = busNumber;
     this.source = source;
     this.destination = destination;
    this.totalSeats = totalSeats;
this.availableSeats = availableSeats;
    this.costPerTicket = costPerTicket;
    }

    public int getAvailableSeat() { 
        return availableSeats;
     }
    public void setAvailableSeat(int seats) { 
        this.availableSeats = seats; 
    }
    public int getCostPerTicket() {
         return costPerTicket; 
        }
    public String getBusNum() { 
        return busNumber; 
    }
    public String getBusName() { 
        return busName;
     }
    public String getSource() {
         return source; 
        }
    public String getDestination() { 
        return destination; 
    }

    public void showDetails() {
        System.out.println("=================================");
        System.out.println("Bus Name      : " + busName);
        System.out.println("Bus Number    : " + busNumber);
        System.out.println("From          : " + source);
        System.out.println("To            : " + destination);
        System.out.println("AvailableSeat : " + availableSeats);
        System.out.println("Price         : " + costPerTicket);
    }
}

class User {
    private String userId;
    private String password;

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getPassword() { 
        return password; 
    }
}
class Booking {
    String userId, busName, busNumber, source, destination;
    int seat;
    double totalCost;

    public Booking(String userId, String busName, String busNumber,String source, String destination, int seat, double totalCost) {
        this.userId = userId;
        this.busName = busName;
        this.busNumber = busNumber;
        this.source = source;
        this.destination = destination;
        this.seat = seat;
        this.totalCost = totalCost;
    }

    public void viewTicket() {
        System.out.println("====== TICKET ======");
        System.out.println("User       : " + userId);
        System.out.println("Bus Name   : " + busName);
        System.out.println("Bus Number : " + busNumber);
        System.out.println("Seats      : " + seat);
        System.out.println("Total Cost : " + totalCost);
    }

    public String getBusNumber() {
        return busNumber;
    }

    public int getSeat() {
        return seat;
    }
}

public class BusTicketBookingSystem {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Bus> buses = new ArrayList<>();
        buses.add(new Bus("APSRTC", "1234", "Anantapur", "Tirupathi", 50, 50, 120));
        buses.add(new Bus("INDRA", "5678", "Hyderabad", "Bangalore", 60, 60, 500));

        HashMap<String, User> userMap = new HashMap<>();
        userMap.put("MasthanSanjay", new User("MasthanSanjay", "MS@2005"));

        ArrayList<Booking> bookings = new ArrayList<>();
        System.out.println("==== LOGIN ====");
        System.out.print("User id: ");
        String userid = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        if (!userMap.containsKey(userid)) {
            System.out.println("User not found ");
            return;
        }

        User u = userMap.get(userid);

        if (!u.getPassword().equals(password)) {
            System.out.println("Wrong Password ");
            return;
        }

        System.out.println("Login Successful ");

        System.out.print("Enter source: ");
        String source = sc.nextLine();

        System.out.print("Enter destination: ");
        String destination = sc.nextLine();

        for (Bus b : buses) {
            if (b.getSource().equalsIgnoreCase(source) &&
                b.getDestination().equalsIgnoreCase(destination)) {
                b.showDetails();
            }
        }


        System.out.print("Enter bus number: ");
        String busNo = sc.nextLine();

        for (Bus b : buses) {
            if (b.getBusNum().equalsIgnoreCase(busNo)) {

                System.out.print("Enter number of seats: ");
                int seats = sc.nextInt();

                if (b.getAvailableSeat() >= seats) {
                    double total = seats * b.getCostPerTicket();

                    b.setAvailableSeat(b.getAvailableSeat() - seats);

                    bookings.add(new Booking(userid, b.getBusName(),
                            b.getBusNum(), source, destination, seats, total));

                    System.out.println("Booking successful ");
                } else {
                    System.out.println("Seats not available ");
                }
            }
        }

 System.out.print("Do you want to cancel  ticket  (yes/no): ");
        sc.nextLine(); 
        String cancel = sc.nextLine();

        if (cancel.equalsIgnoreCase("yes")) {

            if (!bookings.isEmpty()) {
                Booking last = bookings.get(bookings.size() - 1);

                for (Bus b : buses) {
                    if (b.getBusNum().equalsIgnoreCase(last.getBusNumber())) {
                        b.setAvailableSeat(b.getAvailableSeat() + last.getSeat());
                    }
                }

                bookings.remove(bookings.size() - 1);
                System.out.println("Last ticket cancelled ");
            } else {
                System.out.println("No bookings found ");
            }
        }
       for (Booking bk : bookings) {
            bk.viewTicket();
        }

        sc.close();
    }
}