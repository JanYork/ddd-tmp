package net.zhaixing.blog.user.infra.db.repository;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import jakarta.annotation.Resource;
import net.zhaixing.blog.user.domain.aggregate.role.model.Role;
import net.zhaixing.blog.user.domain.aggregate.role.repository.RoleRepository;
import net.zhaixing.blog.user.infra.db.converter.RoleConverter;
import net.zhaixing.blog.user.infra.db.mapper.RoleMapper;
import net.zhaixing.blog.user.infra.db.model.RolePO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 角色仓库接口的实现。
 * 提供了与数据库中角色数据交互的方法。
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
@Repository
public class RoleRepositoryImpl implements RoleRepository {
    @Resource
    private RoleMapper roleMapper; // 用于与数据库中的角色数据交互的Mapper

    @Override
    public void delete(Long id) {
        // 根据ID删除角色
        roleMapper.deleteById(id);
    }

    @Override
    public Role byId(Long id) {
        // 根据ID检索角色
        RolePO role = roleMapper.selectById(id);
        if (Objects.isNull(role)) {
            return null;
        }
        return RoleConverter.deserialize(role);
    }

    @Override
    public Role save(Role role) {
        // 保存角色信息
        RolePO rolePO = RoleConverter.serialize(role);
        if (Objects.isNull(role.getId())) {
            // 如果角色ID为空，则插入新角色
            roleMapper.insert(rolePO);
        } else {
            // 如果角色ID不为空，则更新现有角色
            roleMapper.updateById(rolePO);
        }
        return RoleConverter.deserialize(rolePO);
    }

    @Override
    public List<Role> listByIds(List<Long> ids) {
        // 根据ID列表获取角色列表
        List<RolePO> rolePOS = roleMapper.selectBatchIds(ids);
        if (CollectionUtils.isEmpty(rolePOS)) {
            return null;
        }
        return rolePOS.stream()
                .map(RoleConverter::deserialize)
                .collect(Collectors.toList());
    }
}
