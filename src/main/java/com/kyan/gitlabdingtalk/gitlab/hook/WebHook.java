package com.kyan.gitlabdingtalk.gitlab.hook;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * GitLab WebHook抽象类
 *
 * @author kyan
 * @date 2019/4/25
 */
@Data
public abstract class WebHook {

    @JSONField(name = "repository")
    private Repository repository;
    @JSONField(name = "object_kind")
    private String objectKind;

}
