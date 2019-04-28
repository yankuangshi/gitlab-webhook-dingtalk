package com.kyan.gitlabdingtalk.commons.property;

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

    private String dingtalkApiUrl;
    private String dingtalkAccessToken;

    @Override
    public void afterPropertiesSet() throws Exception {
        checkNonNull();
    }

    private void checkNonNull() {
        Assert.notNull(dingtalkApiUrl, "The property 'webhook.dingtalk-api-url' should not be null");
        Assert.notNull(dingtalkAccessToken, "The property 'webhook.dingtalk-access-token' should not be null");
    }
}
