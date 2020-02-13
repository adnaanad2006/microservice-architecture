package com.mightyjava;

import brave.sampler.Sampler;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrixDashboard
//@RibbonClient(name = "${feign.name}", configuration = RibbonConfiguration.class)
public class Application {
	
	/*@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Autowired
	private RestTemplate restTemplate;*/

	@Autowired
	private MyFeignClient fiegnClient;

	@Autowired
	private MyFeignClient7 fiegnClient7;

    private Logger logger = LoggerFactory.getLogger(Application.class.getName());

	@RequestMapping("/")
	public String home() throws JSONException {

        logger.info("in eureka client 2 home()");

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", "Hello World 2");
		/*jsonObject.put("message-3", new JSONObject(
				restTemplate.exchange(
						"http://localhost:8003/", HttpMethod.GET, null, String.class).getBody()));*/
		jsonObject.put("message-3", new JSONObject(fiegnClient.getClient3()));
		jsonObject.put("message-7", new JSONObject(fiegnClient7.getClient7()));
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
