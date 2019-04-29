package com.kyan.gitlabdingtalk.event.handler;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.eventbus.Subscribe;
import com.kyan.gitlabdingtalk.commons.annotation.EventBusListener;
import com.kyan.gitlabdingtalk.gitlab.hook.Commit;
import com.kyan.gitlabdingtalk.gitlab.hook.MergeRequestHook;
import com.kyan.gitlabdingtalk.gitlab.hook.PushHook;
import com.kyan.gitlabdingtalk.gitlab.hook.State;
import com.kyan.gitlabdingtalk.libs.dingtalk.DingTalkMarkdownMessage;
import com.kyan.gitlabdingtalk.model.UserDO;
import com.kyan.gitlabdingtalk.service.DingTalkRobotService;
import com.kyan.gitlabdingtalk.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Gitlab的事件处理器
 *
 * @author kyan
 * @date 2019/4/25
 */
@Slf4j
@Component
@EventBusListener
public class GitlabEventHandler {

    private static final String SEPARATOR = "/";

    @Resource
    private DingTalkRobotService dingTalkRobotService;
    @Resource
    private UserService userService;

    /**
     * 订阅Gitlab的Push事件，把Push事件包装成钉钉markdown消息
     *
     * eg.
     * {
     *   "msgtype": "markdown",
     *   "markdown": {
     *     "title": "xxx pushed to branch [develop]@[repo_name]",
     *     "text": "xxx pushed to branch [develop]@[repo_name]\n" +
     *             "> [266468b2](http://gitlab.example.com/groupname/gitlabtest/commit/266468b2d596fd95f0a1b4c9a984b568698ed6f6): Finish new feature\n " +
     *             "> [fcf05237](http://gitlab.example.com/groupname/gitlabtest/commit/fcf05237ac703b772c733cdefd5c14b78aab3925): Merge branch 'develop' into 'master'\n\nFinish new feature\n\nSee merge request groupname/gitlabtest!1"
     *   },
     *   "at": {
     *      "atMobiles": [
     *             "156xxxx8827",
     *      ],
     *      "isAtAll": false
     *   }
     * }
     *
     * @param pushHook
     */
    @Subscribe
    public void onPushEvent(PushHook pushHook) {
        log.info("onPushEvent event_name: {}", pushHook.getObjectKind());
        log.info("onPushEvent content: {}", JSON.toJSONString(pushHook));
        // 把push事件封装成钉钉消息进行推送
        String userFullName = pushHook.getUserName();
        int lastIndexOf = pushHook.getRef().lastIndexOf(SEPARATOR);
        String branchName = pushHook.getRef().substring(lastIndexOf + 1);
        String repositoryName = pushHook.getRepository().getName();
        String title = userFullName + " pushed to branch '" + branchName + "' at repository '" + repositoryName + "'";
        String markdownText = title + "\n";
        for (Commit commit : pushHook.getCommits()) {
            String commitId = commit.getId().substring(0, 8);
            String commitMessage = commit.getMessage();
            // link to commit
            String commitUrl = commit.getUrl();
            markdownText += "> [" + commitId + "](" + commitUrl + "): " + commitMessage + "\n";
        }
        DingTalkMarkdownMessage markdownMessage = DingTalkMarkdownMessage.builder()
                .title(title)
                .text(markdownText)
                .atAll(false)
                .build();
        dingTalkRobotService.pushMarkdownMessage(markdownMessage);
    }

    /**
     * 订阅Gitlab的Merge Request事件，把Merge Request事件包装成钉钉markdown消息
     *
     * eg.
     *
     * {
     *   "msgtype": "markdown",
     *   "markdown": {
     *     "title": "xxx merged the merge request from branch 'develop' to 'master'",
     *     "text": "xxx merged the merge request from branch 'develop' to 'master'\n" +
     *             "> Title: [Finish new feature](http://gitlab.example.com/groupname/gitlabtest/merge_requests/1)\n" +
     *             "> Status: merged\n" +
     *             "> Repository: gitlabtest"
     *   },
     *   "at": {
     *     "atMobiles": [
     *       "156xxxx0907"
     *     ],
     *     "isAtAll": false
     *   }
     * }
     *
     * @param mergeRequestHook
     */
    @Subscribe
    public void onMergeRequestEvent(MergeRequestHook mergeRequestHook) {
        log.info("onMergeRequestEvent event_name: {}", mergeRequestHook.getObjectKind());
        log.info("onMergeRequestEvent content: {}", JSON.toJSONString(mergeRequestHook));
        // 把merge request事件封装成钉钉消息进行推送
        String userFullName = mergeRequestHook.getUser().getName();
        State mergeState = mergeRequestHook.getObjectAttributes().getState();
        String sourceBranch = mergeRequestHook.getObjectAttributes().getSourceBranch();
        String targetBranch = mergeRequestHook.getObjectAttributes().getTargetBranch();
        // merge request 发起人
        Integer authorId = mergeRequestHook.getObjectAttributes().getAuthorId();
        UserDO authorUser = userService.get(authorId);
        // merge request 被指派人
        Integer assigneeUserId = mergeRequestHook.getObjectAttributes().getAssigneeId();
        UserDO assigneeUser = userService.get(assigneeUserId);
        String title = userFullName + " " + mergeState.toString() + " the merge request from branch '" + sourceBranch + "' to '" + targetBranch + "'";
        String markdownText = title;
        List<String> atMobileList = null;
        if (State.opened.equals(mergeState) || State.reopened.equals(mergeState) || State.updated.equals(mergeState)) {
            if (assigneeUser != null && assigneeUser.getMobile() != null) {
                markdownText += " @" + assigneeUser.getMobile();
                atMobileList = Lists.newArrayList(assigneeUser.getMobile());
            }
        } else if (State.merged.equals(mergeState) || State.closed.equals(mergeState)) {
            if (authorUser != null && authorUser.getMobile() != null) {
                markdownText += " @" + authorUser.getMobile();
                atMobileList = Lists.newArrayList(authorUser.getMobile());
            }
        }
        markdownText += "\n";
        markdownText += "> Title: [" + mergeRequestHook.getObjectAttributes().getTitle() + "](" + mergeRequestHook.getObjectAttributes().getUrl() + ")\n\n";
        markdownText += "> Status: " + mergeState.toString() + "\n\n";
        markdownText += "> Repository: " + mergeRequestHook.getRepository().getName();
        DingTalkMarkdownMessage markdownMessage = DingTalkMarkdownMessage.builder()
                .title(title)
                .text(markdownText)
                .atMobiles(atMobileList)
                .atAll(false)
                .build();
        dingTalkRobotService.pushMarkdownMessage(markdownMessage);
    }
}
