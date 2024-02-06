package net.zhaixing.blog.user.application.query;

import net.zhaixing.blog.user.application.query.model.role.dto.RoleDTO;
import net.zhaixing.blog.user.common.domain.AppQueryService;

import java.util.List;

/**
 * 角色查询应用服务
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-02
 * @since 1.0.0
 */
public interface RoleAppQueryService extends AppQueryService {

    /**
     * 根据角色id查询信息
     *
     * @param roleIds 角色id
     * @return 角色信息
     */
    List<RoleDTO> list(List<Long> roleIds);
}
