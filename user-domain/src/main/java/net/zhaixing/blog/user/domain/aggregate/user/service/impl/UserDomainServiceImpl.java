package net.zhaixing.blog.user.domain.aggregate.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.zhaixing.blog.user.domain.aggregate.role.model.Role;
import net.zhaixing.blog.user.domain.aggregate.user.model.User;
import net.zhaixing.blog.user.domain.aggregate.user.service.UserDomainService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户领域服务实现
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
@Service
@Slf4j
public class UserDomainServiceImpl implements UserDomainService {
    @Override
    public void printTag(User user, List<Role> roles){
        roles.forEach(role->{
            //省略大量逻辑
            if(role.isAdmin()){
                log.info("用户：{}的标签解析为：{}",user.getUserName(),role.getName());
            }
        });
    }
}
