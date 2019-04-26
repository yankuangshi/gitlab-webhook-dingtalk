package com.kyan.gitlabdingtalk.gitlab.hook;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * Gitlab Merge Request Label
 *
 * @author kyan
 * @date 2019/4/25
 */
@Data
public class MergeRequestLabel {

    private Integer id;
    private String title;
    private String color;
    @JSONField(name = "project_id")
    private Integer projectId;
    @JSONField(name = "created_at")
    private Date createdAt;
    @JSONField(name = "updated_at")
    private Date updatedAt;
    private Boolean template;
    private String description;
    private String type;
    @JSONField(name = "group_id")
    private Integer groupId;
}
