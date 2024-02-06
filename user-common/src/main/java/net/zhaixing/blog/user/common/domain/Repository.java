package net.zhaixing.blog.user.common.domain;

import java.io.Serializable;

/**
 * 仓库接口基类
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
public interface Repository<AGGREGATE, ID extends Serializable> {
    /**
     * 删除
     *
     * @param id 主键
     */
    void delete(ID id);

    /**
     * 按id查找
     *
     * @param id 主键
     * @return 聚合根
     */
    AGGREGATE byId(ID id);

    /**
     * 保存或更新聚合根
     *
     * @param aggregate 聚合根
     * @return 聚合根
     */
    AGGREGATE save(AGGREGATE aggregate);


    /**
     * 保存或更新聚合根【直接刷表】
     *
     * @param aggregate 聚合根
     * @return 聚合根
     */
    default AGGREGATE saveAndFlush(AGGREGATE aggregate) {
        return aggregate;
    }
}
