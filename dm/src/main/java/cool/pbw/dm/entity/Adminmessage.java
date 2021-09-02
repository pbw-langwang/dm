package cool.pbw.dm.entity;

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
public class Adminmessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String adminname;

    private String adminpwd;

    private String adminimg;

    private String admintype;


}
