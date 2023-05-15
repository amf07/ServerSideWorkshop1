package sg.nus.iss.visa.ssf.demo;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class DemoApplication {

	private static final Logger logger = LoggerFactory.getLogger
	(DemoApplication.class);

	//Default port Number
	private static final String DEFAULT_PORT = "3000";

	public static void main(String[] args) {
		logger.info("Main method begins....");
	
		//Initialize the spring app
		SpringApplication app = new SpringApplication(DemoApplication.class);
		

		//read args and check for "Port" paremeter

		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);

		List opsValues = appArgs.getOptionValues("port");
		
		String portNumber = null;
			
		// if port numebr is not in argument
		if(opsValues == null || opsValues.get(0) == null){

			//read port number fromm env variables
			portNumber = System.getenv("PORT");

			if(portNumber == null){
				portNumber = DEFAULT_PORT;
			}

		} else{
		//passing port number from CLI
		portNumber = (String) opsValues.get(0);
		}

		if(portNumber != null){
			//setting port number in the spring-boot config
			app.setDefaultProperties(Collections.singletonMap("server.port", portNumber));
		}

		logger.info("Port number is : " +portNumber);	
	//launch spring boot app	
	app.run(args);
	}

}


