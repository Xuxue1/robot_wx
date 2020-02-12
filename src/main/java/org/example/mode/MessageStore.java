package org.example.mode;


import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageStore {

    private static Gson G = new Gson();

    public static Map<String, String> meuResponse = new HashMap<>();

    public static Map<String, String> keywordResponse = new HashMap<>();


    static {

        InputStream meuRead;
        try {
            meuRead = new FileInputStream("meu_response.json");
        } catch (IOException ex) {
            meuRead = MessageStore.class.getResourceAsStream("/meu_response.json");
            if (meuRead == null) {
                throw new Error();
            }
        }
        InputStream msgRead;

        try {
            msgRead = new FileInputStream("msg_response.json");
        } catch (IOException ex) {
            msgRead = MessageStore.class.getResourceAsStream("/msg_response.json");
            if (msgRead == null) {
                throw new Error();
            }
        }

        try {
            meuResponse = toMap(meuRead);
            keywordResponse = toMap(msgRead);
        } catch (Exception ex) {
            throw new Error();
        }

    }


    static Map<String, String> toMap(InputStream inputStream) throws Exception {
        HashMap<String, String> map =  new HashMap<>();

        int bytes = inputStream.available();
        byte[] data = new byte[bytes];
        inputStream.read(data);
        List<Object> kv = G.fromJson(new String(data), ArrayList.class);
        kv.forEach(a -> {
            if (a instanceof Map) {
                Map m = (Map)a;
                map.put(m.get("key").toString(), m.get("response").toString());
            }
        });
        return map;
    }


}
