package com.kyan.gitlabdingtalk.libs.dingtalk;

import com.kyan.gitlabdingtalk.WebhookProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author kyan
 * @date 2019/4/26
 */
@Slf4j
@Component("dingTalkApi")
public class DingTalkApi {

    @Resource
    private WebhookProperties properties;
    @Resource
    private RestTemplate restTemplate;

    public DingTalkResponse sendMessage(DingTalkMessage message) {
        final String webhookUrl = properties.getDingtalkApiUrl() + properties.getDingtalkAccessToken();
        final HttpEntity<String> entity = new HttpEntity<>(message.toJson(), getHeaders());
        ResponseEntity<DingTalkResponse> result = restTemplate.postForEntity(webhookUrl, entity, DingTalkResponse.class);
        log.info("Result: {}", result);
        return result.getBody();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return headers;
    }
}
