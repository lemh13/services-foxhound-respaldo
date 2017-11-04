package fox.hound.spring.services.mails;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Scanner;

@Service
public class MailService {
	
	private JavaMailSender javaMailSender;
	private MimeMessage mimeMessage;
	private MimeMessageHelper helper;
	
	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void sendSimpleMail(String email) throws MailException, MessagingException {
		mimeMessage = javaMailSender.createMimeMessage();
		helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
		
		String htmlMsg = getFile("html/simpleMail.html");

		helper.setText(htmlMsg, true);
		
		helper.setTo(email);
		helper.setFrom("foxhound.ucla@gmail.com");
		helper.setSubject("Hello Fox");
		
		javaMailSender.send(mimeMessage);
	}
	
	/**
	 * Obtenemos un archivo HTML y lo convertimos a String
	 * 
	 *	@param fileName: direccion del archivo
	 *	@return archivo casheado a String 
	 **/
	private String getFile(String fileName) {
		StringBuilder result = new StringBuilder("");

		//Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		try (Scanner scanner = new Scanner(file)) {

			while (scanner.hasNextLine()) {
				result.append( scanner.nextLine() ).append("\n");
			}
			scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result.toString();
	}
	
}
