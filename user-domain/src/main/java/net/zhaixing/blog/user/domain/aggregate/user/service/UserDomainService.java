package net.zhaixing.blog.user.domain.aggregate.user.service;

import net.zhaixing.blog.user.common.domain.DomainService;
import net.zhaixing.blog.user.domain.aggregate.role.model.Role;
import net.zhaixing.blog.user.domain.aggregate.user.model.User;

import java.util.List;


/**
 * 用户领域服务
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
public interface UserDomainService extends DomainService {
    /**
     * 需要用户角色两个聚合完成用户聚合的原子化逻辑
     * <p>
     * 根据用户关联的角色打印出标签
     *
     * @param user  用户聚合
     * @param roles 角色聚合
     */
    void printTag(User user, List<Role> roles);
}
