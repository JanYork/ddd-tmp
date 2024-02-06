package net.zhaixing.blog.user.infra.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.zhaixing.blog.user.infra.db.model.RolePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色信息mapper
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
@Mapper
public interface RoleMapper extends BaseMapper<RolePO> {

}
