package org.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class WxRobot {
    private static final Logger LOG = LoggerFactory.getLogger(WxRobot.class);



    @Autowired
    private Config config;


    @RequestMapping(value = "response", method = RequestMethod.GET)
    public String response(String signature, String timestamp, String nonce, String echostr) {
        return echostr;
    }

    @RequestMapping(value = "response", method = RequestMethod.POST)
    public String res(@RequestBody String xml) {
        LOG.info(xml);
        return xml;
    }




}
