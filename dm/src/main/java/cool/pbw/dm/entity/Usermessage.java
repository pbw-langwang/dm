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
 * @since 2021-04-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Usermessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String userpwd;

    private String userimg;

    @TableField("userRegTime")
    private Date userregtime;

    private String userintroduction;

    private Integer uservip;
    
    private Integer userid;
    
    private Integer usertype;
    
    private String useremail;
    
    private transient String vericode;

}
