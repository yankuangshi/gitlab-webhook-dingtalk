package com.kyan.gitlabdingtalk.commons.property;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

/**
 * Gitlab相关属性
 *
 * @author kyan
 * @date 2019/4/28
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "gitlab")
public class GitlabProperties implements InitializingBean {

    private String apiToken;
    private String hostUrl;
    private String privateToken;

    @Override
    public void afterPropertiesSet() throws Exception {
        checkNonNull();
    }

    private void checkNonNull() {
        Assert.notNull(apiToken, "The property 'gitlab.api-token' should not be null");
        Assert.notNull(hostUrl, "The property 'gitlab.host-url' should not be null");
        Assert.notNull(privateToken, "The property 'gitlab.private-token' should not be null");
    }
}