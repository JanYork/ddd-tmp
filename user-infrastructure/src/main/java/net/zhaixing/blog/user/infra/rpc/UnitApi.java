package net.zhaixing.blog.user.infra.rpc;

/**
 * 单位rpc接口
 * <p>
 * 此处对应为调用其他微服务接口，为演示方便，此处采用默认返回
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
public interface UnitApi {
    /**
     * 根据单位id获取单位信息
     *
     * @param unitId 单位id
     * @return {@link UnitInfoDTO} 单位信息
     */
    default UnitInfoDTO getByUnitId(Long unitId) {
        return new UnitInfoDTO(12345L, "XX单位", "浙江省杭州市XXX区XXX街道XXX号");
    }
}
