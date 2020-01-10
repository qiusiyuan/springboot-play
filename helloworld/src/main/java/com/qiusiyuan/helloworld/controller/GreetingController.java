package com.qiusiyuan.helloworld.controller;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

import com.qiusiyuan.helloworld.dto.Greeting;
import com.qiusiyuan.helloworld.dto.Quote;
import com.qiusiyuan.helloworld.dto.user.User;

@RestController
public class GreetingController {

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @RequestMapping("/greeting")
  public Greeting greeting() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String name =  ((User)(auth.getPrincipal())).getUsername();
    return new Greeting(counter.incrementAndGet(),
              String.format(template, name));
  }

  @RequestMapping("/message")
  public Greeting messege() {
    final String uri = "https://gturnquist-quoters.cfapps.io/api/random";
     
    RestTemplate restTemplate = new RestTemplate();
    Quote result = restTemplate.getForObject(uri, Quote.class);

    return new Greeting(counter.incrementAndGet(),
              result.getValue().getQuote());
  }
}