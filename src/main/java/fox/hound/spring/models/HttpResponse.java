package fox.hound.spring.models;

public class HttpResponse {
	
	private String message;

	public HttpResponse(String message) {
		super();
		this.setMessage(message);
	}
	public HttpResponse() {
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
