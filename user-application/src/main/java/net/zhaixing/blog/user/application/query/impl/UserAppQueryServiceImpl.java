package net.zhaixing.blog.user.application.query.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Resource;
import net.zhaixing.blog.user.application.query.RoleAppQueryService;
import net.zhaixing.blog.user.application.query.UserAppQueryService;
import net.zhaixing.blog.user.application.query.model.role.dto.RoleDTO;
import net.zhaixing.blog.user.application.query.model.user.dto.UserDTO;
import net.zhaixing.blog.user.application.query.model.user.dto.UserPageVO;
import net.zhaixing.blog.user.common.model.query.KeywordQuery;
import net.zhaixing.blog.user.common.model.result.Page;
import net.zhaixing.blog.user.common.util.ValidationUtil;
import net.zhaixing.blog.user.domain.adapter.UnitAdapter;
import net.zhaixing.blog.user.domain.adapter.model.unit.UnitDTO;
import net.zhaixing.blog.user.infra.db.mapper.UserMapper;
import net.zhaixing.blog.user.infra.db.model.UserPO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户查询应用服务实现类。
 * 提供用户相关的查询功能，包括分页查询和获取用户详情。
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-03
 * @since 1.0.0
 */
@Service
public class UserAppQueryServiceImpl implements UserAppQueryService {
    @Resource
    private UserMapper userMapper; // 数据访问对象，用于与数据库进行交互
    @Resource
    private UnitAdapter unitAdapter; // 单位适配器，用于获取单位相关信息
    @Resource
    private RoleAppQueryService roleQueryApplicationService; // 角色查询服务，用于获取用户的角色信息

    /**
     * 分页查询用户信息。
     *
     * @param query 包含关键字的查询对象
     * @return 分页的用户信息
     */
    @Override
    public Page<UserPageVO> userPage(KeywordQuery query) {
        // 使用MyBatis Plus分页查询用户信息
        Page<UserPO> pos = userMapper.userPage(query);
        // 将UserPO转换为UserPageDTO
        return pos.convert(po -> {
            UserPageVO dto = new UserPageVO();
            // 初始化用户DTO信息
            this.initUserDTO(dto, po);
            // 将用户的角色ID字符串转换为角色ID列表
            List<Long> roleIds = Arrays.stream(po.getRoleIds().split(",")).map(Long::valueOf).collect(Collectors.toList());
            // 获取角色信息并设置到DTO中
            List<RoleDTO> roles = roleQueryApplicationService.list(roleIds);
            dto.setRoles(roles);
            return dto;
        });
    }

    /**
     * 获取用户详细信息。
     *
     * @param userName 用户名
     * @return 用户详细信息DTO
     */
    @Override
    public UserDTO detail(String userName) {
        // 根据用户名查询用户信息
        UserPO po = userMapper.selectOne(Wrappers.<UserPO>lambdaQuery().eq(UserPO::getUserName, userName));
        // 如果用户不存在，返回null
        if (Objects.isNull(po)) {
            return null;
        }
        UserDTO dto = new UserDTO();
        // 初始化用户DTO信息
        this.initUserDTO(dto, po);
        return dto;
    }

    /**
     * 初始化用户DTO信息。
     * 将UserPO的属性复制到UserDTO，并且获取并设置单位名称。
     *
     * @param dto 用户DTO对象
     * @param po 用户PO对象
     */
    private <T extends UserDTO> void initUserDTO(T dto, UserPO po) {
        // 使用Spring的BeanUtils进行属性复制
        BeanUtils.copyProperties(po, dto);
        // 获取单位信息并设置单位名称
        UnitDTO unit = unitAdapter.byUnitId(po.getUnitId());
        ValidationUtil.isTrue(Objects.nonNull(unit), "unit.is.not.exist");
        dto.setUnitName(unit.getUnitName());
    }
}
