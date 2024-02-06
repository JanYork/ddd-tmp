package net.zhaixing.blog.user.domain.share.event;

/**
 * 领域事件发布接口
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
public interface DomainEventPublisher {
    /**
     * 发布事件
     *
     * @param event event
     */
    <EVENT extends BaseDomainEvent<?>> void publish(EVENT event);

    /**
     * 发布事件并保存
     *
     * @param event event
     */
    <EVENT extends BaseDomainEvent<?>> void publishAndSave(EVENT event);
}
