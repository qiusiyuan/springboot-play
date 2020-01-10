package com.qiusiyuan.helloworld;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HelloworldApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloworldApplication.class, args);
	}

    @Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager()
	{
		List<UserDetails> userDetailsList = new ArrayList<>();

		return new InMemoryUserDetailsManager(userDetailsList);
	}
}
