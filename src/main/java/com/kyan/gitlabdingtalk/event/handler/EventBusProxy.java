package com.kyan.gitlabdingtalk.event.handler;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.kyan.gitlabdingtalk.commons.annotation.EventBusListener;
import com.kyan.gitlabdingtalk.commons.util.SpringContextUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * EventBus代理类
 *
 * @author kyan
 * @date 2019/4/25
 */
@Component("eventBusProxy")
public class EventBusProxy {

    /**
     * 管理同步事件
     */
    private EventBus syncEventBus = new EventBus();

    /**
     * 管理异步事件
     */
    private AsyncEventBus asyncEventBus = new AsyncEventBus(Executors.newCachedThreadPool());

    public void postSync(Object event) {
        syncEventBus.post(event);
    }

    public void postAsync(Object event) {
        asyncEventBus.post(event);
    }

    @PostConstruct
    public void init() {
        // 获取所有带有 @EventBusListener 的 bean，将他们注册为监听者
        List<Object> listeners = SpringContextUtil.getBeansWithAnnotation(EventBusListener.class);
        for (Object listener : listeners) {
            asyncEventBus.register(listener);
            syncEventBus.register(listener);
        }
    }
}
