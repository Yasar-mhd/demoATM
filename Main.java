import java.util.Scanner;

public class Main {
    static long balance = 0;
    static final int PIN = 2402;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int attempts = 3;
        boolean authenticated = false;
        int userPin;

        while(attempts>0){
            System.out.print("Enter your pin : "); 
            userPin = sc.nextInt();     // Pin authentication
           
            if(userPin == PIN){
                authenticated = true;
                break;
            }
            else{
                attempts--;
                if(attempts > 0){
                    System.out.println("Incorrect pin. "+attempts+" attempts left");            // Incorrect pin message
                }
            }
        }

        if(!authenticated){
            System.out.println("Too many attempts. Card is blocked !!");        // Block card message
            sc.close();
            return;
        }

        boolean willing = true;

        while (willing) {
            System.out.println("-----Banking System-----");         //  Menu display
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Balance Enquiry");
            System.out.println("4. Exit");
            
            System.out.print("Enter your choice: ");         // User choice input
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the amount to deposit: ");        // Deposit amount input
                    long depAmount = sc.nextLong();
                    if (depAmount > 0) {
                        deposit(depAmount);
                    } else {
                        invalid();
                    }
                    break;

                case 2:
                    System.out.print("Enter the amount to withdraw: ");      // Withdraw amount input
                    long withDraw = sc.nextLong();
                    if (withDraw > 0) {
                        withdraw(withDraw);
                    } else {
                        invalid();
                    }
                    break;

                case 3:
                    showBalance();      // Show balance
                    break;

                case 4:
                    goodbye();       // Exit message
                    willing = false;
                    break;

                default:
                    invalid();       // Invalid choice message
                    break;
            }

            if (!willing) {
                break;
            }

            System.out.print("Do you want to process again ?(y/n): ");       // Continue prompt
            char ans = sc.next().charAt(0);
            if (ans == 'n' || ans == 'N') {
                goodbye();
                willing = false;
            }
        }
        sc.close();
    }

    public static void deposit(long amount) {
        balance += amount;
        System.out.println("Amount deposited successfully");        // Deposit success message
    }

    public static void withdraw(long amount) {
        if(amount<=50000) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Amount withdrawn successfully");        // Withdraw success message
            } else {
                System.out.println("Insufficient Balance !!");      // Insufficient balance message
            }
        }
        else{
            System.out.println("The maximum withdrawal amount is $50000");        // Exceed max withdrawal message
        }
    }

    public static void showBalance() {
        System.out.println("Your current balance is $" + balance);        // Display balance
    }

    public static void goodbye(){
        System.out.println("Thank you !!");        //Exit message
    }

    public static void invalid(){
        System.out.println("Invalid amount.\nOperation cancelled!!");        // Invalid amount message
    }
}
