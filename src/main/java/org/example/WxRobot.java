package org.example;


import com.google.gson.Gson;
import org.example.mode.MenuClickMessage;
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

    static {
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


    @RequestMapping(value = "response", method = RequestMethod.GET)
    public String response(String signature, String timestamp, String nonce, String echostr) {
        return echostr;
    }

    @RequestMapping(value = "response", method = RequestMethod.POST)
    public String res(@RequestBody String xml) {
        if (xml.matches("<Event><!\\[CDATA\\[.*?\\]\\]></Event>")) {
            MenuClickMessage msg = new MenuClickMessage(xml);
            LOG.info(G.toJson(msg));
            return "";
        } else {
            RequestMessage msg = new RequestMessage(xml);
            LOG.info(G.toJson(msg));
            return msg.revertMsg();
        }
    }




}
