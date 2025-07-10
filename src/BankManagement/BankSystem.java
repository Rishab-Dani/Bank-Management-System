package BankManagement;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

class NegativeAmountException extends Exception {
    public NegativeAmountException(String message) {
        super(message);
    }
}

class Accounts {

    private String accountHolder;
    private double balance;

    Accounts(String newName, double initialDeposit) {
        this.accountHolder = newName;
        this.balance = initialDeposit;
    }

    void deposit(double amount) {
        try {
            if (amount < 0) {
                throw new NegativeAmountException(" Amount cannot be negative !");
            } else balance = balance + amount;
            System.out.println(" ✅ Amount Deposited Successfully !");
            System.out.println("Deposited ₹" + amount + ". New balance: ₹" + balance);

        } catch (NegativeAmountException e) {
            System.out.println(" ❌ Error:" + e.getMessage());
        }
    }


    void withdraw(double withdrawAmount) {

        try {
            if (withdrawAmount < 0) {
                throw new NegativeAmountException(" Amount cannot be negative !");
            } else if (withdrawAmount <= balance) {
                balance = balance - withdrawAmount;
                System.out.println(" ✅ Withdraw ₹ " + withdrawAmount + ". New balance: ₹ " + balance);
            } else {
                System.out.println(" ❌ Insufficient amount!");
            }
        } catch (NegativeAmountException e) {
            System.out.println(" ❌ Error:" + e.getMessage());
        }
    }

    void showBalance() {

        System.out.println(" ✅ Name: " + accountHolder);
        //System.out.println("Account No: " + accountNumber);
        System.out.println(" ✅ Balance: ₹" + balance);
    }


}

public class BankSystem {
    static HashMap<String, Accounts> accounts = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String args[]) {

        int choice = 0;
        boolean b = true;
        while (true) {
            System.out.println("=====================================================================");
            // System.out.println();
            System.out.println("Banking Management System");
            // System.out.println();
            System.out.println("=====================================================================");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Show Balance");
            System.out.println("5. Exit");
            System.out.print("Choice: ");

            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(" 🙏 Please Enter Valid Operations !");
            }

            sc.nextLine();


            switch (choice) {

                case 1:
                    createAccount();
                    break;

                case 2:
                    deposit();
                    break;

                case 3:
                    withdraw();
                    break;

                case 4:
                    showBalance();
                    break;

                case 5:
                    System.out.println("Exiting..........");
                    System.out.println(" 🙏 Thanking you for using our services !");
                    return;

                default:
                    System.out.println(" ❌ Invalid Option");

            }

        }
    }

    static void createAccount() {
        System.out.print("Create a new AccountHolder Name: ");
        String name = sc.nextLine();
        System.out.print("Enter your Initial Deposit: ");
        try {
            double initialDeposit = sc.nextInt();
            if (initialDeposit < 0) {
                throw new NegativeAmountException(" Amount cannot be negative !");
            } else
                accounts.put(name, new Accounts(name, initialDeposit));
            System.out.println("✅ Account created Successfully ! ");
        } catch (InputMismatchException e) {
            System.out.println(" ❌ Invalid Operation");
            System.out.println("Please Enter in Digits!");
        } catch (NegativeAmountException e) {
            System.out.println(" ❌ Error:" + e.getMessage());
        }
    }

    static void deposit() {
        System.out.print("Enter AccountHolder Name: ");
        String name = sc.nextLine();
        System.out.print("Enter your Deposit Amount: ");
        Accounts acc = accounts.get(name);
        if (acc == null) {
            System.out.println(" ❌ Account not found!");
        } else {
            try {
                double amount = sc.nextDouble();
                acc.deposit(amount);
            } catch (InputMismatchException e) {
                System.out.println(" ❌ Invalid Operation!");
                System.out.println("Please Enter the valid Amount!");
            }
        }
    }

    static void withdraw() {
        System.out.print("Enter AccountHolder Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Your Withdraw Amount: ");
        Accounts acc = accounts.get(name);
        if (acc == null) {
            System.out.println(" ❌ Account not found!");
        } else
            try {
                double withdrawAmount = sc.nextDouble();
                if (withdrawAmount == 0.0) {
                    System.out.println(" ❌ Invalid Operations");
                }
                acc.withdraw(withdrawAmount);
            } catch (InputMismatchException e) {
                System.out.println(" ❌ Invalid Operation!");
                System.out.println("Please Enter the valid Amount!");
            }
    }


    static void showBalance() {
        System.out.print("Enter AccountHolder Name: ");
        String name = sc.nextLine();
        Accounts acc = accounts.get(name);
        if (acc != null) {
            acc.showBalance();
        } else {
            System.out.println(" ❌ Account not found!");
        }
    }


}

