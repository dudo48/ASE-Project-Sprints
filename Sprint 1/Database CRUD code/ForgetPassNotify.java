import java.util.ArrayList;

public class ForgetPassNotify extends Notification {

	public ForgetPassNotify(String ClassType) {
        super(ClassType);
        // TODO Auto-generated constructor stub
    }



    private String Message; // messageTemplate


	
	public String getMessageTemplate() {
        // TODO Auto-generated method stub
        return Message;
    }
	
	public void setMessageTemplate(String Message) {
        this.Message = Message;
    }

}