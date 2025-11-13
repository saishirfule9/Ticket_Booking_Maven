package com.sai.Library_Management;

import java.util.*;

class Book {
    private int id;
    private String title;
    private String author;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book ID: " + id + ", Title: " + title + ", Author: " + author;
    }
}

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static List<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Remove Book");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1 -> addBook();
                case 2 -> viewBooks();
                case 3 -> searchBook();
                case 4 -> removeBook();
                case 5 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 5);
    }

    private static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author Name: ");
        String author = sc.nextLine();

        books.add(new Book(id, title, author));
        System.out.println("Book added successfully!");
    }

    private static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        System.out.println("\n--- Book List ---");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private static void searchBook() {
        System.out.print("Enter Book ID or Title to search: ");
        String input = sc.nextLine();

        for (Book book : books) {
            if (String.valueOf(book.getId()).equalsIgnoreCase(input) || book.getTitle().equalsIgnoreCase(input)) {
                System.out.println("Book found: " + book);
                return;
            }
        }
        System.out.println("Book not found.");
    }

    private static void removeBook() {
        System.out.print("Enter Book ID to remove: ");
        int id = sc.nextInt();
        sc.nextLine();

        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                System.out.println("Book removed successfully!");
                return;
            }
        }
        System.out.println("Book not found.");
    }
}
