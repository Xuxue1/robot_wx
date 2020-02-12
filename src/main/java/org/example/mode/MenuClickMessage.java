package org.example.mode;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MenuClickMessage {

    private String ToUserName;
    private String FromUserName;
    private long CreateTime;
    private String MsgType;
    private String Event;
    private String EventKey;

    public MenuClickMessage(String xml) {
        Document doc = Jsoup.parse(xml);
        ToUserName = doc.select("xml").select("ToUserName").text();
        FromUserName = doc.select("xml").select("FromUserName").text();
        CreateTime = Integer.parseInt(doc.select("xml").select("CreateTime").text());
        MsgType = doc.select("xml").select("MsgType").text();
        Event = doc.select("xml").select("Event").text();
        EventKey = doc.select("xml").select("EventKey").text();


        ToUserName = RequestMessage.formateMsg(ToUserName);
        FromUserName = RequestMessage.formateMsg(FromUserName);
        MsgType = RequestMessage.formateMsg(MsgType);
        Event = RequestMessage.formateMsg(Event);
        EventKey = RequestMessage.formateMsg(EventKey);
    }


    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }

    public String createResponse(String msg) {
        return "<xml>\n" +
                "  <ToUserName><![CDATA["+getFromUserName()+"]]></ToUserName>\n" +
                "  <FromUserName><![CDATA["+getToUserName()+"]]></FromUserName>\n" +
                "  <CreateTime>"+(System.currentTimeMillis() / 1000 )+"</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[回复:\t"+ msg +"]]></Content>\n" +
                "</xml>\n";
    }
}
