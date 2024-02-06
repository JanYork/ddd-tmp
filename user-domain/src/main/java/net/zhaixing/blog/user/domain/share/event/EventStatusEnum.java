package net.zhaixing.blog.user.domain.share.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 领域事件状态枚举
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum EventStatusEnum {

    PENDING(0, "等待处理"),
    SUCCESS(1, "处理成功"),
    FAILED(2, "处理失败");

    private final Integer code;
    private final String desc;
}
