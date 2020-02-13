package com.mightyjava;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//@FeignClient(value = "${feign.name}", url = "${feign.url}")
@FeignClient(value = "${feign.name}")
public interface MyFiegnClient {

    @GetMapping("/")
    String getClient2();
}
