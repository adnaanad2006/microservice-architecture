package com.rsystems.adnan;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @RequestMapping("/client1Fallback")
    public String client1Fallback() {
        return "Client 1 is down";
    }

    @RequestMapping("/client2Fallback")
    public String client2Fallback() {
        return "Client 2 is down";
    }

    @RequestMapping("/client3Fallback")
    public String client3Fallback() {
        return "Client 3 is down";
    }

    @RequestMapping("/client7Fallback")
    public String client7Fallback() {
        return "Client 7 is down";
    }
}
