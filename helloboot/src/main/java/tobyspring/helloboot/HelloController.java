package tobyspring.helloboot;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RequestMapping
//@Component // 나는 Spring Container 에 들어가는 component야! 선언해버림
//@MyComponent
//@Controller
@RestController
public class HelloController { // implements ApplicationContextAware

    private final HelloService helloService;
    private ApplicationContext applicationContext;

    public HelloController(HelloService helloService, ApplicationContext applicationContext) {
        this.helloService = helloService;
        this.applicationContext = applicationContext;

        System.out.println(applicationContext);
    }

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
//    @ResponseBody
    public String hello(String name) {
        if (name == null || name.length() == 0)
            throw new IllegalArgumentException();
        return helloService.sayHello(Objects.requireNonNull(name));
    }

//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        System.out.println(applicationContext);
//    }

}
