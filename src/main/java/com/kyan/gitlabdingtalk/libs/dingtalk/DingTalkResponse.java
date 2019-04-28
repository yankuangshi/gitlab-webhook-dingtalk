package com.kyan.gitlabdingtalk.libs.dingtalk;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author kyan
 * @date 2019/4/26
 */
@Data
public class DingTalkResponse<T> {

    @JsonProperty("errcode")
    private String errCode;
    @JsonProperty("errmsg")
    private String errMsg;
    private T data;
}
