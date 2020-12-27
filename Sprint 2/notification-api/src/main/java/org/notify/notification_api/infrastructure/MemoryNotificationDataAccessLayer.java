package Sprint_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class MemoryNotificationDataAccessLayer implements INotificationDataAccessLayer{

	ArrayList<Notification>AllNotifications;
	static int id=0;
	MemoryNotificationDataAccessLayer()
	{
		AllNotifications=new ArrayList<>();
	}
	public void AddNotification(Notification n) {
		AllNotifications.add(n);
	}
	public void UpdateNotification(Notification n, int id) {
		if(id<=AllNotifications.size() && id>0)
		    AllNotifications.set(id,n);
		else
			System.out.println("the id isn't exist");
	}
	public void DeleteNotification(int id) {
		if(id<=AllNotifications.size() && id>0)
			AllNotifications.remove(id);
		else
			System.out.println("the id isn't exist");
	}
	public Notification GetNotification(int id) {
		if(id<=AllNotifications.size() && id>0)
			return AllNotifications.get(id);
		else
			return null;
	}
	public ArrayList<Notification> GetAllNotification() {
		if(AllNotifications.size()>0)
			return AllNotifications;
		else
			return null;
	}
	public void StoreQueues(ArrayList<Notification> queue, String filename){
		File file=new File(filename);
    	try {
    		if (!file.exists()) {
                file.createNewFile();
            }
			 FileWriter f1=new FileWriter(file);
			 for(int i=0;i<queue.size();i++)
			 {
				 f1.write(queue.get(i).ID+"  "+queue.get(i).sendway+queue.get(i).template+"\n");
			 }
			 f1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	public ArrayList<Notification> readQueues(Notification obj,String filename) {
		int maxid=0;
		File f=new File(filename);
		if(f.exists())
		{
		ArrayList<Notification>queue=new ArrayList<>();
		try {
			Scanner scan = new Scanner(f);
			while(scan.hasNext()) {
				int id = scan.nextInt();
				String sendway=scan.next();
				NotificationTemplate template=new NotificationTemplate();
				template.id=scan.nextInt();
				template.Content=scan.next();
				template.language=LanguageEnum.valueOf(scan.next());
				template.Subject = scan.next();
				obj=new Notification();
				obj.setID(id);
				obj.setSendway(sendway);
				obj.setTemplate(template);
				queue.add(obj);
				maxid=Math.max(id, maxid);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		checkmaxid(maxid);
		return queue;
		}
		else
		{
			checkmaxid(maxid);
			return null;		
		}
	}
	public void checkmaxid(int maxid)
	{
		if(maxid>id)
			id=maxid;	
	}
    public int getid()
    {
	return id;
    }
}
