package net.zhaixing.blog.user.infra.event;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.zhaixing.blog.user.common.util.GsonUtil;
import net.zhaixing.blog.user.domain.share.event.BaseDomainEvent;
import net.zhaixing.blog.user.domain.share.event.DomainEventPublisher;
import net.zhaixing.blog.user.domain.share.event.DomainEventRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * 领域事件发布实现类。
 * 实现了领域事件发布接口，用于管理和发布领域事件。
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
@Component
@Slf4j
public class DomainEventPublisherImpl implements DomainEventPublisher {
    @Resource
    private ApplicationEventPublisher applicationEventPublisher; // Spring的应用事件发布器
    @Resource
    private DomainEventRepository domainEventRepository; // 领域事件仓库

    @Override
    public <EVENT extends BaseDomainEvent<?>> void publish(EVENT event) {
        // 发布事件
        log.info("发布事件,event:{}", GsonUtil.gsonToString(event));
        // 使用Spring的事件发布器发布事件
        applicationEventPublisher.publishEvent(event);
    }

    @Override
    public <EVENT extends BaseDomainEvent<?>> void publishAndSave(EVENT event) {
        // 保存并发布事件
        log.info("保存并发布事件,event:{}", GsonUtil.gsonToString(event));

        // 从领域事件仓库中加载与事件相同领域ID的所有事件
        List<BaseDomainEvent<?>> baseDomainEventList = domainEventRepository.loadByDomainId(event.getDomainId());
        if (CollectionUtils.isNotEmpty(baseDomainEventList)) {
            // 检查是否已存在相同ID的事件
            boolean anyMatch = baseDomainEventList.stream().anyMatch(e -> Objects.equals(e.getId(), event.getId()));
            if (anyMatch) {
                // 如果存在，仅发布事件
                applicationEventPublisher.publishEvent(event);
                return;
            }
        }
        // 保存事件到仓库并发布
        domainEventRepository.save(event);
        applicationEventPublisher.publishEvent(event);
    }
}
