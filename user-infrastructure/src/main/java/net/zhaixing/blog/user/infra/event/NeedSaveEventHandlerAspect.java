package net.zhaixing.blog.user.infra.event;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.zhaixing.blog.user.common.model.result.BaseResult;
import net.zhaixing.blog.user.common.model.result.Result;
import net.zhaixing.blog.user.domain.share.event.BaseDomainEvent;
import net.zhaixing.blog.user.domain.share.event.DomainEventRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 领域事件处理的切面。
 * 用于处理标记有@NeedSaveEventResult注解的方法，以在方法返回后对领域事件进行后续处理。
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
@Aspect
@Component
@Slf4j
public class NeedSaveEventHandlerAspect {
    @Resource
    private DomainEventRepository domainEventRepository; // 领域事件仓库，用于事件的存储和更新

    @Pointcut("@annotation(net.zhaixing.blog.user.domain.share.event.NeedSaveEventResult)")
    public void pointcut() {
        // 切点定义，针对@NeedSaveEventResult注解的方法
    }

    @AfterReturning(value = "pointcut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Result<?> result) {
        // 方法返回后的处理逻辑
        // 获取切点方法的第一个参数，预期为BaseDomainEvent类型
        BaseDomainEvent<?> baseEvent = (BaseDomainEvent<?>) joinPoint.getArgs()[0];

        // 根据方法返回的结果更新事件状态
        if (Objects.equals(result.getCode(), BaseResult.CODE_SUCCESS)) {
            // 如果返回结果表示成功，更新事件状态为成功
            log.info("更新事件{}状态为成功", baseEvent.getId());
            baseEvent.handleSuccess();
        } else {
            // 如果返回结果表示失败，更新事件状态为失败
            log.info("更新事件{}状态为失败", baseEvent.getId());
            baseEvent.handleFailed();
        }
        // 更新事件在仓库中的状态
        domainEventRepository.update(baseEvent);
    }
}
