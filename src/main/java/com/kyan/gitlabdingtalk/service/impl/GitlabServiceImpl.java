package com.kyan.gitlabdingtalk.service.impl;

import com.kyan.gitlabdingtalk.commons.property.GitlabProperties;
import com.kyan.gitlabdingtalk.service.GitlabService;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Gitlab Service 实现
 *
 * @author kyan
 * @date 2019/4/28
 */
@Service("gitlabService")
public class GitlabServiceImpl implements GitlabService {

    @Resource
    private GitlabProperties gitlabProperties;

    @Override
    public List<User> listAllUsers() {
        GitLabApi gitLabApi = new GitLabApi(gitlabProperties.getHostUrl(), gitlabProperties.getPrivateToken());
        gitLabApi.setIgnoreCertificateErrors(true);
        try {
            return gitLabApi.getUserApi().getActiveUsers();
        } catch (GitLabApiException e) {
            e.printStackTrace();
            return null;
        }
    }
}
