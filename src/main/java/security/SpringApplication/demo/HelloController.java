package security.SpringApplication.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/user/hello")
    public String user() {
        return "hello, USER!";
    }
    @GetMapping("/admin/hello")
    public String admin() {
        return "hello, admin!";
    }
}
