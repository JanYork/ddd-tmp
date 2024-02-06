package net.zhaixing.blog.user.infra.adapter;

import net.zhaixing.blog.user.domain.adapter.UnitAdapter;
import net.zhaixing.blog.user.domain.adapter.model.unit.UnitDTO;
import org.springframework.stereotype.Component;

/**
 * 用户单位适配器实现
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
@Component
class UnitAdapterImpl implements UnitAdapter {

/*
    正常应该注入调用，因为没有单位服务(RPC/领域)，所以用静态方法演示
    @Autowired
    private UnitApi unitApi;
*/

    /**
     * 根据单位id获取单位信息
     *
     * @param unitId 单位id
     * @return {@link UnitDTO}
     */
    @Override
    public UnitDTO byUnitId(Long unitId) {
//        UnitInfoDTO byUnitId = UnitApi.getByUnitId(unitId);
//        ValidationUtil.isTrue(Objects.nonNull(byUnitId),"unit.is.not.exist");
//        UnitDTO unitDTO = new UnitDTO();
//        BeanUtils.copyProperties(byUnitId,unitDTO);
        return new UnitDTO(10000L, "XX单位");
    }
}
