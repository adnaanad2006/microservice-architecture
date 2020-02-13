package com.mightyjava;

import brave.sampler.Sampler;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class Application {

	@Value("${server.port}")
	private String port;

    private Logger logger = LoggerFactory.getLogger(Application.class.getName());

	@RequestMapping("/")
	public String home() throws JSONException {

        logger.info("in eureka client 3 home()");

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", "Hello World 3 from port: " + port);
		return jsonObject.toString();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
