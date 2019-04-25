package com.kyan.gitlabdingtalk.gitlab.hook;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @author kyan
 * @date 2019/4/25
 */
@Data
public class MergeRequestObjectAttributes {

    private Integer id;
    private Integer iid;
    @JSONField(name = "source_branch")
    private String sourceBranch;
    @JSONField(name = "target_branch")
    private String targetBranch;
    @JSONField(name = "source_project_id")
    private Integer sourceProjectId;
    @JSONField(name = "target_project_id")
    private Integer targetProjectId;
    @JSONField(name = "author_id")
    private Integer authorId;
    @JSONField(name = "assignee_id")
    private Integer assigneeId;
    private String title;
    @JSONField(name = "created_at")
    private Date createdAt;
    @JSONField(name = "updated_at")
    private Date updatedAt;
    private State state;
    @JSONField(name = "merge_status")
    private String mergeStatus;
    private String description;
    private Project source;
    private Project target;
    @JSONField(name = "last_commit")
    private Commit lastCommit;
    @JSONField(name = "work_in_progress")
    private Boolean workInProgress;
    private String url;
    private Action action;
    private User assignee;
}
