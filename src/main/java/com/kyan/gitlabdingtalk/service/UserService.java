package com.kyan.gitlabdingtalk.service;

import com.kyan.gitlabdingtalk.model.UserDO;

import java.util.List;

/**
 * User Service 接口
 *
 * @author kyan
 * @date 2019/4/28
 */
public interface UserService {

    /**
     * 获取所有用户
     * @return
     */
    List<UserDO> listAll();

    /**
     * 根据gitlab用户id获取用户
     * @param gid
     * @return
     */
    UserDO get(Integer gid);

    /**
     * 新增新用户
     * @param user
     * @return
     */
    int save(UserDO user);

    /**
     * 更新用户
     * @param user
     */
    void update(UserDO user);

    /**
     * 删除用户
     * @param gid
     */
    void delete(Integer gid);
}
