package org.example;

import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;

public class AccessToken {

    private static final Logger LOG = LoggerFactory.getLogger(AccessToken.class);

    private static final Gson G = new Gson();

    public static String accessToken;

    private static final String appid = "";
    private static final String appsecret = "";


    public static void start() {
        new Thread(() -> {
            while (true) {
                try {
                    accessToken = requestToken();
                    LOG.info("RequestToken {}", accessToken);
                    Thread.sleep(5400000);
                } catch (InterruptedException ex) {
                    break;
                }
            }
        }).start();
    }


    private static String requestToken() throws InterruptedException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet("https://api.weixin.qq.com/cgi-bin/token" +
                "?grant_type=client_credential&appid="+ appid +"&secret=" + appsecret);
        try (CloseableHttpResponse res = client.execute(get)) {
            String resString = EntityUtils.toString(res.getEntity());
            LOG.info(resString);
            HashMap map = G.fromJson(resString, HashMap.class);
            return map.get("access_token").toString();
        } catch (IOException ex) {
            Thread.sleep(2 * 60 * 1000);
            return requestToken();
        }
    }


}
