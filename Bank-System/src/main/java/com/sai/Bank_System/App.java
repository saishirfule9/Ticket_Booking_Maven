package com.sai.Bank_System;

import java.util.*;

class Account {
    private int accountNumber;
    private String name;
    private double balance;
    private int pin;

    public Account(int accountNumber, String name, double balance, int pin) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
        this.pin = pin;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public boolean verifyPin(int inputPin) {
        return this.pin == inputPin;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited successfully. New Balance: " + balance);
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New Balance: " + balance);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    @Override
    public String toString() {
        return "Account No: " + accountNumber + ", Name: " + name + ", Balance: " + balance;
    }
}

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static List<Account> accounts = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== Bank Account Management System ===");
            System.out.println("1. Create Account");
            System.out.println("2. View All Accounts");
            System.out.println("3. Deposit Amount");
            System.out.println("4. Withdraw Amount");
            System.out.println("5. Check Balance");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> viewAccounts();
                case 3 -> depositMoney();
                case 4 -> withdrawMoney();
                case 5 -> checkBalance();
                case 6 -> System.out.println("Thank you for using our bank system!");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 6);
    }

    private static void createAccount() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();
        System.out.print("Set a 4-digit PIN: ");
        int pin = sc.nextInt();

        accounts.add(new Account(accNo, name, balance, pin));
        System.out.println("Account created successfully!");
    }

    private static void viewAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }
        System.out.println("\n--- All Accounts ---");
        for (Account acc : accounts) {
            System.out.println(acc);
        }
    }

    private static void depositMoney() {
        Account acc = authenticateAccount();
        if (acc != null) {
            System.out.print("Enter amount to deposit: ");
            double amount = sc.nextDouble();
            acc.deposit(amount);
        }
    }

    private static void withdrawMoney() {
        Account acc = authenticateAccount();
        if (acc != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = sc.nextDouble();
            acc.withdraw(amount);
        }
    }

    private static void checkBalance() {
        Account acc = authenticateAccount();
        if (acc != null) {
            System.out.println("Current Balance: " + acc.getBalance());
        }
    }

    private static Account authenticateAccount() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();
        Account acc = findAccount(accNo);

        if (acc == null) {
            System.out.println("Account not found.");
            return null;
        }

        System.out.print("Enter 4-digit PIN: ");
        int pin = sc.nextInt();

        if (acc.verifyPin(pin)) {
            return acc;
        } else {
            System.out.println("Incorrect PIN.");
            return null;
        }
    }

    private static Account findAccount(int accNo) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accNo) {
                return acc;
            }
        }
        return null;
    }
}
