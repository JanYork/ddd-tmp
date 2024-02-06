package net.zhaixing.blog.user.domain.aggregate.user.event;

import cn.hutool.core.lang.UUID;
import net.zhaixing.blog.user.domain.aggregate.user.model.User;
import net.zhaixing.blog.user.domain.share.event.BaseDomainEvent;
import net.zhaixing.blog.user.domain.share.event.DomainEventEnum;
import net.zhaixing.blog.user.domain.share.event.EventStatusEnum;

import java.time.LocalDateTime;

/**
 * 用户创建领域事件
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
public class UserCreateEvent extends BaseDomainEvent<User> {
    /**
     * 构造函数
     *
     * @param user 用户聚合根
     */
    public UserCreateEvent(User user) {
        super(String.valueOf(user.getId()),
                //仅做演示，领域事件id为防止重复建议自定义雪花id
                UUID.fastUUID().toString(),
                DomainEventEnum.USER_UPDATE,
                EventStatusEnum.PENDING,
                LocalDateTime.now(),
                user
        );
    }
}
