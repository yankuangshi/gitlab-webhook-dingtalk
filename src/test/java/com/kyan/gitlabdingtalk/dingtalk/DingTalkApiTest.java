package com.kyan.gitlabdingtalk.dingtalk;

import com.kyan.gitlabdingtalk.libs.dingtalk.DingTalkApi;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * DingTalk API test
 *
 * @author kyan
 * @date 2019/4/26
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DingTalkApiTest {

    @Resource
    private DingTalkApi dingTalkApi;


    @Test
    public void testSendTextMessage() {
//        DingTalkTextMessage textMessage = DingTalkTextMessage.builder()
//                .content("kyan 创建任务：#001「测试天啦撸」")
//                .atMobiles(Lists.newArrayList("15657510907"))
//                .atAll(false)
//                .build();
//        log.info("Text Message: {}", textMessage.toJson());
//        dingTalkApi.sendMessage(textMessage);
    }

    @Test
    public void testSendLinkMessage() {
        //TODO
    }

    @Test
    public void testSendMarkdownMessage() {
        //TODO
    }
}
