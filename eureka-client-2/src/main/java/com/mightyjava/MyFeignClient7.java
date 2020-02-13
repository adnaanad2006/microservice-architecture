package com.mightyjava;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "${feign.client7}", fallback = MyFeignClient7FallbackImpl.class)
@RibbonClient(name = "${feign.client7}")//, configuration = RibbonConfiguration.class
public interface MyFeignClient7 {

    @GetMapping("/")
    String getClient7();
}
