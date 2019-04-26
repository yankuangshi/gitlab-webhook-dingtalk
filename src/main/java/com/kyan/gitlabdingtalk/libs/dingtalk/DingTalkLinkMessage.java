package com.kyan.gitlabdingtalk.libs.dingtalk;

import com.alibaba.fastjson.JSON;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;

/**
 * @author kyan
 * @date 2019/4/26
 */
@Data
@Builder
public class DingTalkLinkMessage implements DingTalkMessage {

    private String title;
    private String text;
    private String messageUrl;
    private String picUrl;

    @Override
    public String toJson() {
        HashMap<String, Object> payload = new HashMap<>((int) Math.ceil(2 / 0.75));
        HashMap<String, String> link = new HashMap<>((int) Math.ceil(4 / 0.75));
        link.put("text", text);
        link.put("title", title);
        link.put("picUrl", picUrl);
        link.put("messageUrl", messageUrl);
        payload.put("msgtype", DingTalkMessageType.LINK.getValue());
        payload.put("link", link);
        return JSON.toJSONString(payload);
    }
}
