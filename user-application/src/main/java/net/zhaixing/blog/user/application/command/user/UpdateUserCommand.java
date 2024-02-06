package net.zhaixing.blog.user.application.command.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import net.zhaixing.blog.user.common.domain.Command;


/**
 * 修改用户指令
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-02
 * @since 1.0.0
 */
@Data
public class UpdateUserCommand implements Command {
    @NotNull(message = "{user.id.is.null}")
    private Long userId;
    /**
     * 用户名
     */
    @NotBlank(message = "{user.userName.is.blank}")
    private String userName;
}
