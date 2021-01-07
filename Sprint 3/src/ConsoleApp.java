import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleApp {

    // get input for a choice in specified range
    public static int makeChoice(int low, int high) {
        Scanner input = new Scanner(System.in);
        int choice = -1;

        System.out.println("Enter your choice number: ");
        while (true) {

            choice = input.nextInt();
            if ((choice >= low) && (choice <= high))
                break;
            System.out.println("Please enter a valid choice number: ");

        }
        return choice;
    }

    public static void run(){

        System.out.println("Choose your operation:\n" +
                "(1) Add a notification\n" +
                "(2) Read a notification");

        int choice = makeChoice(1, 2);

        if(choice == 1){
            // add
        }
        else if(choice == 2){
            Scanner input = new Scanner(System.in);

            String receiver = input.nextLine();
            ArrayList<Notification> result = MysqlNotificationDataAccessLayer.readNotification(receiver);

            for(Notification notification : result){
                System.out.println(notification);
            }
        }
    }

    public static void main(String[] args) {
        run();
    }

}
