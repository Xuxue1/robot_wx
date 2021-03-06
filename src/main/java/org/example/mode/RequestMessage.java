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

    public String createResponse(String msg) {
        if (msg.equals("https://mp.weixin.qq.com/s/T7ol72aZHHrXRluWXm5srA")) {
            return createDefaultMessage();
        }
        return "<xml>\n" +
                "  <ToUserName><![CDATA["+getFromUserName()+"]]></ToUserName>\n" +
                "  <FromUserName><![CDATA["+getToUserName()+"]]></FromUserName>\n" +
                "  <CreateTime>"+(System.currentTimeMillis() / 1000 )+"</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA["+ msg +"]]></Content>\n" +
                "</xml>\n";
    }

    public String createDefaultMessage() {
        return "<xml>\n" +
                "  <ToUserName><![CDATA["+getFromUserName()+"]]></ToUserName>\n" +
                "  <FromUserName><![CDATA["+getToUserName()+"]]></FromUserName>\n" +
                "  <CreateTime>"+(System.currentTimeMillis() / 1000 )+"</CreateTime>\n" +
                "  <MsgType><![CDATA[news]]></MsgType>\n" +
                "  <ArticleCount>1</ArticleCount>\n" +
                "  <Articles>\n" +
                "    <item>\n" +
                "      <Title><![CDATA[（2.12 更新）知乎热门：免费大学教材 PDF 哪里找？]]></Title>\n" +
                "      <Description><![CDATA[快开学了，各种资料答案走起~]]></Description>\n" +
                "      <PicUrl><![CDATA[http://mmbiz.qpic.cn/mmbiz_jpg/TS8rulAuOdKFq1HV4FkXkWE731UKq922h3Qqt0UpIWyr6rDauyfOwOu9JVPOjzXp3I0vZmLsrrgBuvNcbK0TFg/0?wx_fmt=jpeg]]></PicUrl>\n" +
                "      <Url><![CDATA[https://mp.weixin.qq.com/s/J95beHEQHAwQ6U2l8XwfHA]]></Url>\n" +
                "    </item>\n" +
                "  </Articles>\n" +
                "</xml>";
    }
}
