package net.zhaixing.blog.user.application.query;

import net.zhaixing.blog.user.application.query.model.user.dto.UserDTO;
import net.zhaixing.blog.user.application.query.model.user.dto.UserPageVO;
import net.zhaixing.blog.user.common.domain.AppQueryService;
import net.zhaixing.blog.user.common.model.query.KeywordQuery;
import net.zhaixing.blog.user.common.model.result.Page;

/**
 * 用户查询应用服务
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-02
 * @since 1.0.0
 */
public interface UserAppQueryService extends AppQueryService {
    /**
     * 用户分页数据查询
     *
     * @param query 查询条件
     * @return 分页数据
     */
    Page<UserPageVO> userPage(KeywordQuery query);

    /**
     * 根据用户名称查询用户详情
     *
     * @param userName 用户名称
     * @return 用户详情
     */
    UserDTO detail(String userName);
}
