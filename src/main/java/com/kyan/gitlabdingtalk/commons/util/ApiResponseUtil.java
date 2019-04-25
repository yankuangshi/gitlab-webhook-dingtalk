package com.kyan.gitlabdingtalk.commons.util;

import lombok.Getter;
import lombok.Setter;

/**
 * API返回结果包装类
 *
 * @author kyan
 * @date 2019/4/25
 */
@Getter
@Setter
public class ApiResponseUtil<T> {

    private static final long serialVersionUID = 1L;

    private Integer code = 0;
    private String msg = "操作成功";
    private T data;

    public ApiResponseUtil() {}

    public static <T> ApiResponseUtil<T> ok() {
        ApiResponseUtil<T> apiResponseUtil = new ApiResponseUtil<>();
        return apiResponseUtil;
    }

    public static <T> ApiResponseUtil<T> ok(T data) {
        ApiResponseUtil<T> apiResponseUtil = new ApiResponseUtil<>();
        apiResponseUtil.data = data;
        return apiResponseUtil;
    }

    public static <T> ApiResponseUtil<T> error(Exception e) {
        ApiResponseUtil<T> apiResponseUtil = new ApiResponseUtil<>();
        apiResponseUtil.code = 1;
        apiResponseUtil.msg = e.getMessage();
        return apiResponseUtil;
    }

}