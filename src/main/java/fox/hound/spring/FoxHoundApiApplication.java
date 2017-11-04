package fox.hound.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class FoxHoundApiApplication {
	
	@RequestMapping("/")
    @ResponseBody
    String home() {
      return "Fox Hound Rules!";
    }

	public static void main(String[] args) {
		SpringApplication.run(FoxHoundApiApplication.class, args);
	}
}
