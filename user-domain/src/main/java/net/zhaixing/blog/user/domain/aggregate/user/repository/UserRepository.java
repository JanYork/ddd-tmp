package net.zhaixing.blog.user.domain.aggregate.user.repository;


import net.zhaixing.blog.user.common.domain.Repository;
import net.zhaixing.blog.user.domain.aggregate.user.model.User;

/**
 * 用户仓储接口
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
public interface UserRepository extends Repository<User, Long> {
    /**
     * 按照用户名查询用户
     *
     * @param userName 用户名
     * @return 用户聚合
     */
    User byUserName(String userName);
}
