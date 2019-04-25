package com.kyan.gitlabdingtalk.event.handler;

import com.google.common.eventbus.Subscribe;
import com.kyan.gitlabdingtalk.commons.annotation.EventBusListener;
import com.kyan.gitlabdingtalk.gitlab.hook.MergeRequestHook;
import com.kyan.gitlabdingtalk.gitlab.hook.PushHook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author kyan
 * @date 2019/4/25
 */
@Slf4j
@Component
@EventBusListener
public class GitlabEventHandler {

    @Subscribe
    public void onPushEvent(PushHook pushHook) {
        //TODO
    }

    @Subscribe
    public void onMergeRequestEvent(MergeRequestHook mergeRequestHook) {
        //TODO
    }
}
