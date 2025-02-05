package com.shristi.internship;

import java.util.*;

class Room {
    int roomNumber;
    String type;
    double price;
    boolean isBooked;

    public Room(int roomNumber, String type, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.isBooked = false;
    }
}

class Hotel {
    List<Room> rooms = new ArrayList<>();
    Map<Integer, String> bookings = new HashMap<>();

    public Hotel() {
        rooms.add(new Room(101, "Single", 50.0));
        rooms.add(new Room(102, "Double", 80.0));
        rooms.add(new Room(103, "Suite", 150.0));
        rooms.add(new Room(104, "Single", 50.0));
        rooms.add(new Room(105, "Double", 80.0));
    }

    public void showAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (!room.isBooked) {
                System.out.println("Room " + room.roomNumber + " (" + room.type + ") - $" + room.price);
            }
        }
    }

    public void bookRoom(int roomNumber, String customerName) {
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber && !room.isBooked) {
                room.isBooked = true;
                bookings.put(roomNumber, customerName);
                System.out.println("Room " + roomNumber + " booked successfully for " + customerName + "!");
                return;
            }
        }
        System.out.println("Room not available or does not exist.");
    }

    public void viewBookingDetails() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            System.out.println("Booking Details:");
            for (Map.Entry<Integer, String> entry : bookings.entrySet()) {
                System.out.println("Room " + entry.getKey() + " - Booked by " + entry.getValue());
            }
        }
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();

        while (true) {
            System.out.println("\nHotel Reservation System");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. View Booking Details");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    hotel.showAvailableRooms();
                    break;
                case 2:
                    System.out.print("Enter Room Number: ");
                    int roomNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Your Name: ");
                    String customerName = scanner.nextLine();
                    hotel.bookRoom(roomNumber, customerName);
                    break;
                case 3:
                    hotel.viewBookingDetails();
                    break;
                case 4:
                    System.out.println("Exiting... Thank you for using the Hotel Reservation System!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

