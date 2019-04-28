package com.kyan.gitlabdingtalk;

import com.kyan.gitlabdingtalk.model.UserDO;
import com.kyan.gitlabdingtalk.service.GitlabService;
import com.kyan.gitlabdingtalk.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.gitlab4j.api.models.User;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 程序自启动后执行
 *
 * @author kyan
 * @date 2019/4/28
 */
@Slf4j
@Component
public class ApplicationStartupRunner implements ApplicationRunner {

    @Resource
    private GitlabService gitlabService;
    @Resource
    private UserService userService;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        // load all gitlab users
        List<User> gitlabUsers = gitlabService.listAllUsers();
        for (User gitlabUser : gitlabUsers) {
            if (userService.get(gitlabUser.getId()) == null) {
                log.info("Gitlab user[id={}, username={}] doesn't exit, save into db", gitlabUser.getId(), gitlabUser.getUsername());
                UserDO user = UserDO.builder()
                        .gid(gitlabUser.getId())
                        .username(gitlabUser.getUsername())
                        .build();
                userService.save(user);
            }
        }
    }
}
