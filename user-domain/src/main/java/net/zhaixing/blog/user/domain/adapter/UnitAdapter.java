package net.zhaixing.blog.user.domain.adapter;


import net.zhaixing.blog.user.common.domain.Adapter;
import net.zhaixing.blog.user.domain.adapter.model.unit.UnitDTO;

/**
 * 用户所在单位适配器
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
public interface UnitAdapter extends Adapter {
    /**
     * 根据单位id获取单位信息
     *
     * @param unitId 单位id
     * @return 单位信息
     */
    UnitDTO byUnitId(Long unitId);
}
