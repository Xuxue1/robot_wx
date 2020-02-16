package org.example;


import com.google.gson.Gson;
import org.example.mode.MenuClickMessage;
import org.example.mode.MessageStore;
import org.example.mode.RequestMessage;
import org.example.mode.SubscribeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping("/api")
public class WxRobot {
    private static final Logger LOG = LoggerFactory.getLogger(WxRobot.class);

    private static final Gson G = new Gson();

    private static Set<String> users = new HashSet<>();

    static {
        try {
            FileInputStream inputStream = new FileInputStream("users");
            ObjectInputStream in = new ObjectInputStream(inputStream);
            users = (Set<String>) in.readObject();
            in.close();
            inputStream.close();
            LOG.info("Reade users {}", users);
        } catch (Exception e) {
            LOG.warn(e.getMessage());
        }

        try {
            AccessToken.start();
            Thread.sleep(5000);
            LOG.info("meu get" + Util.getMeu());
            LOG.info("meu delete" + Util.deleteMeu());
            LOG.info("meu set" + Util.setMeu(Util.getConfigMeu()));
            LOG.info("meu get" + Util.getMeu());
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    public static void addUser(String key) {
        try {
            users.add(key);
            FileOutputStream outputStream = new FileOutputStream("users");
            ObjectOutputStream out = new ObjectOutputStream(outputStream);
            out.writeObject(users);
            outputStream.close();
            out.close();
        } catch (Exception ex) {
            LOG.warn(ex.getMessage());
        }
    }


    @RequestMapping(value = "response", method = RequestMethod.GET)
    public String response(String signature, String timestamp, String nonce, String echostr) {
        return echostr;
    }

    @RequestMapping(value = "response", method = RequestMethod.POST)
    public String res(@RequestBody String xml) throws Exception{
        LOG.info(xml);
        if (xml.contains("<Event><![CDATA[subscribe]]></Event>")) {
            SubscribeMessage subscribeMessage = new SubscribeMessage(xml);
            return subscribeMessage.getResponse();
        }
        if (xml.contains("<EventKey>")) {
            MenuClickMessage msg = new MenuClickMessage(xml);
            LOG.info(G.toJson(msg));
            String key = msg.getEventKey();
            if (MessageStore.meuResponse.containsKey(key)) {
                return msg.createResponse(MessageStore.meuResponse.get(key));
            } else {
                return "";
            }
        }
        RequestMessage msg = new RequestMessage(xml);
        for(String regex : MessageStore.keywordResponse.keySet()) {
            if (msg.getContent().matches(regex)) {
                return msg.createResponse(MessageStore.keywordResponse.get(regex));
            }
        }
        String user = msg.getFromUserName();
        if (!users.contains(user)) {
            addUser(user);
            Thread.sleep(2000);
            return msg.createDefaultMessage();
        }
        return "";
    }




}
