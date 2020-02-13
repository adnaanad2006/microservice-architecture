package com.rsystems.adnan;

import brave.sampler.Sampler;
import org.json.JSONException;
import org.json.JSONObject;
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
public class EurekaClient7Application {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClient7Application.class, args);
	}


	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}


	private Logger logger = LoggerFactory.getLogger(EurekaClient7Application.class.getName());


	@Value("${server.port}")
	private String port;

	@RequestMapping("/")
	public String home() throws JSONException {

		logger.info("in eureka client 7 home()");

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", "Hello World 7 from port: " + port);
		return jsonObject.toString();
	}


}
