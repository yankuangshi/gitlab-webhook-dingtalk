package com.kyan.gitlabdingtalk;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

/**
 * WebHook属性
 *
 * @author kyan
 * @date 2019/4/25
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "webhook")
public class WebhookProperties implements InitializingBean {

    private String apiToken;
    private String dingtalkUrl;
    private String dingtalkToken;

    @Override
    public void afterPropertiesSet() throws Exception {
        checkNonNull();
    }

    private void checkNonNull() {
        Assert.notNull(apiToken, "The property 'webhook.api.token' should not be null");
        Assert.notNull(dingtalkUrl, "The property 'webhook.dingtalk.url' should not be null");
        Assert.notNull(dingtalkToken, "The property 'webhook.dingtalk.token' should not be null");
    }
}
