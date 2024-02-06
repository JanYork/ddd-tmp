package net.zhaixing.blog.user.domain.share.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.zhaixing.blog.user.common.domain.ValueObject;

import java.util.Objects;

/**
 * 地址值对象
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Address implements ValueObject<Address> {
    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String county;

    /**
     * 比较地址相等
     *
     * @param address 地址
     * @return 是否相等
     */
    @Override
    public boolean sameValueAs(Address address) {
        return Objects.equals(this, address);
    }
}
