package com.kyan.gitlabdingtalk.gitlab.hook;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Gitlab Repository
 *
 * @author kyan
 * @date 2019/4/25
 */
@Data
public class Repository {

    private String name;
    private String url;
    private String description;
    private String homepage;
    @JSONField(name = "git_ssh_url")
    private String gitSshUrl;
    @JSONField(name = "git_http_url")
    private String gitHttpUrl;
    @JSONField(name = "visibility_level")
    private Integer visibilityLevel;

}
