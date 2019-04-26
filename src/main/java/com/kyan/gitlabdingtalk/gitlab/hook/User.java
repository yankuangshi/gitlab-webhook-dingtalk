package com.kyan.gitlabdingtalk.gitlab.hook;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Gitlab for both author and assignee
 *
 * @author kyan
 * @date 2019/4/25
 */
@Data
public class User {

    private Integer id;
    private String name;
    private String username;
    private String email;
    @JSONField(name = "avatar_url")
    private String avatarUrl;
}
