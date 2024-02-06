package net.zhaixing.blog.user.infra.db.repository;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Resource;
import net.zhaixing.blog.user.domain.aggregate.user.model.User;
import net.zhaixing.blog.user.domain.aggregate.user.repository.UserRepository;
import net.zhaixing.blog.user.infra.db.converter.UserConverter;
import net.zhaixing.blog.user.infra.db.mapper.UserMapper;
import net.zhaixing.blog.user.infra.db.model.UserPO;
import org.springframework.stereotype.Repository;

import java.util.Objects;

/**
 * 用户信息仓库实现类。
 * 实现了用户仓库接口，提供与数据库中用户数据的交互。
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    @Resource
    private UserMapper userMapper; // 用户数据的Mapper，用于数据库交互

    @Override
    public void delete(Long id) {
        // 根据用户ID删除用户
        userMapper.deleteById(id);
    }

    @Override
    public User byId(Long id) {
        // 根据用户ID查询用户
        UserPO user = userMapper.selectById(id);
        if (Objects.isNull(user)) {
            // 如果未找到用户，返回null
            return null;
        }
        // 将数据库对象转换为领域模型对象并返回
        return UserConverter.deserialize(user);
    }

    @Override
    public User byUserName(String userName) {
        // 根据用户名查询用户
        UserPO user = userMapper.selectOne(Wrappers.<UserPO>lambdaQuery().eq(UserPO::getUserName, userName));
        if (Objects.isNull(user)) {
            // 如果未找到用户，返回null
            return null;
        }
        // 将数据库对象转换为领域模型对象并返回
        return UserConverter.deserialize(user);
    }

    @Override
    public User save(User user) {
        // 保存用户信息
        UserPO userPo = UserConverter.serialize(user);
        if (Objects.isNull(user.getId())) {
            // 如果用户ID为空，执行插入操作
            userMapper.insert(userPo);
        } else {
            // 如果用户ID非空，执行更新操作
            userMapper.updateById(userPo);
        }
        // 将更新后的数据库对象转换回领域模型对象并返回
        return UserConverter.deserialize(userPo);
    }
}
