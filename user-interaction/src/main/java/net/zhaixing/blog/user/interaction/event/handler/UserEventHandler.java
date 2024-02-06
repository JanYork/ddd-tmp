package net.zhaixing.blog.user.interaction.event.handler;

import lombok.extern.slf4j.Slf4j;
import net.zhaixing.blog.user.common.model.result.Result;
import net.zhaixing.blog.user.domain.aggregate.user.event.UserDeleteEvent;
import net.zhaixing.blog.user.domain.share.event.NeedSaveEventResult;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * 用户事件处理
 *
 * @author JanYork
 */
@Component
@Slf4j
public class UserEventHandler {

    @TransactionalEventListener(fallbackExecution = true)
    @NeedSaveEventResult
    public Result<Void> handleEvent(UserDeleteEvent event) {
        try {
            log.info("用户删除后，后续执行强相关的链式调用逻辑");
            return Result.ok();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

}
