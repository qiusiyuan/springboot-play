package com.hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GreetingController {

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @RequestMapping("/greeting")
  public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    return new Greeting(counter.incrementAndGet(),
              String.format(template, name));
  }

  @RequestMapping("/messege")
  public Greeting messege() {
    final String uri = "https://gturnquist-quoters.cfapps.io/api/random";
     
    RestTemplate restTemplate = new RestTemplate();
    Quote result = restTemplate.getForObject(uri, Quote.class);

    return new Greeting(counter.incrementAndGet(),
              result.getValue().getQuote());
  }
}