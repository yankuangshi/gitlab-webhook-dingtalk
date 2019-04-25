package com.kyan.gitlabdingtalk.gitlab.hook;

import java.util.List;

/**
 * @author kyan
 * @date 2019/4/25
 */
public class MergeRequestHook extends WebHook {

    private User user;
    private User assignee;
    private Project project;
    private MergeRequestObjectAttributes objectAttributes;
    private List<MergeRequestLabel> labels;
}
