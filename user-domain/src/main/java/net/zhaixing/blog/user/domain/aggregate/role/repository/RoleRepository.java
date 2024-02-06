package net.zhaixing.blog.user.domain.aggregate.role.repository;

import net.zhaixing.blog.user.common.domain.Repository;
import net.zhaixing.blog.user.domain.aggregate.role.model.Role;

import java.util.List;

/**
 * 角色仓储接口
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
public interface RoleRepository extends Repository<Role, Long> {
    /**
     * 根据id列表获取角色列表
     *
     * @param ids id列表
     * @return {@link List}<{@link Role}>   角色列表
     */
    List<Role> listByIds(List<Long> ids);
}
