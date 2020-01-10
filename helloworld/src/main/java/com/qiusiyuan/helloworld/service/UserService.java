package com.qiusiyuan.helloworld.service;

import com.qiusiyuan.helloworld.dto.user.User;
import com.qiusiyuan.helloworld.dao.InMemoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    InMemoryDao InMemoryDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = InMemoryDao.loadUserByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("Non existing user");
        }
        return user;
    }

}