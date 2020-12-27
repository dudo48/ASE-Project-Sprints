package Sprint_2;

import java.util.ArrayList;
import java.util.Queue;

public interface INotificationDataAccessLayer {
	void AddNotification(Notification n);
	void UpdateNotification(Notification n,int id);
	void DeleteNotification(int id);
	Notification GetNotification(int id);
	ArrayList<Notification> GetAllNotification();
	void StoreQueues(ArrayList<Notification>queue,String filename);
	ArrayList<Notification>readQueues(Notification obj,String filename);
}
