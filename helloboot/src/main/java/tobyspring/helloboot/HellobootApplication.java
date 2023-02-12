package tobyspring.helloboot;

import org.springframework.boot.SpringApplication;
import tobyspring.config.MySpringBootApplication;

//@Configuration
//@ComponentScan // @Component 가 붙은 클래스들을 찾아서 bean으로 등록해달라
@MySpringBootApplication
public class HellobootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HellobootApplication.class, args);
	}


}


