package com.mightyjava;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "${feign.name}", fallback = MyFeignClientFallbackImpl.class)
@RibbonClient(name = "${feign.name}")//, configuration = RibbonConfiguration.class
public interface MyFeignClient {

    @GetMapping("/")
    String getClient3();
}
