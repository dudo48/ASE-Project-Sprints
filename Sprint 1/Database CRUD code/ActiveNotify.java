public class ActiveNotify extends Notification {

	private String Message;

	public ActiveNotify(String classType) {
		super(classType); // notifying code.
	}

	public String getMessageTemplate() {
        // TODO Auto-generated method stub
        return Message;
    }
	public void setMessageTemplate(String Message) {
        this.Message = Message;
    }
}