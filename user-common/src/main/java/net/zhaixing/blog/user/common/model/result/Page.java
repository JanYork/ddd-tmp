package net.zhaixing.blog.user.common.model.result;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 分页数据返回实体。
 * 继承自MyBatis Plus的Page类，并实现了Iterable接口，允许遍历分页中的记录。
 * 可用于多种类型的分页数据。
 *
 * @param <T> 分页中记录的类型
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Page<T> extends com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> implements Iterable<T> {

    /**
     * 返回一个迭代器，用于遍历分页中的记录。
     *
     * @return 记录的迭代器
     */
    @Override
    public Iterator<T> iterator() {
        return getRecords().iterator();
    }

    /**
     * 对分页中的每个记录执行给定的操作。
     *
     * @param action 要对每个记录执行的操作
     */
    @Override
    public void forEach(Consumer<? super T> action) {
        getRecords().forEach(action);
    }

    /**
     * 创建并返回一个用于分页中记录的Spliterator。
     *
     * @return 记录的Spliterator
     */
    @Override
    public Spliterator<T> spliterator() {
        return getRecords().spliterator();
    }

    /**
     * 将当前分页中的元素转换为另一种类型。
     * 使用提供的映射函数对每个元素进行转换。
     *
     * @param mapper 映射函数
     * @param <R> 目标元素类型
     * @return 转换后的分页对象
     */
    @Override
    public <R> Page<R> convert(Function<? super T, ? extends R> mapper) {
        Page<R> resultPage = new Page<>();
        this.getRecords().stream().map(mapper).forEach(resultPage.getRecords()::add);
        resultPage.setCurrent(this.getCurrent());
        resultPage.setSize(this.getSize());
        resultPage.setTotal(this.getTotal());
        return resultPage;
    }

    /**
     * 设置分页偏移量。
     * 用于反序列化时计算当前页码。
     *
     * @param offset 偏移量
     */
    public void setOffset(Long offset) {
        setCurrent(offset / getSize() + 1);
    }

    /**
     * 获取分页大小。
     *
     * @return 分页大小
     */
    @Override
    public long getSize() {
        return super.getSize();
    }

    /**
     * 设置分页大小。
     * 用于反序列化时设置分页大小。
     *
     * @param limit 分页大小
     */
    public void setLimit(Long limit) {
        setSize(limit);
    }

    /**
     * 获取当前页码。
     *
     * @return 当前页码
     */
    @Override
    public long getCurrent() {
        return super.getCurrent();
    }

    /**
     * 获取总记录数。
     *
     * @return 总记录数
     */
    @Override
    public long getTotal() {
        return super.getTotal();
    }
}
