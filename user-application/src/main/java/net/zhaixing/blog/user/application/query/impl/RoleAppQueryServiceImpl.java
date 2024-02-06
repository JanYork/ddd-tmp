package net.zhaixing.blog.user.application.query.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import jakarta.annotation.Resource;
import net.zhaixing.blog.user.application.query.RoleAppQueryService;
import net.zhaixing.blog.user.application.query.model.role.dto.RoleDTO;
import net.zhaixing.blog.user.infra.db.mapper.RoleMapper;
import net.zhaixing.blog.user.infra.db.model.RolePO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色查询应用服务实现类
 * 实现了 RoleAppQueryService 接口，用于提供角色相关的查询服务。
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-02
 * @since 1.0.0
 */
@Repository
public class RoleAppQueryServiceImpl implements RoleAppQueryService {
    @Resource
    private RoleMapper roleMapper; // MyBatis Plus mapper 用于数据库操作

    /**
     * 根据角色ID列表查询角色。
     *
     * @param roleIds 角色ID列表
     * @return {@link List}<{@link RoleDTO}> 角色列表
     */
    @Override
    public List<RoleDTO> list(List<Long> roleIds) {
        // 检查输入的角色ID列表是否为空
        if (CollectionUtils.isEmpty(roleIds)) {
            return null; // 如果列表为空，则直接返回null
        }

        // 使用 MyBatis Plus 的批量查询方法根据ID列表查询角色
        List<RolePO> pos = roleMapper.selectBatchIds(roleIds);
        if (CollectionUtils.isEmpty(pos)) {
            return null; // 如果查询结果为空，则直接返回null
        }

        // 将查询到的 RolePO 对象列表转换成 RoleVO 对象列表
        // 使用 Java 8 的流和 lambda 表达式进行转换
        return pos.stream().map(po -> {
            RoleDTO dto = new RoleDTO();
            BeanUtils.copyProperties(po, dto); // 使用 Spring 的 BeanUtils 进行属性拷贝
            return dto;
        }).collect(Collectors.toList());
    }
}
