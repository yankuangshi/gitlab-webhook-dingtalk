package com.kyan.gitlabdingtalk.libs.dingtalk;

import com.alibaba.fastjson.JSON;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * DingTalk Message of type markdown
 * https://open-doc.dingtalk.com/microapp/serverapi3/iydd5h#-5
 * Example:
 *
 * {
 *      "msgtype": "markdown",
 *      "markdown": {
 *          "title":"杭州天气",
 *          "text": "#### 杭州天气 @156xxxx8827\n" +
 *                  "> 9度，西北风1级，空气良89，相对温度73%\n\n" +
 *                  "> ![screenshot](https://gw.alipayobjects.com/zos/skylark-tools/public/files/84111bbeba74743d2771ed4f062d1f25.png)\n"  +
 *                  "> ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n"
 *      },
 *     "at": {
 *         "atMobiles": [
 *             "156xxxx8827",
 *             "189xxxx8325"
 *         ],
 *         "isAtAll": false
 *     }
 *  }
 *
 * @author kyan
 * @date 2019/4/26
 */
@Data
@Builder
public class DingTalkMarkdownMessage implements DingTalkMessage {

    private String title;
    private String text;
    private List<String> atMobiles;
    private boolean atAll;

    @Override
    public String toJson() {
        HashMap<String, Object> payload = new HashMap<>((int) Math.ceil(3 / 0.75));
        HashMap<String, String> markdown = new HashMap<>((int) Math.ceil(2 / 0.75));
        markdown.put("title", title);
        markdown.put("text", text);
        HashMap<String, Object> at = new HashMap<>((int) Math.ceil(2 / 0.75));
        if (atMobiles != null && !atMobiles.isEmpty()) {
            at.put("atMobiles", atMobiles);
        }
        at.put("isAtAll", atAll);
        payload.put("msgtype", DingTalkMessageType.MARKDOWN.getValue());
        payload.put("markdown", markdown);
        payload.put("at", at);
        return JSON.toJSONString(payload);
    }
}
