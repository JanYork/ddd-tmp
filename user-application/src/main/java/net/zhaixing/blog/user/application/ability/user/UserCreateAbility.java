package net.zhaixing.blog.user.application.ability.user;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import jakarta.annotation.Resource;
import net.zhaixing.blog.user.application.ability.share.AbilityContext;
import net.zhaixing.blog.user.application.ability.share.BaseAbility;
import net.zhaixing.blog.user.application.ability.user.cmd.CreateUserAbilityCommand;
import net.zhaixing.blog.user.common.model.result.Result;
import net.zhaixing.blog.user.common.util.ValidationUtil;
import net.zhaixing.blog.user.domain.aggregate.role.model.Role;
import net.zhaixing.blog.user.domain.aggregate.role.repository.RoleRepository;
import net.zhaixing.blog.user.domain.aggregate.user.event.UserCreateEvent;
import net.zhaixing.blog.user.domain.aggregate.user.model.User;
import net.zhaixing.blog.user.domain.aggregate.user.repository.UserRepository;
import net.zhaixing.blog.user.domain.aggregate.user.service.UserDomainService;
import net.zhaixing.blog.user.domain.share.event.DomainEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 创建用户能力类。
 * 这个类实现了用户创建的相关业务逻辑。
 * 继承自BaseAbility，处理CreateUserAbilityCommand命令，返回Result<Void>类型的结果。
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-02
 * @since 1.0.0
 */
@Service
public class UserCreateAbility extends BaseAbility<CreateUserAbilityCommand, Void> {
    @Resource
    RoleRepository roleRepository;
    @Resource
    UserRepository userRepository;
    @Resource
    DomainEventPublisher domainEventPublisher;
    @Resource
    UserDomainService userDomainService;

    private final static String ROLE_INFO_KEY = "roleInfo";

    @Override
    public void checkHandler(CreateUserAbilityCommand command) {
        // 校验用户名是否已存在
        ValidationUtil.isTrue(Objects.isNull(userRepository.byUserName(command.getUserName())), "user.user.name.is.exist");

        // 校验指定的角色是否存在
        List<Role> roles = roleRepository.listByIds(command.getRoles());
        ValidationUtil.isTrue(CollectionUtils.isNotEmpty(roles) &&
                        Objects.equals(roles.size(), command.getRoles().size()),
                "user.role.is.not.exist");

        // 将角色信息存储在上下文中供后续使用
        AbilityContext.putValue(ROLE_INFO_KEY, roles);
    }

    @Override
    public Result<Void> checkIdempotent(CreateUserAbilityCommand command) {
        // 在这里进行幂等性检查
        // 确保同一操作不会被重复执行
        return Result.success(null);
    }

    @Override
    public Result<Void> execute(CreateUserAbilityCommand command) {
        // 使用工厂方法创建用户实例
        User user = command.toUser();

        // 执行用户创建的业务逻辑
        user.printCreate();

        // 使用领域服务处理用户标签，演示领域服务的用途
        List<Role> roles = AbilityContext.getValue(ROLE_INFO_KEY);
        userDomainService.printTag(user, roles);

        // 将用户信息存储到仓库
        User savedUser = userRepository.save(user);

        // 发布用户创建事件
        domainEventPublisher.publish(new UserCreateEvent(savedUser));

        return Result.success(null);
    }
}
