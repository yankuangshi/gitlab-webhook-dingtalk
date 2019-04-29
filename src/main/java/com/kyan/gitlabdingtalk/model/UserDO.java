package com.kyan.gitlabdingtalk.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author kyan
 * @date 2019/4/28
 */
@Data
@Builder
public class UserDO {

    private Integer id;
    private Integer gid;
    private String fullName;
    private String userName;
    private String mobile;
}
