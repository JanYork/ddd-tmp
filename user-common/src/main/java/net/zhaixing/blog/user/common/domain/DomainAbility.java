package net.zhaixing.blog.user.common.domain;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 领域能力标识注解
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Component
public @interface DomainAbility {

    /**
     * 该值可能指示对逻辑组件名称的建议，在自动检测到组件的情况下转换为 Spring bean。
     *
     * @return 建议的组件名称（如果有）
     */
    @AliasFor(annotation = Component.class, attribute = "value") String value() default "";

    /**
     * 所属业务域
     */
    String domain();

    /**
     * 能力名称
     */
    String name() default "";
}
