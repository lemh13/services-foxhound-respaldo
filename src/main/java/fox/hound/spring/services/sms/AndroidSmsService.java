package fox.hound.spring.services.sms;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fox.hound.spring.services.notifications.HeaderRequestInterceptor;

@Service
public class AndroidSmsService {
	
	private static final String FIREBASE_SERVER_KEY = "AAAATWmilHQ:APA91bGdmE9cXrC4UbED8qOlsHp5uSyopuF0IUbHSjZjKwI_vSjqObm8WtjqHWj2m8cppObuGzaAHDNy1FnwtBgG7V5J9ajOYGCVbwFAVwGy6HbBqcvVGIqmieO8_jgJVXCQGH9xb-01";
	private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";
	
	@Async
	public CompletableFuture<String> send(HttpEntity<String> entity) {

		RestTemplate restTemplate = new RestTemplate();

		/**
		https://fcm.googleapis.com/fcm/send
		Content-Type:application/json
		Authorization:key=FIREBASE_SERVER_KEY*/

		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
		interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
		restTemplate.setInterceptors(interceptors);

		String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);

		return CompletableFuture.completedFuture(firebaseResponse);
	}

}
