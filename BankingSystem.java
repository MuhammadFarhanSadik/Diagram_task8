

package farhans.bankingsystem;



import java.util.Scanner;

public class BankingSystem {

    // Customer class
    static class Customer {
        private String name;
        private String phone;

        public Customer(String name, String phone) {
            this.name = name;
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }
    }

    // Account class
    static class Account {
        private int accountNumber;
        private Customer customer;
        private double balance;

        public Account(int accountNumber, Customer customer, double balance) {
            this.accountNumber = accountNumber;
            this.customer = customer;
            this.balance = balance;
        }

        public int getAccountNumber() {
            return accountNumber;
        }

        public Customer getCustomer() {
            return customer;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        }

        public boolean withdraw(double amount) {
            if (amount <= balance) {
                balance -= amount;
                System.out.println("Withdrawn: " + amount);
                return true;
            } else {
                System.out.println("Insufficient balance.");
                return false;
            }
        }
    }

    // Bank class
    static class Bank {
        private String name;
        private Account[] accounts;
        private int totalAccounts = 0;

        public Bank(String name, int size) {
            this.name = name;
            accounts = new Account[size];
        }

        public void addAccount(Account acc) {
            if (totalAccounts < accounts.length) {
                accounts[totalAccounts++] = acc;
            }
        }

        public Account getAccountByNumber(int accNumber) {
            for (int i = 0; i < totalAccounts; i++) {
                if (accounts[i].getAccountNumber() == accNumber) {
                    return accounts[i];
                }
            }
            return null;
        }

        public String getName() {
            return name;
        }
    }

    // ATM class
    static class ATM {
        private Bank bank;

        public ATM(Bank bank) {
            this.bank = bank;
        }

        public void performTransaction(int accNumber, String type, double amount) {
            Account acc = bank.getAccountByNumber(accNumber);

            if (acc != null) {
                System.out.println("Customer: " + acc.getCustomer().getName());
                if (type.equalsIgnoreCase("deposit")) {
                    acc.deposit(amount);
                } else if (type.equalsIgnoreCase("withdraw")) {
                    acc.withdraw(amount);
                } else {
                    System.out.println("Invalid transaction type.");
                }
                System.out.println("Current Balance: " + acc.getBalance());
            } else {
                System.out.println("Account not found.");
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        Bank bank = new Bank("SmartBank", 5);

        // Pre-define 5 accounts
        bank.addAccount(new Account(1001, new Customer("Farhan", "01711111111"), 5000));
        bank.addAccount(new Account(1002, new Customer("Nayem", "01722222222"), 4000));
        bank.addAccount(new Account(1003, new Customer("Murad", "01733333333"), 3000));
        bank.addAccount(new Account(1004, new Customer("Shahed", "01744444444"), 2000));
        bank.addAccount(new Account(1005, new Customer("Nahian", "01755555555"), 1000));

        ATM atm = new ATM(bank);
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to " + bank.getName());

        System.out.print("Enter your account number: ");
        int accNum = input.nextInt();

        System.out.print("Enter transaction type (deposit/withdraw): ");
        String type = input.next();

        System.out.print("Enter amount: ");
        double amount = input.nextDouble();

        atm.performTransaction(accNum, type, amount);

        input.close();
    }
}

