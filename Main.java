import java.util.Scanner;

/**
 * Represents a bank account adhering to encapsulation principles.
 */
class BankAccount {
    private double balance;

    // Constructor to initialize account with an initial balance
    public BankAccount(double initialBalance) {
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0.0;
            System.out.println("Initial balance cannot be negative. Set to $0.00");
        }
    }

    // Getter for balance (Encapsulation)
    public double getBalance() {
        return balance;
    }

    // Deposit method with amount validation
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: $" + String.format("%.2f", amount));
        } else {
            System.out.println("Invalid deposit amount. Please enter a value greater than 0.");
        }
    }

    // Withdrawal method with balance and amount validation
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount. Please enter a value greater than 0.");
            return false;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds! Transaction failed.");
            return false;
        }
        balance -= amount;
        System.out.println("Successfully withdrew: $" + String.format("%.2f", amount));
        return true;
    }
}

/**
 * Represents the ATM interface for user interaction.
 */
class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    // Start the ATM menu loop
    public void start() {
        int choice;
        do {
            System.out.println("\n==============================");
            System.out.println("       WELCOME TO THE ATM       ");
            System.out.println("==============================");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Cash");
            System.out.println("3. Withdraw Cash");
            System.out.println("4. Exit");
            System.out.print("Please select an option (1-4): ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        checkBalance();
                        break;
                    case 2:
                        handleDeposit();
                        break;
                    case 3:
                        handleWithdrawal();
                        break;
                    case 4:
                        System.out.println("Thank you for using our ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select an option between 1 and 4.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.next(); // Clear invalid input token
                choice = 0;
            }
        } while (choice != 4);
    }

    private void checkBalance() {
        System.out.println("\n------------------------------");
        System.out.println("Current Balance: $" + String.format("%.2f", account.getBalance()));
        System.out.println("------------------------------");
        
        // Pause so the user can read the balance before the menu reloads
        System.out.print("Press Enter to return to the menu...");
        scanner.nextLine(); // Clear leftover newline from nextInt()
        scanner.nextLine(); // Wait for the user to press Enter
    }

    private void handleDeposit() {
        System.out.print("Enter amount to deposit: $");
        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            account.deposit(amount);
            showUpdatedBalance();
        } else {
            System.out.println("Invalid amount format. Please enter numbers only.");
            scanner.next();
        }
    }

    private void handleWithdrawal() {
        System.out.print("Enter amount to withdraw: $");
        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            account.withdraw(amount);
            showUpdatedBalance();
        } else {
            System.out.println("Invalid amount format. Please enter numbers only.");
            scanner.next();
        }
    }

    private void showUpdatedBalance() {
        System.out.println("Updated Balance: $" + String.format("%.2f", account.getBalance()));
        System.out.print("Press Enter to return to the menu...");
        scanner.nextLine(); // Clear leftover newline
        scanner.nextLine(); // Wait for Enter
    }
}

/**
 * Main entry point of the program.
 */
public class Main {
    public static void main(String[] args) {
        // Initialize a bank account with an initial balance of $1,000.00
        BankAccount userAccount = new BankAccount(1000.00);
        
        // Link account to ATM and start the interface
        ATM atmMachine = new ATM(userAccount);
        atmMachine.start();
    }
}