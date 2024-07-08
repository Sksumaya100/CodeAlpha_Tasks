import java.util.ArrayList;
import java.util.Scanner;

public class HotelReservationSystem {
  private static ArrayList<Room> rooms = new ArrayList<>();
  private static ArrayList<Reservation> reservations = new ArrayList<>();
  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    // Add rooms
    rooms.add(new Room(1, "Single", 100, RoomCategory.STANDARD));
    rooms.add(new Room(2, "Double", 200, RoomCategory.DELUXE));
    rooms.add(new Room(3, "Suite", 300, RoomCategory.LUXURY));

    while (true) {
      System.out.println("Hotel Reservation System");
      System.out.println("1. Search Rooms");
      System.out.println("2. Make Reservation");
      System.out.println("3. View Booking Details");
      System.out.println("4. Exit");

      int choice = scanner.nextInt();

      switch (choice) {
        case 1:
          searchRooms();
          break;
        case 2:
          makeReservation();
          break;
        case 3:
          viewBookingDetails();
          break;
        case 4:
          System.exit(0);
          break;
        default:
          System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  private static void searchRooms() {
    System.out.println("Search Rooms");
    System.out.print("Enter check-in date (yyyy-mm-dd): ");
    String checkInDate = scanner.next();
    System.out.print("Enter check-out date (yyyy-mm-dd): ");
    String checkOutDate = scanner.next();

    ArrayList<Room> availableRooms = new ArrayList<>();

    for (Room room : rooms) {
      if (room.isAvailable(checkInDate, checkOutDate)) {
        availableRooms.add(room);
      }
    }

    if (availableRooms.isEmpty()) {
      System.out.println("No rooms available");
    } else {
      System.out.println("Available Rooms:");
      for (Room room : availableRooms) {
        System.out.println(room.toString());
      }
    }
  }

  private static void makeReservation() {
    System.out.println("Make Reservation");
    System.out.print("Enter room number: ");
    int roomNumber = scanner.nextInt();
    System.out.print("Enter guest name: ");
    String guestName = scanner.next();
    System.out.print("Enter check-in date (yyyy-mm-dd): ");
    String checkInDate = scanner.next();
    System.out.print("Enter check-out date (yyyy-mm-dd): ");
    String checkOutDate = scanner.next();

    Room room = findRoomByNumber(roomNumber);

    if (room != null && room.isAvailable(checkInDate, checkOutDate)) {
      Reservation reservation = new Reservation(room, guestName, checkInDate, checkOutDate);
      reservations.add(reservation);
      room.setAvailability(false);
      System.out.println("Reservation made successfully. Your reservation ID is: " + reservation.getReservationID());
    } else {
      System.out.println("Room not available");
    }
  }

  private static void viewBookingDetails() {
    System.out.println("View Booking Details");
    System.out.print("Enter reservation ID: ");
    int reservationID = scanner.nextInt();

    Reservation reservation = findReservationByID(reservationID);

    if (reservation != null) {
      System.out.println(reservation.toString());
    } else {
      System.out.println("Reservation not found");
    }
  }

  private static Room findRoomByNumber(int roomNumber) {
    for (Room room : rooms) {
      if (room.getRoomNumber() == roomNumber) {
        return room;
      }
    }
    return null;
  }

  private static Reservation findReservationByID(int reservationID) {
    for (Reservation reservation : reservations) {
      if (reservation.getReservationID() == reservationID) {
        return reservation;
      }
    }
    return null;
  }
}

enum RoomCategory {
  STANDARD,
  DELUXE,
  LUXURY;
}

class Room {
  private int roomNumber;
  private String roomType;
  private double rate;
  private RoomCategory category;
  private boolean availability;

  public Room(int roomNumber, String roomType, double rate, RoomCategory category) {
    this.roomNumber = roomNumber;
    this.roomType = roomType;
    this.rate = rate;
    this.category = category;
    this.availability = true;
  }

  public int getRoomNumber() {
    return roomNumber;
  }

  public boolean isAvailable(String checkInDate, String checkOutDate) {
    return availability;
  }

  public void setAvailability(boolean availability) {
    this.availability = availability;
  }

  @Override
  public String toString() {
    return "Room " + roomNumber + ": " + roomType + " (" + category + ")";
  }
}

class Reservation {
  private static int idCounter = 1;
  private int reservationID;
  private Room room;
  private String guestName;
  private String checkInDate;
  private String checkOutDate;

  public Reservation(Room room, String guestName, String checkInDate, String checkOutDate) {
    this.reservationID = idCounter++;
    this.room = room;
    this.guestName = guestName;
    this.checkInDate = checkInDate;
    this.checkOutDate = checkOutDate;
  }

  public int getReservationID() {
    return reservationID;
  }

  @Override
  public String toString() {
    return "Reservation ID: " + reservationID + "\nGuest: " + guestName + "\nRoom: " + room + "\nCheck-in: " + checkInDate + "\nCheck-out: " + checkOutDate;
  }
  }
