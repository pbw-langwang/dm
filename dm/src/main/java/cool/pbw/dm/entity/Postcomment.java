package cool.pbw.dm.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * InnoDB free: 9216 kB
 * </p>
 *
 * @author pbw
 * @since 2021-05-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Postcomment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer commentid;

    private String username;

    private String content;

    private Date time;

    private Integer postid;

    @TableField("isDel")
    private Integer isdel;

    public transient String userimg;
}
