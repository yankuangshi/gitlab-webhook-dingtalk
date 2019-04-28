package com.kyan.gitlabdingtalk.event.handler;

import com.google.common.collect.Lists;
import com.google.common.eventbus.Subscribe;
import com.kyan.gitlabdingtalk.commons.annotation.EventBusListener;
import com.kyan.gitlabdingtalk.gitlab.hook.Commit;
import com.kyan.gitlabdingtalk.gitlab.hook.MergeRequestHook;
import com.kyan.gitlabdingtalk.gitlab.hook.PushHook;
import com.kyan.gitlabdingtalk.libs.dingtalk.DingTalkMarkdownMessage;
import com.kyan.gitlabdingtalk.model.UserDO;
import com.kyan.gitlabdingtalk.service.DingTalkRobotService;
import com.kyan.gitlabdingtalk.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
     *     "text": "> ![266468b2](http://gitlab.example.com/nasduck/gitlabtest/commit/266468b2d596fd95f0a1b4c9a984b568698ed6f6): Finish new feature\n " +
     *             "> ![fcf05237](http://gitlab.example.com/nasduck/gitlabtest/commit/fcf05237ac703b772c733cdefd5c14b78aab3925): Merge branch 'develop' into 'master'\n\nFinish new feature\n\nSee merge request nasduck/gitlabtest!1"
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
        log.info("onPushEvent: {}", pushHook.getObjectKind());
        // 把push事件封装成钉钉消息进行推送
        String userFullName = pushHook.getUserName();
        String markdownText = "";
        for (Commit commit : pushHook.getCommits()) {
            String commitId = commit.getId().substring(0, 8);
            String commitMessage = commit.getMessage();
            String commitUrl = commit.getUrl();
            markdownText += "> ![" + commitId + "](" + commitUrl + "): " + commitMessage;
        }
        int lastIndexOf = pushHook.getRef().lastIndexOf(SEPARATOR);
        String branchName = pushHook.getRef().substring(lastIndexOf + 1);
        String repositoryName = pushHook.getRepository().getName();
        DingTalkMarkdownMessage markdownMessage = DingTalkMarkdownMessage.builder()
                .title(userFullName + " pushed to branch [" + branchName + "]@[" + repositoryName + "]")
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
     *     "title": "xxx merged the merge request from branch [develop] to [master]",
     *     "text": "> Title: ![Finish new feature](http://gitlab.example.com/nasduck/gitlabtest/merge_requests/1)\n" +
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
        log.info("onMergeRequestEvent: {}", mergeRequestHook.getObjectKind());
        // 把merge request事件封装成钉钉消息进行推送
        String userFullName = mergeRequestHook.getUser().getName();
        String mergeAction = mergeRequestHook.getObjectAttributes().getState().toString();
        String sourceBranch = mergeRequestHook.getObjectAttributes().getSourceBranch();
        String targetBranch = mergeRequestHook.getObjectAttributes().getTargetBranch();
        Integer assigneeUserId = mergeRequestHook.getAssignee().getId();
        UserDO assigneeUser = userService.get(assigneeUserId);
        String atMobile = null;
        if (assigneeUser != null && assigneeUser.getMobile() != null) {
            atMobile = assigneeUser.getMobile();
        }
        String markdownText = "> Title: ![" + mergeRequestHook.getObjectAttributes().getTitle() + "](" + mergeRequestHook.getObjectAttributes().getUrl() + ")\n";
        markdownText += "> Status: " + mergeRequestHook.getObjectAttributes().getState().toString() + "\n";
        markdownText += "> Repository: " + mergeRequestHook.getRepository().getName();
        DingTalkMarkdownMessage markdownMessage = DingTalkMarkdownMessage.builder()
                .title(userFullName + " " + mergeAction + " the merge request from branch [" + sourceBranch + "] to [" + targetBranch + "]")
                .text(markdownText)
                .atMobiles(Lists.newArrayList(atMobile))
                .atAll(false)
                .build();
        dingTalkRobotService.pushMarkdownMessage(markdownMessage);
    }
}
