package org.example.mode;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;



public class RequestMessage {

    private String ToUserName;
    private String FromUserName;
    private Integer CreateTime;
    private String MsgType;
    private String Content;
    private String MsgId;


    public RequestMessage(String xml) {
        Document doc = Jsoup.parse(xml);
        ToUserName = doc.select("xml").select("ToUserName").text();
        FromUserName = doc.select("xml").select("FromUserName").text();
        CreateTime = Integer.parseInt(doc.select("xml").select("CreateTime").text());
        MsgType = doc.select("xml").select("MsgType").text();
        Content = doc.select("xml").select("Content").text();
        MsgId = doc.select("xml").select("MsgId").text();
        ToUserName = RequestMessage.formateMsg(ToUserName);
        FromUserName = RequestMessage.formateMsg(FromUserName);
        MsgType = RequestMessage.formateMsg(MsgType);
        Content = RequestMessage.formateMsg(Content);
    }

    public static String formateMsg(String msg) {
        return msg.replace("<![CDATA[", "").replace("]]>", "");
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

    public Integer getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Integer createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }

    public String revertMsg() {
        return "<xml>\n" +
                "  <ToUserName><![CDATA["+getFromUserName()+"]]></ToUserName>\n" +
                "  <FromUserName><![CDATA["+getToUserName()+"]]></FromUserName>\n" +
                "  <CreateTime>"+(System.currentTimeMillis() / 1000 )+"</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[回复:\t"+getContent()+"]]></Content>\n" +
                "</xml>\n";
    }
}
