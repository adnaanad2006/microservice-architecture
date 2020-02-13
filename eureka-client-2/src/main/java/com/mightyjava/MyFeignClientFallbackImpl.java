package com.mightyjava;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class MyFeignClientFallbackImpl implements MyFeignClient {

    @Override
    public String getClient3() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("message", "Hello World 2");
            jsonObject.put("message-3", "Hello World 8003 is down");
            jsonObject.put("message-7", "Hello World 8007 is down");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
