package net.zhaixing.blog.user.domain.share.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 领域事件业务类型枚举
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum DomainEventEnum {
    USER_CREATE("user_create", "用户删除事件"),
    USER_UPDATE("user_update", "用户修改事件"),
    USER_DELETE("user_delete", "用户删除事件");

    private final String key;

    private final String value;
}
