package net.zhaixing.blog.user.common.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 验证异常
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class ValidationException extends ServiceException {

    private Object[] params;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Object[] params) {
        super(message);
        this.params = params;
    }

    public ValidationException(String code, String message, Object[] params) {
        super(code, message);
        this.params = params;
    }

    public static ValidationException of(String code, Object[] params) {
        return new ValidationException(code, null, params);
    }
}
