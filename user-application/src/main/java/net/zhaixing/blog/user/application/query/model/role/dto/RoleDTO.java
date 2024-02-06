package net.zhaixing.blog.user.application.query.model.role.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.zhaixing.blog.user.common.domain.ResultModel;

/**
 * 角色信息
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-03
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO implements ResultModel {
    /**
     * 角色id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /**
     * 角色code
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;
}
