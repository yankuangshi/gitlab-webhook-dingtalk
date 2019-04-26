package com.kyan.gitlabwebhook.dingtalk;

import com.google.common.collect.Lists;
import com.kyan.gitlabdingtalk.libs.dingtalk.DingTalkTextMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author kyan
 * @date 2019/4/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DingTalkApiTest {

//    @Resource
//    private DingTalkApi dingTalkApi;


    @Test
    public void testSendTextMessage() {
        DingTalkTextMessage message = DingTalkTextMessage.builder()
                .content("严匡世 创建任务：#001「天啦撸」")
                .atMobiles(Lists.newArrayList("15657510907"))
                .atAll(false)
                .build();

    }

    @Test
    public void testSendLinkMessage() {
    }

    @Test
    public void testSendMarkdownMessage() {

    }
}
