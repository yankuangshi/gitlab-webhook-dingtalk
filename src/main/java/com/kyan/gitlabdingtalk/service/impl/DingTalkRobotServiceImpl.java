package com.kyan.gitlabdingtalk.service.impl;

import com.kyan.gitlabdingtalk.libs.dingtalk.DingTalkApi;
import com.kyan.gitlabdingtalk.libs.dingtalk.DingTalkLinkMessage;
import com.kyan.gitlabdingtalk.libs.dingtalk.DingTalkMarkdownMessage;
import com.kyan.gitlabdingtalk.libs.dingtalk.DingTalkTextMessage;
import com.kyan.gitlabdingtalk.service.DingTalkRobotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * DingTalk Robot Service Implement
 *
 * @author kyan
 * @date 2019/4/26
 */
@Slf4j
@Service("dingTalkRobotService")
public class DingTalkRobotServiceImpl implements DingTalkRobotService {

    @Resource
    private DingTalkApi dingTalkApi;

    @Override
    public void pushLinkMessage(DingTalkLinkMessage linkMessage) {
        dingTalkApi.sendMessage(linkMessage);
    }

    @Override
    public void pushTestMessage(DingTalkTextMessage textMessage) {
        dingTalkApi.sendMessage(textMessage);
    }

    @Override
    public void pushMarkdownMessage(DingTalkMarkdownMessage markdownMessage) {
        log.info("Send markdown message: {}", markdownMessage.toJson());
        dingTalkApi.sendMessage(markdownMessage);
    }
}
