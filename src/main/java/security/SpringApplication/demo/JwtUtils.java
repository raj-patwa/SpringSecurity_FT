package security.SpringApplication.demo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;



@Component

public class JwtUtils {
    private String jwtSecret="YS1zdHJpbmctc2VjcmV0LWF0LWxlYXN0LTI1Ni1iaXRzLWxvbmc=";
    private int jwtExpirationMs=172800000;

    public String getJwtFromHeader(){
        return "";
    }
    public String generateTokenFromUsername(String userName){
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+ jwtExpirationMs))
                .signWith(key())
                .compact();




    }
    public boolean validateJwtToken(){

        return true;
    }
    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
}
