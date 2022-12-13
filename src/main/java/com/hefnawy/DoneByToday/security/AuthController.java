package com.hefnawy.DoneByToday.security;

import com.hefnawy.DoneByToday.user_feature.data_objects.User;
import com.hefnawy.DoneByToday.user_feature.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = {"", "/login"})
    // We will use the AuthenticationManagerBuilder way of authentication that we have told Spring abt it in the Configuration.
    public JWTResponse login(@RequestParam(value = "userName") String userName,
                             @RequestParam(value = "password") String password) {
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        }catch (DisabledException e){
                return new JWTResponse(null, null, null, "USER_DISABLED");
        }
        catch (BadCredentialsException e){
            return new JWTResponse(null, null, null, "BAD_CREDENTIALS");
        }

        if (authentication.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            User user = userService.getUserByUserName(userName);
            String token = jwtUtils.generateToken(userDetails);

            return new JWTResponse(token, userName, user.getRoles(), "SUCCESS");
        }

        return new JWTResponse(null, null, null, "INVALID_CREDENTIALS");
    }

    @PostMapping("/signup")
    public User signup(@RequestBody User user){
        return userService.addUser(user);
    }

}
