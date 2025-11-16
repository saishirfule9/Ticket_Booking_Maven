package com.ticket.Ticket_Booking;

import java.util.*;

class Movie {
    private int id;
    private String title;
    private int totalSeats;
    private int availableSeats;

    public Movie(int id, String title, int totalSeats) {
        this.id = id;
        this.title = title;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public boolean bookSeats(int count) {
        if (count <= availableSeats && count > 0) {
            availableSeats -= count;
            return true;
        }
        return false;
    }

    public void cancelSeats(int count) {
        if (availableSeats + count <= totalSeats) {
            availableSeats += count;
        }
    }

    @Override
    public String toString() {
        return id + ". " + title + " | Available Seats: " + availableSeats;
    }
}

class Booking {
    private int bookingId;
    private String customerName;
    private Movie movie;
    private int seatCount;

    public Booking(int bookingId, String customerName, Movie movie, int seatCount) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.movie = movie;
        this.seatCount = seatCount;
    }

    public int getBookingId() {
        return bookingId;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getSeatCount() {
        return seatCount;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId + ", Customer: " + customerName +
               ", Movie: " + movie.getTitle() + ", Seats: " + seatCount;
    }
}

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static List<Movie> movies = new ArrayList<>();
    private static List<Booking> bookings = new ArrayList<>();
    private static int bookingCounter = 1;

    public static void main(String[] args) {
        setupMovies();
        int choice;
        do {
            System.out.println("\n=== Online Ticket Booking System ===");
            System.out.println("1. View Movies");
            System.out.println("2. Book Ticket");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View All Bookings");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> viewMovies();
                case 2 -> bookTicket();
                case 3 -> cancelBooking();
                case 4 -> viewBookings();
                case 5 -> System.out.println("Thank you for using our ticket booking system!");
                default -> System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 5);
    }

    private static void setupMovies() {
        movies.add(new Movie(1, "Avengers: Endgame", 50));
        movies.add(new Movie(2, "Inception", 40));
        movies.add(new Movie(3, "Interstellar", 30));
    }

    private static void viewMovies() {
        System.out.println("\n--- Available Movies ---");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    private static void bookTicket() {
        System.out.println("\nEnter your name: ");
        String name = sc.nextLine();

        viewMovies();
        System.out.print("Select movie ID: ");
        int movieId = sc.nextInt();
        System.out.print("Enter number of seats to book: ");
        int seats = sc.nextInt();

        Movie selected = findMovie(movieId);
        if (selected != null) {
            if (selected.bookSeats(seats)) {
                Booking booking = new Booking(bookingCounter++, name, selected, seats);
                bookings.add(booking);
                System.out.println("Booking successful! Your Booking ID: " + booking.getBookingId());
            } else {
                System.out.println("Not enough seats available.");
            }
        } else {
            System.out.println("Invalid movie ID.");
        }
    }

    private static void cancelBooking() {
        System.out.print("\nEnter your Booking ID to cancel: ");
        int id = sc.nextInt();

        Iterator<Booking> iterator = bookings.iterator();
        while (iterator.hasNext()) {
            Booking booking = iterator.next();
            if (booking.getBookingId() == id) {
                booking.getMovie().cancelSeats(booking.getSeatCount());
                iterator.remove();
                System.out.println("Booking cancelled successfully.");
                return;
            }
        }
        System.out.println("Booking ID not found.");
    }

    private static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }
        System.out.println("\n--- All Bookings ---");
        for (Booking b : bookings) {
            System.out.println(b);
        }
    }

    private static Movie findMovie(int id) {
        for (Movie m : movies) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }
}
