package org.example;


import com.google.gson.Gson;
import org.example.mode.RequestMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class WxRobot {
    private static final Logger LOG = LoggerFactory.getLogger(WxRobot.class);

    private static final Gson G = new Gson();


    @Autowired
    private Config config;


    @RequestMapping(value = "response", method = RequestMethod.GET)
    public String response(String signature, String timestamp, String nonce, String echostr) {
        return echostr;
    }

    @RequestMapping(value = "response", method = RequestMethod.POST)
    public String res(@RequestBody String xml) {
        RequestMessage msg = new RequestMessage(xml);
        LOG.info(G.toJson(msg));
        return msg.revertMsg();
    }




}
