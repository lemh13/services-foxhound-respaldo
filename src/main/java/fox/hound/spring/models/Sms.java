package fox.hound.spring.models;

public class Sms {
	
	private String numbers;
	private String body;
	public Sms(String numbers, String body) {
		super();
		this.numbers = numbers;
		this.body = body;
	}
	public Sms() {
		
	}
	public String getNumbers() {
		return numbers;
	}
	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}

}
