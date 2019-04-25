package com.kyan.gitlabdingtalk.gitlab.hook;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Gitlab Project
 *
 * @author kyan
 * @date 2019/4/25
 */
@Data
public class Project {

    private Integer id;
    private String name;
    private String description;
    @JSONField(name = "web_url")
    private String webUrl;
    @JSONField(name = "avatar_url")
    private String avatarUrl;
    @JSONField(name = "git_ssh_url")
    private String gitSshUrl;
    @JSONField(name = "git_http_url")
    private String gitHttpUrl;
    private String namespace;
    @JSONField(name = "visibility_level")
    private Integer visibilityLevel;
    @JSONField(name = "path_with_namespace")
    private String pathWithNamespace;
    @JSONField(name = "default_branch")
    private String defaultBranch;
    private String homepage;
    private String url;
    @JSONField(name = "ssh_url")
    private String sshUrl;
    @JSONField(name = "http_url")
    private String httpUrl;

}
