package security.SpringApplication.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.AuthenticationException;
@RestController
public class HelloController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;



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
    @PostMapping("/signin")
    public String login(@RequestBody LoginRequest loginRequest){
        Authentication authentication;
        try{
            authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                  loginRequest.getUsername(),
                    loginRequest.getPassword()
            )
            );

        }catch (AuthenticationException e){
            e.printStackTrace();
            return "could not authenticate";
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails=(UserDetails) authentication.getPrincipal();
        String jwtToken= jwtUtils.generateTokenFromUsername(userDetails.getUsername());
        return jwtToken;
    }
}
