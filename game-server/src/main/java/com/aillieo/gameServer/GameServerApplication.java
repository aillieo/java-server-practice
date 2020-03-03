package com.aillieo.gameServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GameServerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context  = SpringApplication.run(GameServerApplication.class, args);

		try{
			context.getBean(TCPServer.class);
		}
		catch (Exception e)
		{

		}
		finally {
		}
	}

}
