package top.ppnt.actable.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Ping E Lee
 * @since 2022-05-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Role implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 自增id
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    /**
     * 角色名字
     */
    private String name;

    /**
     * 角色的中文名字
     */
    private String nameZh;

    /**
     * 角色的描述
     */
    private String desc;


}
