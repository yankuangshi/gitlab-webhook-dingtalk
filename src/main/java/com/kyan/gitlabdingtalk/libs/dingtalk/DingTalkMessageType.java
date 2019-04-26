package com.kyan.gitlabdingtalk.libs.dingtalk;

/**
 * DingTalk Message Type Enum
 *
 * @author kyan
 * @date 2019/4/26
 */
public enum DingTalkMessageType {

    /**
     * Type text
     */
    TEXT("text"),
    /**
     * Type link
     */
    LINK("link"),
    /**
     * Type markdown
     */
    MARKDOWN("markdown");

    private String value;

    DingTalkMessageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
