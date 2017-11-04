package fox.hound.spring.services.notifications;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AndroidPushNotificationsService {
	
	private static final String FIREBASE_SERVER_KEY = "AAAA0fNHvB0:APA91bH0KNux5JWjk3xgmTnJw736w3IviGuxbR-QaJ5KdH2yHN5qAknUSS7gCAvjPJmn1YbEpCg7R-6WJaAsL_Dyre_tCgMK9vyZt058DjMinupN9shP45WZJrpaGUFwixwO407TsRYR";
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
