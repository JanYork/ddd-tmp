package net.zhaixing.blog.user.common.util;


import net.zhaixing.blog.user.common.exception.ValidationException;

/**
 * 验证工具类
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
public class ValidationUtil {
    /**
     * @param expect 期望值
     * @param code   错误码
     * @param params 错误码参数
     */
    public static void isTrue(boolean expect, String code, Object... params) {
        if (!expect) {
            throw ValidationException.of(code, params);
        }
    }

    /**
     * @param expect 期望值
     * @param code   错误码
     * @param params 错误码参数
     */
    public static void isFalse(boolean expect, String code, Object... params) {
        isTrue(!expect, code, params);
    }
}
