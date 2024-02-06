package net.zhaixing.blog.user.infra.db.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import net.zhaixing.blog.user.common.model.result.BaseModel;

/**
 * 角色表
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("t_role")
public class RolePO extends BaseModel {

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色code
     */
    private String code;
}
