package com.kyan.gitlabdingtalk.service;

import com.kyan.gitlabdingtalk.model.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * User Service test
 *
 * @author kyan
 * @date 2019/4/28
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testSaveUser() {
        UserDO user = UserDO.builder()
                .gid(1)
                .username("kyan")
                .build();
        int ret = userService.save(user);
        assertThat(ret, is(1));
    }

    @Test
    public void testGetOneUser() {
        UserDO user = userService.get(1);
        assertThat(user.getUsername(), is("kyan"));
    }

    @Test
    public void testGetAllUsers() {
        List<UserDO> users = userService.listAll();
        assertThat(users.size(), is(1));
    }

    @Test
    public void testUpdateUser() {
        UserDO user = UserDO.builder()
                .gid(1).mobile("156xxxx0907").build();
        userService.update(user);
        user = userService.get(1);
        assertThat(user.getMobile(), is("156xxxx0907"));
    }

    @Test
    public void testDeleteUser() {
        userService.delete(1);
        UserDO user = userService.get(1);
        assertThat(user, is(nullValue()));
    }
}
