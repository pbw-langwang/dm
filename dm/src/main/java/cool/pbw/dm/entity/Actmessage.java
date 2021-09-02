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
public class Actmessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String actname;

    private String actimg;

    private String acturl;

    private String actintroduction;

    private String actvip;

    private Integer type;

    @TableId(value = "actid", type = IdType.AUTO)
    private Integer actid;


}
