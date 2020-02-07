package com.qiusiyuan.helloworld.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.qiusiyuan.helloworld.dto.user.User;
import com.qiusiyuan.helloworld.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtTokenAuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/authentication/login", method = RequestMethod.POST)
    public String createToken( String username,String password, HttpServletResponse response) throws AuthenticationException {
        String jwtToken = authService.login( username, password );
        if (jwtToken == null){
            return null;
        }
        Cookie cookie = new Cookie("access_token", jwtToken);
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setHttpOnly(true); // for XSS attack
        cookie.setPath("/"); // allow global check
        //cookie.setSecure(true); // for https
        response.addCookie(cookie);

        return jwtToken;
    }

    @RequestMapping(value = "/authentication/register", method = RequestMethod.POST)
    public User register( @RequestBody User addedUser ) throws AuthenticationException {
        return authService.register(addedUser);
    }

}