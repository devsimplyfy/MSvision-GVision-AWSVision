
package com.example;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	static Logger logger = Logger.getLogger(Application.class);

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
		}
}