import com.notification.rest.repository.INotificationDataAccessLayer;
import com.notification.rest.repository.MysqlNotificationDataAccessLayer;

public class Main {
    public static void main(String[] args) {
        INotificationDataAccessLayer database = new MysqlNotificationDataAccessLayer();
        ConsoleApp app = new ConsoleApp(database);
        app.run();
    }
}