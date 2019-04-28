package com.kyan.gitlabdingtalk.libs.dingtalk;

import com.kyan.gitlabdingtalk.commons.property.WebhookProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
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

    public boolean sendMessage(DingTalkMessage message) {
        boolean ret = false;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        final String requestJson = message.toJson();
        final String webhookUrl = properties.getDingtalkApiUrl() + properties.getDingtalkAccessToken();
        final HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);
        ResponseEntity<DingTalkResponse> result = restTemplate.postForEntity(webhookUrl, requestEntity, DingTalkResponse.class);
        log.info("Result: {}", result);
        if (result != null && result.getStatusCode() == HttpStatus.OK) {
            if (result.getBody().getErrCode().equals(0)) {
                ret = true;
            }
        }
        return ret;
    }
}
