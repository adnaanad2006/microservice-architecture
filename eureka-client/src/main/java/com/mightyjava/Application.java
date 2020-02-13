package com.mightyjava;

import brave.sampler.Sampler;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrix
@RefreshScope
public class Application {
	
	/*@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Autowired
	private RestTemplate restTemplate;*/

	@Autowired
	private MyFiegnClient fiegnClient;

	@Value("${greetings.message}")
	private String greetings;


	private Logger logger = LoggerFactory.getLogger(Application.class.getName());

	@RequestMapping("/")
	@HystrixCommand(fallbackMethod = "homeFallback")
	public String home(@RequestHeader("new-header-req-1") String newHeader) throws JSONException {

		logger.info("in eureka client 1 home()");

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", "Hello World (header: " + newHeader + " ) - " + greetings);
		/*jsonObject.put("message-2", new JSONObject(
				restTemplate.exchange(
						"http://localhost:8002/", HttpMethod.GET, null, String.class).getBody()));*/
		jsonObject.put("message-2", new JSONObject(fiegnClient.getClient2()));
		return jsonObject.toString();
	}

	@RequestMapping("/get")
	@HystrixCommand(fallbackMethod = "homeFallbackGet")
	public String home() throws JSONException {

		logger.info("in eureka client 1 home()");

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", "Hello World - " + greetings);
		/*jsonObject.put("message-2", new JSONObject(
				restTemplate.exchange(
						"http://localhost:8002/", HttpMethod.GET, null, String.class).getBody()));*/
		jsonObject.put("message-2", new JSONObject(fiegnClient.getClient2()));
		return jsonObject.toString();
	}

	public String homeFallback(@RequestHeader("new-header-req-1") String newHeader) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", "Hello World");
		jsonObject.put("message-2", "Hello World 8002 is down");
		return jsonObject.toString();
	}

	public String homeFallbackGet() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", "Hello World");
		jsonObject.put("message-2", "Hello World 8002 is down");
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
