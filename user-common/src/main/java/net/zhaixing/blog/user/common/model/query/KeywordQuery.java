package net.zhaixing.blog.user.common.model.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 关键字查询
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class KeywordQuery extends PageQuery<Void> {
    private String keyword;
}
