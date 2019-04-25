package com.kyan.gitlabdingtalk.gitlab.hook;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Gitlab Commit
 *
 * @author kyan
 * @date 2019/4/25
 */
@Data
public class Commit {

    private String id;
    private String message;
    private Date timestamp;
    private String url;
    private User author;
    private List<String> added;
    private List<String> modified;
    private List<String> removed;
}
