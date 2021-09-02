package cool.pbw.dm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * InnoDB free: 9216 kB
 * </p>
 *
 * @author pbw
 * @since 2021-04-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Contentclass implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "classId", type = IdType.AUTO)
    private Integer classid;

    private String content;


}
