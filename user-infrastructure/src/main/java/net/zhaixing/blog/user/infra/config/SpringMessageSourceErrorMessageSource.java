package net.zhaixing.blog.user.infra.config;

import jakarta.annotation.Resource;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Spring MessageSource 错误消息源
 * <p>
 * 此类用于在Spring框架应用中获取和格式化错误消息。
 * 它利用Spring的MessageSource来支持国际化和本地化消息。
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
@Component  // 表明这是一个Spring组件，可以被Spring容器自动检测和管理。
public class SpringMessageSourceErrorMessageSource {

    @Resource
    private MessageSource messageSource;  // 注入Spring的MessageSource，用于消息的国际化和本地化。

    /**
     * 根据消息代码和参数获取消息文本。
     * 这个方法使用当前的区域设置（locale）来获取相应的国际化消息。
     *
     * @param code   消息代码，通常是properties文件中定义的键。
     * @param params 替换消息文本中占位符的参数。
     * @return 根据区域设置解析后的消息文本。
     */
    public String getMessage(String code, Object... params) {
        return messageSource.getMessage(code, params, LocaleContextHolder.getLocale());
    }

    /**
     * 根据消息代码、默认消息和参数获取消息文本。
     * 如果给定的消息代码在消息源中未找到，则返回默认消息。
     *
     * @param code           消息代码。
     * @param defaultMessage 如果消息代码未找到时返回的默认消息。
     * @param params         替换消息文本中占位符的参数。
     * @return 根据区域设置解析后的消息文本，或默认消息。
     */
    public String getMessage(String code, String defaultMessage, Object... params) {
        return messageSource.getMessage(code, params, defaultMessage, LocaleContextHolder.getLocale());
    }
}
