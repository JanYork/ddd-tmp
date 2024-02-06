package net.zhaixing.blog.user.application.query.model.user.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.zhaixing.blog.user.application.query.model.role.dto.RoleDTO;

import java.util.List;

/**
 * 用户列表页展示实体
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-02
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPageVO extends UserDTO {
    /**
     * 角色信息
     */
    List<RoleDTO> roles;
}
