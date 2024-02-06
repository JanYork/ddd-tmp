package net.zhaixing.blog.user.common.model.query;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.zhaixing.blog.user.common.model.result.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页查询对象
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-03
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageQuery<T> extends Page<T> {

    @Getter
    private Long offset = -1L; // 查询的偏移量，-1表示未设置

    private Long limit = 10L; // 分页的大小，默认为10

    private String sort = "desc"; // 排序方式，默认为降序

    private String order; // 排序字段

    /**
     * 设置查询的偏移量。
     *
     * @param offset 偏移量值
     */
    @Override
    public void setOffset(Long offset) {
        this.offset = offset;
        super.setOffset(offset); // 同时更新基类的偏移量
    }

    /**
     * 获取分页大小。
     *
     * @return 分页大小
     */
    public Long getLimit() {
        return getSize();
    }

    /**
     * 设置分页大小。
     *
     * @param limit 分页大小
     */
    @Override
    public void setLimit(Long limit) {
        this.limit = limit;
        setSize(limit); // 同时更新基类的分页大小
    }

    /**
     * 获取当前页码。
     *
     * @return 当前页码
     */
    public Long getPageNo() {
        return getCurrent();
    }

    /**
     * 设置当前页码。
     *
     * @param pageNum 页码
     */
    public void setPageNo(Long pageNum) {
        setCurrent(pageNum);
    }

    /**
     * 获取分页大小。
     *
     * @return 分页大小
     */
    public Long getPageSize() {
        return getSize();
    }

    /**
     * 设置分页大小。
     *
     * @param pageSize 分页大小
     */
    public void setPageSize(Long pageSize) {
        setSize(pageSize);
    }

    /**
     * 计算分页的偏移量。
     *
     * @return 分页的偏移量
     */
    @Override
    public long offset() {
        return getOffset() >= 0 ? getOffset() : (getCurrent() - 1) * getSize();
    }

    /**
     * 获取排序字段。
     *
     * @return 排序字段
     */
    public String getOrder() {
        return StrUtil.isBlank(order) ? null : order;
    }

    /**
     * 获取排序方式。
     *
     * @return 排序方式
     */
    public String getSort() {
        return "asc".equals(sort) ? "asc" : "desc";
    }

    /**
     * 添加排序项到查询。
     * 根据排序字段和排序方式构造OrderItem，并添加到分页查询中。
     */
    public void addOrderItem() {
        List<OrderItem> orderItems = new ArrayList<>();
        String currentOrder = getOrder();
        if (StrUtil.isNotBlank(currentOrder)) {
            orderItems.add("asc".equals(getSort()) ? OrderItem.asc(currentOrder) : OrderItem.desc(currentOrder));
        } else {
            orderItems.add(OrderItem.desc("id")); // 默认按id降序
        }
        this.addOrder(orderItems);
    }
}
