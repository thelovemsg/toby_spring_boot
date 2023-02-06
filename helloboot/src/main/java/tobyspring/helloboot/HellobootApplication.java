package tobyspring.helloboot;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan // @Component 가 붙은 클래스들을 찾아서 bean으로 등록해달라
public class HellobootApplication {

	@Bean
	public ServletWebServerFactory servletWebServerFactory() {
		return new TomcatServletWebServerFactory();
	}

	@Bean
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}

	//빈 생성을 위한 Factory Method
	/*@Bean
	public HelloController helloController(HelloService helloService) {
		return new HelloController(helloService);
	}

	//빈 생성을 위한 Factory Method
	@Bean
	public HelloService helloService() {
		return new SimpleHelloService();
	}*/

	public static void main(String[] args) throws LifecycleException {
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext(){
			@Override
			protected void onRefresh() {
				super.onRefresh();

				ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
				DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
				//dispatcherServlet.setApplicationContext(this); // spring container 주입

				WebServer webServer = serverFactory.getWebServer(servletContext -> {
					servletContext.addServlet("dispatcherServlet", dispatcherServlet
						).addMapping("/*");
				});
				webServer.start();

			}
		};
		applicationContext.register(HellobootApplication.class);
		applicationContext.refresh();
	}
}


