package com.kyan.gitlabdingtalk.gitlab.hook;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * Gitlab Merge Request Hook
 *
 * @author kyan
 * @date 2019/4/25
 */
@Data
public class MergeRequestHook extends WebHook {

    private User user;
    private User assignee;
    private Project project;
    @JSONField(name = "object_attributes")
    private MergeRequestObjectAttributes objectAttributes;
    private List<MergeRequestLabel> labels;

    public static MergeRequestHook parseObject(String json) {
        return JSON.parseObject(json, MergeRequestHook.class);
    }
}
