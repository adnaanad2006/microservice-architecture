package com.rsystems.adnan;

import brave.sampler.Sampler;
import com.rsystems.adnan.com.rsystems.adnan.filters.ErrorFilter;
import com.rsystems.adnan.com.rsystems.adnan.filters.PostFilter;
import com.rsystems.adnan.com.rsystems.adnan.filters.PreFilter;
import com.rsystems.adnan.com.rsystems.adnan.filters.RouteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class ZuulServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulServerApplication.class, args);
	}


    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

	@Bean
	public PreFilter getPreFilter() {
		return new PreFilter();
	}

	@Bean
	public PostFilter getPostFilter() {
		return new PostFilter();
	}

	@Bean
	public RouteFilter getRouteFilter() {
		return new RouteFilter();
	}

	@Bean
	public ErrorFilter getErrorFilter() {
		return new ErrorFilter();
	}

}
