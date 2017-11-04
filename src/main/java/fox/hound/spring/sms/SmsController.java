package fox.hound.spring.sms;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fox.hound.spring.models.Sms;
import fox.hound.spring.services.sms.AndroidSmsService;

@RestController
@RequestMapping("sms")
public class SmsController {
	
	private final Logger logger = Logger.getLogger(this.getClass());


	private final String TOPIC = "foxhoundsms";
	
	@Autowired
	private AndroidSmsService androidSmsService;
	
	@RequestMapping(value = "/send", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> send(@RequestBody Sms sms) throws JSONException {
		
		logger.info(sms.getBody() + " " + sms.getNumbers());
 
		JSONObject body = new JSONObject();
		body.put("to", "/topics/" + TOPIC);
		body.put("priority", "high");
 
		JSONObject notification = new JSONObject();
		notification.put("title", "Send to: " + sms.getNumbers());
		notification.put("body", sms.getBody());
		
		JSONObject data = new JSONObject();
		data.put("numero", sms.getNumbers());
		data.put("body", sms.getBody());
 
		body.put("notification", notification);
		body.put("data", data);
 
/**
		{
		   "notification": {
		      "body": "body"
		   },
		   "data": {
		      "numero": "+584120523025",
		   },
		   "to": "/topics/foxhoundsms",
		   "priority": "high"
		}
*/
 
		HttpEntity<String> request = new HttpEntity<>(body.toString());
 
		CompletableFuture<String> pushNotification = androidSmsService.send(request);
		CompletableFuture.allOf(pushNotification).join();
 
		try {
			String firebaseResponse = pushNotification.get();
			
			return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
 
		return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
	}
	
	/** MULTIPLE NUMBERS
	{
	   "notification": {
	      "body": "body"
	   },
	   "data": {
	      "numero": "+584120523025;+584127832291;..",
	   },
	   "to": "/topics/foxhoundsms",
	   "priority": "high"
	}
*/
	
}
