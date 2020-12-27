package Sprint_2;

import java.util.ArrayList;

public class SmsNotification extends Notification{
	public SmsNotification(NotificationTemplate template, ArrayList<String> messageParts,
			String sendway) {
		super(template, messageParts, sendway);
	}
	public SmsNotification(){}
	public void AddToQueue(Notification l)
	{ 
		NotificationEngine n=new NotificationEngine();
		n.smsNotification.add(l);
	}
}