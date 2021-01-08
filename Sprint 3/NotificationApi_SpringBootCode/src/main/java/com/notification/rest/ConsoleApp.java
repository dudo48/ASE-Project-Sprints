import com.notification.rest.model.Notification;
import com.notification.rest.repository.INotificationDataAccessLayer;
import com.notification.rest.repository.MysqlNotificationDataAccessLayer;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleApp {

    private INotificationDataAccessLayer database;

    public ConsoleApp(INotificationDataAccessLayer database){
        this.database = database;
    }

    // get input for a choice in specified range
    private int makeChoice(int low, int high) {
        Scanner input = new Scanner(System.in);
        int choice;

        System.out.print("Enter your choice number: ");
        while (true) {

            choice = input.nextInt();
            if ((choice >= low) && (choice <= high))
                break;
            System.out.print("Please enter a valid choice number: ");

        }
        return choice;
    }

    public void run() {

        while (true) {

            System.out.println("Choose your operation:\n" +
                    "(1) Read notifications\n" +
                    "(2) Exit");

            int choice = makeChoice(1, 2);

            if (choice == 1) {
                Scanner input = new Scanner(System.in);

                System.out.print("Enter the Phone Number/Email: ");
                String receiver = input.next();

                ArrayList<Notification> notifications = database.readNotification(receiver);
                ArrayList<Notification> successful_notifications = new ArrayList<>(); // notifications that were received successfully

                for(Notification notification : notifications){

                    // if notification was sent successfully
                    if(notification.isStatus()){
                        successful_notifications.add(notification);
                    }
                }

                if(successful_notifications.isEmpty()){
                    System.out.println("Sorry, there are no notifications.");
                }
                else{
                    for(Notification notification : successful_notifications){
                        System.out.println(notification);
                    }
                    System.out.println("Displayed " + successful_notifications.size() + " notifications.");
                }

                System.out.print("Press Enter to continue..");
                input.nextLine();
            }
            if (choice == 2) {
                break;
            }
        }
    }
    public static void main(String[] args) {
    	INotificationDataAccessLayer database = new MysqlNotificationDataAccessLayer();
    	ConsoleApp app = new ConsoleApp(database);
    	app.run();
    }
}