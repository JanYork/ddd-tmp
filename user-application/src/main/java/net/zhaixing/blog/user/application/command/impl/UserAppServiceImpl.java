package net.zhaixing.blog.user.application.command.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.zhaixing.blog.user.application.ability.user.UserCreateAbility;
import net.zhaixing.blog.user.application.ability.user.cmd.CreateUserAbilityCommand;
import net.zhaixing.blog.user.application.command.UserAppService;
import net.zhaixing.blog.user.application.command.user.UpdateUserCommand;
import net.zhaixing.blog.user.common.util.ValidationUtil;
import net.zhaixing.blog.user.domain.aggregate.user.event.UserDeleteEvent;
import net.zhaixing.blog.user.domain.aggregate.user.event.UserUpdateEvent;
import net.zhaixing.blog.user.domain.aggregate.user.model.User;
import net.zhaixing.blog.user.domain.aggregate.user.repository.UserRepository;
import net.zhaixing.blog.user.domain.share.event.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * 用户应用服务实现
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-02
 * @since 1.0.0
 */
@Slf4j
@Service
public class UserAppServiceImpl implements UserAppService {
    @Resource
    UserCreateAbility userCreateAbility;
    @Resource
    UserRepository userRepository;
    @Resource
    DomainEventPublisher domainEventPublisher;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(CreateUserAbilityCommand command) {
        // 执行创建用户的能力
        userCreateAbility.executeAbility(command);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserName(UpdateUserCommand command) {
        //【应用服务仅允许此种判断，抛出错误情况，即为参数校验，不允许实际业务逻辑处理】
        // 前置校验逻辑很长或者通用率高的情况，可以考虑抽取一个UserValidationUtil统一管理前置的业务校验

        //先校验用户是否存在
        User user = userRepository.byId(command.getUserId());
        ValidationUtil.isTrue(Objects.nonNull(user), "user.is.not.exist");

        //修改用户名
        User existUser = userRepository.byUserName(command.getUserName());
        user.bindUserName(command.getUserName(), existUser);

        //执行用户修改相关业务逻辑
        user.printUpdate();

        //存储用户
        User save = userRepository.save(user);

        //发布用户修改的领域事件
        domainEventPublisher.publish(new UserUpdateEvent(save));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        ValidationUtil.isTrue(Objects.nonNull(userRepository.byId(id)), "user.is.not.exist");
        //根据用户id删除用户聚合
        userRepository.delete(id);
        //发布用户删除领域事件
        domainEventPublisher.publishAndSave(new UserDeleteEvent(id));
    }
}
