package org.example.mode;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class SubscribeMessage {
    private static final String responseString = "来了老弟！\n" +
            "\n" +
            "学网课，找答案，期末复习划重点\n" +
            "有苦恼，解疑惑，人生规划怎么做\n" +
            "统统来找资料哥！\n" +
            "\n" +
            "我是资料哥\uD83D\uDE0E\n" +
            "希望能陪伴大家的大学成长！\n" +
            "\n" +
            "--------------------------------\n" +
            "\n" +
            "\uD83D\uDCA1【大学课后作业答案】\n" +
            "戳菜单栏「课后答案」进行查阅\n" +
            "菜单栏里找不到的，请给资料哥留言。\n" +
            "\n" +
            "\uD83D\uDCA1【大学教材PDF电子版】\n" +
            "由于大家的专业和教材版本问题\n" +
            "资料哥没办法帮大家全部下载\n" +
            "但我把找大学课本PDF的检索方法，放到了文章里，需要的同学点击以下链接\uD83D\uDC47\n" +
            "https://mp.weixin.qq.com/s/T7ol72aZHHrXRluWXm5srA";


    private String ToUserName;
    private String FromUserName;
    private long CreateTime;
    private String MsgType;
    private String Event;

    public SubscribeMessage(String xml) {
        Document doc = Jsoup.parse(xml);
        ToUserName = doc.select("xml").select("ToUserName").text();
        FromUserName = doc.select("xml").select("FromUserName").text();
        CreateTime = Integer.parseInt(doc.select("xml").select("CreateTime").text());
        MsgType = doc.select("xml").select("MsgType").text();
        Event = doc.select("xml").select("Event").text();

        ToUserName = RequestMessage.formateMsg(ToUserName);
        FromUserName = RequestMessage.formateMsg(FromUserName);
        MsgType = RequestMessage.formateMsg(MsgType);
        Event = RequestMessage.formateMsg(Event);
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


    public String getResponse() {
        return "<xml>\n" +
                "  <ToUserName><![CDATA["+getFromUserName()+"]]></ToUserName>\n" +
                "  <FromUserName><![CDATA["+getToUserName()+"]]></FromUserName>\n" +
                "  <CreateTime>"+(System.currentTimeMillis() / 1000 )+"</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA["+responseString+"]]></Content>\n" +
                "</xml>\n";
    }
}
