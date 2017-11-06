//package fox.hound.spring.mails;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import fox.hound.spring.models.HttpResponse;
//import fox.hound.spring.services.mails.MailService;
//
//@RestController
//public class MailController {
//	
//	private Logger logger = LoggerFactory.getLogger(MailController.class);
//	
//	@Autowired
//	private MailService mailService;
//	
//	// Validate email format
//	@RequestMapping(value="/sendMail", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public ResponseEntity<?> sendSimpleMail(@RequestBody HttpResponse email) {
//		HttpResponse response = new HttpResponse("Send success");
//		logger.info("email:" + email.getMessage());
//		try {
//			mailService.sendSimpleMail( email.getMessage() );
//		} catch (Exception e) {
//			response.setMessage("Oops!");
//			logger.info("Error sending mail: " + e.getMessage());
//		}
//		return ResponseEntity.ok(response);
//	}
//
//}
