package net.zhaixing.blog.user.application.ability.user.cmd;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import net.zhaixing.blog.user.common.domain.AbilityCommand;
import net.zhaixing.blog.user.common.domain.Command;
import net.zhaixing.blog.user.domain.aggregate.user.model.User;

import java.util.List;

/**
 * 创建用户(能力)指令
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-02
 * @since 1.0.0
 */
@Data
public class CreateUserAbilityCommand implements AbilityCommand {
    /**
     * 用户名
     */
    @NotBlank(message = "{user.userName.is.blank}")
    private String userName;

    /**
     * 用户真实名称
     */
    @NotBlank(message = "{user.realName.is.blank}")
    private String realName;

    /**
     * 用户手机号
     */
    @NotBlank(message = "{user.phone.is.blank}")
    private String phone;

    /**
     * 省
     */
    @NotBlank(message = "{user.province.is.blank}")
    private String province;

    /**
     * 市
     */
    @NotBlank(message = "{user.city.is.blank}")
    private String city;

    /**
     * 区
     */
    @NotBlank(message = "{user.county.is.blank}")
    private String county;

    /**
     * 角色
     */
    @Size(min = 1, message = "{user.role.id.is.empty}")
    private List<Long> roles;

    /**
     * 单位id
     */
    @NotNull(message = "{unit.id.is.null}")
    private Long unitId;

    /**
     * 用户密码
     */
    @NotBlank(message = "{user.password.is.blank}")
    private String password;

    /**
     * 将command转换成聚合根
     * <p>
     * 逻辑简单，只是做一些字段的映射则在command里面直接转化返回给应用服务层使用即可。
     * <p>
     * 如果逻辑复杂，command参数进来需要做一些比较复杂的逻辑处理，则使用工厂类
     *
     * @return  聚合根
     */
    public User toUser() {
        User user = User.builder()
                .userName(this.userName)
                .realName(this.realName)
                .phone(this.phone)
                .password(this.password)
                .build();
        user.bindUnit(this.unitId);
        user.bindRole(this.roles);
        user.bindAddress(this.province, this.city, this.county);
        return user;
    }
}
