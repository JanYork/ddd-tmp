package net.zhaixing.blog.user.common.model.result;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 基础表实体类
 *
 * @author JanYork
 * @version 1.0.0
 * @date 2024-01-01
 * @since 1.0.0
 */
@Data
public class BaseModel {
    /**
     * 主键id 采用默认雪花算法
     */
    @TableId
    private Long id;
    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;
    /**
     * mybatis-plus支持的数据软删标识
     * <p>
     * 是否删除，0为未删除
     */
    @TableLogic(delval = "current_timestamp()")
    private Long deleted;
}
