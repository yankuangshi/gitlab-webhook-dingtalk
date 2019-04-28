package com.kyan.gitlabdingtalk.service;

import com.kyan.gitlabdingtalk.libs.dingtalk.DingTalkLinkMessage;
import com.kyan.gitlabdingtalk.libs.dingtalk.DingTalkMarkdownMessage;
import com.kyan.gitlabdingtalk.libs.dingtalk.DingTalkTextMessage;

/**
 * DingTalk Robot Service Interface
 *
 * @author kyan
 * @date 2019/4/26
 */
public interface DingTalkRobotService {

    /**
     * 调用钉钉机器人推送link类型的消息
     * @param linkMessage
     */
    void pushLinkMessage(DingTalkLinkMessage linkMessage);

    /**
     * 调用钉钉机器人推送text类型的消息
     * @param textMessage
     */
    void pushTestMessage(DingTalkTextMessage textMessage);

    /**
     * 调用钉钉机器人推送markdown类型的消息
     * @param markdownMessage
     */
    void pushMarkdownMessage(DingTalkMarkdownMessage markdownMessage);
}
