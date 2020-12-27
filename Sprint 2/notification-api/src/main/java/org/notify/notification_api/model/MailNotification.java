package Sprint_2;

import java.util.ArrayList;

public class MailNotification extends Notification{
  public MailNotification(NotificationTemplate template, ArrayList<String> messageParts,
			String sendway) {
		super(template, messageParts,sendway);
	}
  public MailNotification(){}
  public void AddToQueue(Notification l)
    { 
	  NotificationEngine n=new NotificationEngine();
	  n.mailNotification.add(l);
   }
}
