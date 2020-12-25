public class ByMail implements SendingWay {

	private String Email;
	public ByMail(String Email) {
        this.Email = Email;
    }
	public String getTheWay() {
		return Email;
	}
	
}