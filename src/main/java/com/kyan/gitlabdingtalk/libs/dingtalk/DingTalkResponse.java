package com.kyan.gitlabdingtalk.libs.dingtalk;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author kyan
 * @date 2019/4/26
 */
@Data
public class DingTalkResponse<T> {

    @JSONField(name = "errmsg")
    private String errMsg;
    @JSONField(name = "errcode")
    private String errCode;
    private T data;
}
