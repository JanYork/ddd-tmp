package net.zhaixing.blog.user.interaction.api;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import net.zhaixing.blog.user.application.ability.user.cmd.CreateUserAbilityCommand;
import net.zhaixing.blog.user.application.command.UserAppService;
import net.zhaixing.blog.user.application.command.user.UpdateUserCommand;
import net.zhaixing.blog.user.application.query.UserAppQueryService;
import net.zhaixing.blog.user.application.query.model.user.dto.UserPageVO;
import net.zhaixing.blog.user.common.model.query.KeywordQuery;
import net.zhaixing.blog.user.common.model.result.BaseResult;
import net.zhaixing.blog.user.common.model.result.Page;
import net.zhaixing.blog.user.common.model.result.PageResult;
import net.zhaixing.blog.user.common.model.result.Result;
import org.springframework.web.bind.annotation.*;

/**
 * 用户接口控制器。
 * 提供用户相关的RESTful API接口。
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-04
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/blog/")
public class UserApi {
    @Resource
    UserAppService userApplicationService; // 用户应用服务，用于执行用户相关操作

    @Resource
    UserAppQueryService userQueryApplicationService; // 用户查询应用服务，用于查询用户数据

    @PostMapping("user")
    public Result<Void> create(@RequestBody @Valid CreateUserAbilityCommand command){
        // 创建用户接口
        // 接收并校验CreateUserAbilityCommand对象，然后调用应用服务创建用户
        userApplicationService.create(command);
        // 返回成功结果
        return Result.ok(BaseResult.INSERT_SUCCESS);
    }

    @PutMapping("user")
    public Result<Void> updateUserName(@RequestBody @Valid UpdateUserCommand command){
        // 更新用户名称接口
        // 接收并校验UpdateUserCommand对象，然后调用应用服务更新用户名称
        userApplicationService.updateUserName(command);
        // 返回更新成功结果
        return Result.ok(BaseResult.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id){
        // 删除用户接口
        // 根据ID调用应用服务删除用户
        userApplicationService.delete(id);
        // 返回删除成功结果
        return Result.ok(BaseResult.DELETE_SUCCESS);
    }

    @GetMapping("user")
    public PageResult<UserPageVO> query(KeywordQuery query){
        // 查询用户接口
        // 根据关键词查询条件调用查询应用服务获取用户分页信息
        Page<UserPageVO> users = userQueryApplicationService.userPage(query);
        // 返回包含用户分页信息的结果
        return PageResult.ok(users);
    }
}
