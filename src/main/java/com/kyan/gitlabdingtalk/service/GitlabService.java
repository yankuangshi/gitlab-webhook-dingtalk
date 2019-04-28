package com.kyan.gitlabdingtalk.service;

import org.gitlab4j.api.models.User;

import java.util.List;

/**
 * Gitlab Service 接口
 *
 * @author kyan
 * @date 2019/4/28
 */
public interface GitlabService {

    /**
     * 获取所有gitlab用户
     * @return
     */
    List<User> listAllUsers();
}
