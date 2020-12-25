public class ConfirmBooking extends Notification {

	private String Message;

	public ConfirmBooking(String classType) {
		super(classType);
	}

    public String getMessageTemplate() {
        // TODO Auto-generated method stub
        return Message;
    }
    
    public void setMessageTemplate(String Message) {
        this.Message = Message;
    }

}