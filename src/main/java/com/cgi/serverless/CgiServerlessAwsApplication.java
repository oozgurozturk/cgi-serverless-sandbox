package com.cgi.serverless;

import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

@SpringBootApplication
public class CgiServerlessAwsApplication {

	private static String MESSAGE = "Tech stack for this application: Api Gateway, Lambda, Spring Cloud\n";
	
	//@Autowired
	//SqsSender sqsSender;
	
	public static void main(String[] args) {
		SpringApplication.run(CgiServerlessAwsApplication.class, args);
	}
	

    //In SpringCloud all of these things (Bean) are endpoints
    //Geleneksel spring boot uygulamasında addrequestmapping vesaire olur amaburada endpointler function olarak expose ediliyor

 
    //curl -H 'Content-Type: text/plain' https://nhhi8vbaug.execute-api.us-east-1.amazonaws.com/default/Spring-Cloud-Function-Lambda -d 'your-input'
    @Bean
    public Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> function() {
        return input -> {
            APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
            responseEvent.setStatusCode(200);
            
            //sqsSender.send(input.getBody());
            responseEvent.setBody(MESSAGE + "Your input has been converted: " + input.getBody().toUpperCase());
            return responseEvent;
        };
    }

}
