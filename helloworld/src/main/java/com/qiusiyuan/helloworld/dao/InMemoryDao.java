package com.qiusiyuan.helloworld.dao;

import java.util.ArrayList;
import com.qiusiyuan.helloworld.dto.user.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class InMemoryDao {
    private static ArrayList<User> userTable = new ArrayList<>();;

    private static final Logger logger = LoggerFactory.getLogger(InMemoryDao.class);

    public InMemoryDao(){
    }


    public User loadUserByUsername(String username){
        for (User user : userTable){
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    public Boolean userExists(String username){
        for (User user : userTable){
            if (user.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public User createUser(User user){
        userTable.add(user);
        return user;
    }

    public void printUser(){
        logger.info("Print user");
        for (User user : userTable){
           logger.info(user.toString());
        }
    }
}