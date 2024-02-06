package net.zhaixing.blog.user.application.ability.share;

import net.zhaixing.blog.user.common.domain.Ability;
import net.zhaixing.blog.user.common.model.result.BaseResult;
import net.zhaixing.blog.user.common.model.result.Result;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * 基础能力抽象类。
 * 这个类是所有能力类的基类，定义了执行能力点的基本流程和一些通用方法。
 * 它使用泛型T和R来表示输入命令和返回结果的类型。
 * 任何继承此类的子类都必须实现其抽象方法。
 *
 * @param <T> 能力命令的类型
 * @param <R> 能力执行后的返回结果类型
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-02
 * @since 1.0.0
 */
@Component
public abstract class BaseAbility<T, R> implements Ability {

    /**
     * 执行能力点。
     * 这个方法包括初始化上下文、参数校验、幂等性校验和执行业务逻辑几个主要步骤。
     * 这是一个事务性操作，如果过程中发生异常，所有操作将回滚。
     *
     * @param abilityCmd 能力命令，它是执行此能力所需的输入数据。
     * @return {@link Result}<{@link R}> 包含执行结果的对象，包括成功或失败的状态和数据。
     */
    @Transactional(rollbackFor = Exception.class)
    public Result<R> executeAbility(T abilityCmd) {
        try {
            // 初始化上下文，为当前线程设置一个独立的数据存储区域。
            AbilityContext.initContext();

            // 参数校验，确保输入的能力命令符合预期。
            checkHandler(abilityCmd);

            // 幂等性校验，防止相同的操作被重复执行。
            Result<R> checkIdempotent = checkIdempotent(abilityCmd);
            if (Objects.isNull(checkIdempotent) || !Objects.equals(checkIdempotent.getCode(), BaseResult.CODE_SUCCESS)) {
                return checkIdempotent;
            }

            // 执行实际的业务逻辑。
            return execute(abilityCmd);
        } finally {
            // 清理上下文，移除当前线程的数据存储区域。
            AbilityContext.clearContext();
        }
    }

    /**
     * 参数校验方法。
     * 子类应该实现这个方法来校验输入的能力命令是否有效。
     *
     * @param abilityCmd 能力命令
     */
    public abstract void checkHandler(T abilityCmd);

    /**
     * 幂等性校验。
     * 这个方法用于检查是否已经执行过相同的操作，以避免重复执行。
     *
     * @param abilityCmd 能力命令
     * @return {@link Result}<{@link R}> 包含幂等性校验结果的对象。
     */
    public abstract Result<R> checkIdempotent(T abilityCmd);

    /**
     * 执行能力业务的方法。
     * 这是具体的业务逻辑实现，子类应根据实际需求实现此方法。
     *
     * @param abilityCmd 能力命令
     * @return {@link Result}<{@link R}> 包含业务执行结果的对象。
     */
    public abstract Result<R> execute(T abilityCmd);
}
