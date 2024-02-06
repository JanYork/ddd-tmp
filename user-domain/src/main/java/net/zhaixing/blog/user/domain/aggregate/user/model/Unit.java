package net.zhaixing.blog.user.domain.aggregate.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.zhaixing.blog.user.common.domain.Entity;

/**
 * 单位实体
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
public class Unit implements Entity {
    /**
     * 单位id
     */
    private Long id;
}
