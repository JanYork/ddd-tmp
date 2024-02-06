package net.zhaixing.blog.user.domain.adapter.model.unit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户单位信息传输对象
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitDTO {
    /**
     * 单位id
     */
    private Long id;

    /**
     * 单位名称
     */
    private String unitName;
}
