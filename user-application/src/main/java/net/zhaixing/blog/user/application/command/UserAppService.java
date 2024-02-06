package net.zhaixing.blog.user.application.command;


import net.zhaixing.blog.user.application.ability.user.cmd.CreateUserAbilityCommand;
import net.zhaixing.blog.user.application.command.user.UpdateUserCommand;
import net.zhaixing.blog.user.common.domain.AppService;

/**
 * 用户应用服务
 * <p>
 * 业务逻辑编排，仅对业务用例做方法的编排
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-02
 * @since 1.0.0
 */
public interface UserAppService extends AppService {
    /**
     * 新建用户
     *
     * @param command command
     */
    void create(CreateUserAbilityCommand command);

    /**
     * 修改用户用户名
     *
     * @param command command
     */
    void updateUserName(UpdateUserCommand command);

    /**
     * 删除用户
     *
     * @param id id
     */
    void delete(Long id);
}
