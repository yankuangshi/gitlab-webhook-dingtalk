package com.kyan.gitlabdingtalk.mapper;

import com.kyan.gitlabdingtalk.model.UserDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * User DAO 接口
 *
 * @author kyan
 * @date 2019/4/28
 */
@Mapper
public interface UserMapper {

    /**
     * 获取所有用户
     *
     * @return
     */
    @Select("SELECT * FROM gitlab_user")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gid", column = "gid"),
            @Result(property = "username", column = "username"),
            @Result(property = "mobile", column = "mobile")
    })
    List<UserDO> listAll();

    /**
     * 根据gid获取用户
     *
     * @param gid
     * @return
     */
    @Select("SELECT * FROM gitlab_user WHERE gid = #{gid}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gid", column = "gid"),
            @Result(property = "username", column = "username"),
            @Result(property = "mobile", column = "mobile")
    })
    UserDO getUserByGid(Integer gid);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Insert("INSERT INTO gitlab_user(gid, username) VALUES (#{gid}, #{username})")
    int save(UserDO user);

    /**
     * 更新用户手机号
     *
     * @param user
     */
    @Update("UPDATE gitlab_user SET mobile = #{mobile} WHERE gid = #{gid}")
    void update(UserDO user);

    /**
     * 删除用户
     * @param gid
     */
    @Delete("DELETE FROM gitlab_user WHERE gid = #{gid}")
    void delete(Integer gid);
}
