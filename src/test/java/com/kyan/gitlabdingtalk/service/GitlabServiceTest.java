package com.kyan.gitlabdingtalk.service;

import lombok.extern.slf4j.Slf4j;
import org.gitlab4j.api.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

/**
 * Gitlab Service test
 *
 * @author kyan
 * @date 2019/4/28
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class GitlabServiceTest {

    @Resource
    private GitlabService gitlabService;

    @Test
    public void testListAllUsers() {
        List<User> gitlabUsers = gitlabService.listAllUsers();
        assertThat(gitlabUsers.size(), greaterThan(1));
    }
}
