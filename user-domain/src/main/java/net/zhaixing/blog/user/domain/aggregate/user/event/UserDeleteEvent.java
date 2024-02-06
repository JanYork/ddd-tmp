package net.zhaixing.blog.user.domain.aggregate.user.event;

import cn.hutool.core.lang.UUID;
import net.zhaixing.blog.user.domain.share.event.BaseDomainEvent;
import net.zhaixing.blog.user.domain.share.event.DomainEventEnum;
import net.zhaixing.blog.user.domain.share.event.EventStatusEnum;

import java.time.LocalDateTime;

/**
 * 用户删除领域事件
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
public class UserDeleteEvent extends BaseDomainEvent<Long> {
    /**
     * 构造函数
     *
     * @param id 用户id
     */
    public UserDeleteEvent(Long id) {
        super(String.valueOf(id),
                //仅做演示，领域事件id为防止重复建议自定义雪花id
                UUID.fastUUID().toString(),
                DomainEventEnum.USER_DELETE,
                EventStatusEnum.PENDING,
                LocalDateTime.now(),
                id
        );
    }
}
