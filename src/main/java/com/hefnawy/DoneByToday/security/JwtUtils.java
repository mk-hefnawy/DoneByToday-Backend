package com.hefnawy.DoneByToday.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtUtils {
    private static final Long TOKEN_VALIDITY = 1000_000_000L;
    private static final String SECRET = "Secret".repeat(20);

    public String generateToken(UserDetails userDetails){

        HashMap<String, Object> claims = new HashMap<>();
        claims.put(Claims.SUBJECT, userDetails.getUsername());
        claims.put("roles", userDetails.getAuthorities());


        return Jwts.builder()
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public String getUserNameFromToken(String token){
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    private Boolean isTokenExpired(String token){
        Date expirationDate = getClaims(token).getExpiration();
        return expirationDate.before(new Date());
    }

    public Boolean isTokenValid(String token, UserDetails userDetails){
        String userName = getUserNameFromToken(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private Claims getClaims(String token) {
        System.out.println(" HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH" + token);
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        }catch (Exception e){
            e.printStackTrace();
            claims = null;
        }

        return claims;
    }

    public void expireToken(String token) {
        getClaims(token).setExpiration(new Date());
    }
}
