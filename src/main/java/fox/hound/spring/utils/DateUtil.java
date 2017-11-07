package fox.hound.spring.utils;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {
	
	public static Date getCurrentDate() {
		return new Date();
	}

}
