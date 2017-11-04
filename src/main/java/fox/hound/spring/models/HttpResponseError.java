package fox.hound.spring.models;

public class HttpResponseError {
	
	private long status = 401;
	private String error = "Unauthorized";
	private String message;
	private Long date = System.currentTimeMillis();

	public HttpResponseError(long status, String error, String message) {
		super();
		this.status = status;
		this.error = error;
		this.message = message;
	}
	public HttpResponseError() {
		this.date = System.currentTimeMillis();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getStatus() {
		return status;
	}
	public void setStatus(long status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Long getDate() {
		return date;
	}
	public void setDate(Long date) {
		this.date = date;
	}
	
}
