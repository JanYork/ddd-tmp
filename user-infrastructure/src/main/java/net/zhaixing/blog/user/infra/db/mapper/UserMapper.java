package net.zhaixing.blog.user.infra.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.zhaixing.blog.user.common.model.query.KeywordQuery;
import net.zhaixing.blog.user.common.model.result.Page;
import net.zhaixing.blog.user.infra.db.model.UserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息mapper
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
@Mapper
public interface UserMapper extends BaseMapper<UserPO> {
    /**
     * 用户信息分页查询
     *
     * @param query 查询条件
     * @return 用户信息分页
     */
    Page<UserPO> userPage(KeywordQuery query);
}
