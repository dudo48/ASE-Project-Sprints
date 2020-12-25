public class BySMS implements SendingWay {

	private String PhoneNumber;
	public BySMS(String PhoneNumber ) {
	    this.PhoneNumber = PhoneNumber;
	}
	public String getTheWay() {
		return PhoneNumber;
	}

}