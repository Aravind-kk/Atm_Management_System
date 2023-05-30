 import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Account {
    private String accountNumber;
    private String pin;
    private double balance;

    public Account(String accountNumber, String pin, double initialBalance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean verifyPin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            System.out.println("Insufficient balance. Withdrawal canceled.");
            return false;
        }
    }
}

class ATM {
    private Map<String, Account> accounts;
    private Account currentAccount;

    public ATM() {
        accounts = new HashMap<>();
        // Adding some sample accounts for testing
        accounts.put("123456", new Account("123456", "1234", 1000.0));
        accounts.put("789012", new Account("789012", "5678", 5000.0));
        accounts.put("Aravind", new Account("Aravind", "2222", 8000.0));
        accounts.put("aravind", new Account("aravind", "1111", 6000.0));
  
    }

    public boolean authenticate(String accountNumber, String pin) {
        Account account = accounts.get(accountNumber);
        if (account != null && account.verifyPin(pin)) {
            currentAccount = account;
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        currentAccount.deposit(amount);
        System.out.println("Amount deposited successfully.");
    }

    public void withdraw(double amount) {
        boolean success = currentAccount.withdraw(amount);
        if (success) {
            System.out.println("Amount withdrawn successfully.");
        }
    }

    public double getBalance() {
        return currentAccount.getBalance();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM();

        String accountNumber;
        String pin;

        System.out.println("***************************************************");
        System.out.println("           Welcome to Aravind ATM Machine           ");
        System.out.println("***************************************************");

        do {
            System.out.print("Enter your account number: ");
            accountNumber = scanner.nextLine();
            System.out.print("Enter your PIN: ");
            pin = scanner.nextLine();

            if (atm.authenticate(accountNumber, pin)) {
                System.out.println("Authentication successful. What would you like to do?");

                int choice;
                double amount;

                do {
                    System.out.println("ATM Menu");
                    System.out.println("1. Deposit");
                    System.out.println("2. Withdraw");
                    System.out.println("3. Check Balance");
                    System.out.println("4. Quit");
                    System.out.print("Enter your choice: ");
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Clear the newline character from the input buffer

                    switch (choice) {
                        case 1:
                            System.out.print("Enter the amount to deposit: ");
                            amount = scanner.nextDouble();
                            atm.deposit(amount);
                            break;
                        case 2:
                            System.out.print("Enter the amount to withdraw: ");
                            amount = scanner.nextDouble();
                            atm.withdraw(amount);
                            break;
                        case 3:
                            double balance = atm.getBalance();
                            System.out.println("Current balance: ₹" + balance);
                            break;
                        case 4:
                            System.out.println("***************************************************");
                            System.out.println("             Thank you for using Aravind ATM        ");
                            System.out.println("***************************************************");
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }

                    System.out.println();

                } while (choice != 4);

                break;

            } else {
                System.out.println("Invalid account number or PIN. Please try again.");
                System.out.println();
            }

        } while (true);

        scanner.close();
    }
}
