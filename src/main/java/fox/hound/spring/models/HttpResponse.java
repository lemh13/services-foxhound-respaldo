package fox.hound.spring.models;

public class HttpResponse {
	
	private String message;
	private Object object = ".";

	public HttpResponse(String message) {
		super();
		this.setMessage(message);
	}
	public HttpResponse(String message, Object object) {
		super();
		this.setMessage(message);
		this.setObject(object);
	}
	public HttpResponse() {
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	
}
