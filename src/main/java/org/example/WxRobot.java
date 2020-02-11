package org.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class WxRobot {



    @Autowired
    private Config config;


    @RequestMapping(value = "response", method = RequestMethod.GET)
    public String response(String signature, String timestamp, String nonce, String echostr) {
        return echostr;
    }



}
