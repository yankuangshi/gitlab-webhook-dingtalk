package com.kyan.gitlabdingtalk.libs.dingtalk;

import com.alibaba.fastjson.JSON;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * DingTalk Message of text type
 * https://open-doc.dingtalk.com/microapp/serverapi3/iydd5h#-4
 * Example:
 *
 * {
 *     "msgtype": "text",
 *     "text": {
 *         "content": "我就是我, 是不一样的烟火@156xxxx8827"
 *     },
 *     "at": {
 *         "atMobiles": [
 *             "156xxxx8827",
 *             "189xxxx8325"
 *         ],
 *         "isAtAll": false
 *     }
 * }
 *
 * @author kyan
 * @date 2019/4/26
 */
@Data
@Builder
public class DingTalkTextMessage implements DingTalkMessage {

    private String content;
    private List<String> atMobiles;
    private boolean atAll;

    @Override
    public String toJson() {
        HashMap<String, Object> payload = new HashMap<>((int) Math.ceil(3 / 0.75));
        HashMap<String, String> text = new HashMap<>((int) Math.ceil(1 / 0.75));
        text.put("content", content);
        HashMap<String, Object> at = new HashMap<>((int) Math.ceil(2 / 0.75));
        if (atMobiles != null && !atMobiles.isEmpty()) {
            at.put("atMobiles", atMobiles);
        }
        at.put("isAtAll", atAll);
        payload.put("msgtype", DingTalkMessageType.TEXT.getValue());
        payload.put("text", text);
        payload.put("at", at);
        return JSON.toJSONString(payload);
    }
}
