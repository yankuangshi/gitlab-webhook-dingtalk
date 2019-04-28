package com.kyan.gitlabdingtalk.controller;

import com.alibaba.fastjson.JSON;
import com.kyan.gitlabdingtalk.commons.util.ApiResponseUtil;
import com.kyan.gitlabdingtalk.event.handler.EventBusProxy;
import com.kyan.gitlabdingtalk.gitlab.hook.MergeRequestHook;
import com.kyan.gitlabdingtalk.gitlab.hook.PushHook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * WebHook Controller
 *
 * @author kyan
 * @date 2019/4/25
 */
@Slf4j
@RestController
@RequestMapping("/webhook/v1")
public class WebHookController {

    private static final String PUSH_EVENT = "Push Hook";
    private static final String MERGE_REQUEST_EVENT = "Merge Request Hook";

    @Resource
    private EventBusProxy eventBusProxy;

    @PostMapping("/send")
    public ApiResponseUtil send(@RequestHeader("X-Gitlab-Event") String event,
                                @RequestBody String json) {
        log.info("request header X-Gitlab-Event: {}", event);
        log.info("request body: {}", json);
        if (PUSH_EVENT.equals(event)) {
            PushHook pushHook = PushHook.parseObject(json);
            eventBusProxy.postSync(pushHook);
        }
        if (MERGE_REQUEST_EVENT.equals(event)) {
            MergeRequestHook mergeRequestHook = MergeRequestHook.parseObject(json);
            eventBusProxy.postSync(mergeRequestHook);
        }
        return ApiResponseUtil.ok();
    }
}
