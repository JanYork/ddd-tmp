package net.zhaixing.blog.user.common.model.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 返回结果
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Result<T> extends BaseResult {

    private T data;

    // 构造函数
    public Result(Integer code, String message, T data) {
        super(code, message);
        this.data = data;
    }

    public Result(Integer code, String errorCode, String message, T data) {
        super(code, errorCode, message);
        this.data = data;
    }

    // 成功状态判断
    public boolean success() {
        return CODE_SUCCESS.equals(getCode());
    }

    // 系统错误状态判断
    public boolean systemFail() {
        return CODE_SYSTEM_ERROR.equals(getCode());
    }

    // 成功，无数据
    public static Result<Void> ok() {
        return new Result<>(CODE_SUCCESS, "", null);
    }

    // 成功，无数据，自定义消息
    public static Result<Void> ok(String message) {
        return new Result<>(CODE_SUCCESS, message, null);
    }

    // 成功，有数据
    public static <T> Result<T> success(T data) {
        return new Result<>(CODE_SUCCESS, MESSAGE_SUCCESS, data);
    }

    // 成功，有数据，自定义消息
    public static <T> Result<T> success(T data, String message) {
        return new Result<>(CODE_SUCCESS, message, data);
    }

    // 错误，无数据
    public static Result<Void> error(String message) {
        return Result.error(CODE_SYSTEM_ERROR, null, message, null);
    }

    // 错误，无数据，自定义错误码
    public static Result<Void> error(String errorCode, String message) {
        return Result.error(CODE_SYSTEM_ERROR, errorCode, message, null);
    }

    // 错误，可包含数据
    public static <T> Result<T> error(Integer code, String errorCode, String message, T data) {
        return new Result<>(code, errorCode, message, data);
    }
}
