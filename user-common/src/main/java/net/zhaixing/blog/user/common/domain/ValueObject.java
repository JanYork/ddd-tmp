package net.zhaixing.blog.user.common.domain;

/**
 * 值对象标记接口
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
public interface ValueObject<T> extends Marker {

    /**
     * 值对象通过属性比较
     *
     * @param other 其他值对象
     * @return 是否相等
     */
    boolean sameValueAs(T other);

}
