package com.qiusiyuan.helloworld.service;

import com.qiusiyuan.helloworld.dto.user.User;
import com.qiusiyuan.helloworld.dao.InMemoryDao;
import com.qiusiyuan.helloworld.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private InMemoryDao inMemoryDao;

    public String login( String username, String password ) {
        
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken( username, password );
  
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final User userDetails = inMemoryDao.loadUserByUsername( username );
        if (userDetails == null){
            return null;
        }
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    public User register( User userToAdd ) {

        final String username = userToAdd.getUsername();
        boolean flag = inMemoryDao.userExists(username);
        if( flag ) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword( encoder.encode(rawPassword) );
        return inMemoryDao.createUser(userToAdd);
    }
}