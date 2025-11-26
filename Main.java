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
            userPin = sc.nextInt();
            if(userPin == PIN){
                authenticated = true;
                break;
            }
            else{
                attempts--;
                if(attempts > 0){
                    System.out.println("Incorrect pin. "+attempts+" attempts left");
                }
            }
        }

        if(!authenticated){
            System.out.println("Too many attempts. Card is blocked !!");
            sc.close();
            return;
        }

        boolean willing = true;

        while (willing) {
            System.out.println("-----Banking System-----");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Balance Enquiry");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the amount to deposit: ");
                    long depAmount = sc.nextLong();
                    if (depAmount > 0) {
                        deposit(depAmount);
                    } else {
                        invalid();
                    }
                    break;

                case 2:
                    System.out.print("Enter the amount to withdraw: ");
                    long withDraw = sc.nextLong();
                    if (withDraw > 0) {
                        withdraw(withDraw);
                    } else {
                        invalid();
                    }
                    break;

                case 3:
                    showBalance();
                    break;

                case 4:
                    goodbye();
                    willing = false;
                    break;

                default:
                    invalid();
                    break;
            }

            if (!willing) {
                break;
            }

            System.out.print("Do you want to process again ?(y/n): ");
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
        System.out.println("Amount deposited successfully");
    }

    public static void withdraw(long amount) {
        if(amount<=50000) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Amount withdrawn successfully");
            } else {
                System.out.println("Insufficient Balance !!");
            }
        }
        else{
            System.out.println("The maximum withdrawal amount is $50000");
        }
    }

    public static void showBalance() {
        System.out.println("Your current balance is $" + balance);
    }

    public static void goodbye(){
        System.out.println("Thank you !!");
    }

    public static void invalid(){
        System.out.println("Invalid amount.\nOperation cancelled!!");
    }
}
