package com.kyan.gitlabdingtalk.service.impl;

import com.kyan.gitlabdingtalk.mapper.UserMapper;
import com.kyan.gitlabdingtalk.model.UserDO;
import com.kyan.gitlabdingtalk.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User Service 实现
 *
 * @author kyan
 * @date 2019/4/28
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<UserDO> listAll() {
        return userMapper.listAll();
    }

    @Override
    public UserDO get(Integer gid) {
        return userMapper.getUserByGid(gid);
    }

    @Override
    public int save(UserDO user) {
        return userMapper.save(user);
    }

    @Override
    public void update(UserDO user) {
        userMapper.update(user);
    }

    @Override
    public void delete(Integer gid) {
        userMapper.delete(gid);
    }
}
