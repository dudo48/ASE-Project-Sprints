import java.util.ArrayList;

public class Notification {

	protected String ClassType;
	protected SendingWay sendingway;
	protected String MessageText;
	protected Integer ID;
	protected boolean IsSuccess;
	protected String FailureReason;
	protected String Subject;
	protected String Language;
	
	public Notification(String[] MsgPrts, String reason, int id, boolean isSuccess) {
	    // TODO--- converting msgParts to the messageText.
	    this.FailureReason = reason;
	    this.ID = id;
	    this.IsSuccess = isSuccess;
	}
	
	public Notification(String classType) {
        this.ClassType = classType;
    }

    /**
	 * 
	 * @param ClassType
	 * @param obj
	 * @param MsgPrts
	 */
	

	public String getSendingWay() {
		// TODO - implement Notification.getSendingWay
		throw new UnsupportedOperationException();
	}

	public String getMessageText() {
		// TODO - implement Notification.getMessageText
		throw new UnsupportedOperationException();
	}

    public String getClassType() {
        // TODO Auto-generated method stub
        return ClassType;
    }

    public String getMsgText() {
        // TODO Auto-generated method stub
        return MessageText;
    }

    public int getId() {
        // TODO Auto-generated method stub
        return ID;
    }

    public String getFailureReason() {
        // TODO Auto-generated method stub
        return FailureReason;
    }

    public boolean isSuccess() {
        // TODO Auto-generated method stub
        return IsSuccess;
    }

    public String getEmail() {
        // TODO Auto-generated method stub
        if(ClassType.equals("sms")) 
            return null;
        
        return sendingway.getTheWay();
    }

    public String getPhoneNum() {
        if(ClassType.equals("mail"))
            return null;
        
        return sendingway.getTheWay();
    }

    public String getLanguage() {
        // TODO Auto-generated method stub
        return Language;
    }

    public String getSubject() {
        // TODO Auto-generated method stub
        return Subject;
    }

    public void setSendingWay(SendingWay sendingWay) {
        // TODO Auto-generated method stub
        this.sendingway = sendingWay;
    }

}