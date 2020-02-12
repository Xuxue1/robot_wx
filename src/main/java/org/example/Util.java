package org.example;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Util {
    private static final Logger LOG = LoggerFactory.getLogger(Util.class);

    public static String getMeu() throws InterruptedException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet("https://api.weixin.qq.com/cgi-bin/" +
                "get_current_selfmenu_info?access_token=" + AccessToken.accessToken);
        try(CloseableHttpResponse res = client.execute(get)) {
            return EntityUtils.toString(res.getEntity(), "utf-8");
        } catch (IOException ex) {
            Thread.sleep(1000*60);
            return getMeu();
        }
    }

    public static String getConfigMeu() throws InterruptedException {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream("meu.json");
        } catch (IOException e) {
           inputStream = Util.class.getResourceAsStream("/meu.json");
           if (inputStream == null) {
               throw new Error();
           }
        }

        try {
            int av = inputStream.available();
            byte[] data = new byte[av];
            inputStream.read(data);
            String s =  new String(data);
            LOG.info("read config {}", s);
            return s;
        } catch (IOException ex) {
            throw new Error();
        }
    }

    public static String setMeu(String json) throws InterruptedException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost get = new HttpPost("https://api.weixin.qq.com/cgi-bin/" +
                "menu/create?access_token=" + AccessToken.accessToken);
        get.setEntity(new StringEntity(json, "utf-8"));
        try(CloseableHttpResponse res = client.execute(get)) {
            return EntityUtils.toString(res.getEntity(), "utf-8");
        } catch (IOException ex) {
            Thread.sleep(1000*60);
            return getMeu();
        }
    }

    public static String deleteMeu() throws InterruptedException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet("https://api.weixin.qq.com/cgi-bin/" +
                "menu/delete?access_token=" + AccessToken.accessToken);
        try(CloseableHttpResponse res = client.execute(get)) {
            return EntityUtils.toString(res.getEntity(), "utf-8");
        } catch (IOException ex) {
            Thread.sleep(1000*60);
            return getMeu();
        }
    }


}
