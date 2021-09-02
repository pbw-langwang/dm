package cool.pbw.dm.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;

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
public class Contenthistory implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String contentname;

    @TableField("contentSeeTime")
//    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")  小写是12进制，大写是24进制
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date contentseetime;

}
