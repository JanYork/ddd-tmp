package net.zhaixing.blog.user.domain.aggregate.role.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import net.zhaixing.blog.user.common.domain.AggregateRoot;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 角色聚合根
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@Builder
public class Role implements AggregateRoot {
    /**
     * 角色id
     */
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色code
     */
    private String code;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;


    /**
     * 是否是管理员
     *
     * @return {@link Boolean}  是否是管理员
     */
    public Boolean isAdmin() {
        // todo something
        return Objects.equals(this.code, "admin");
    }
}
