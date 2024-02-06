package net.zhaixing.blog.user.infra.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 单位信息(三方服务的实体)
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
public class UnitInfoDTO {
    /**
     * 单位id
     */
    private Long id;
    /**
     * 单位名称
     */
    private String unitName;
    /**
     * 单位地址
     */
    private String position;
}
