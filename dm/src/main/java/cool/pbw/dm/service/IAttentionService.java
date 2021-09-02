package cool.pbw.dm.service;

import cool.pbw.dm.entity.Attention;
import cool.pbw.dm.entity.Usermessage;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * InnoDB free: 9216 kB 服务类
 * </p>
 *
 * @author pbw
 * @since 2021-06-10
 */
public interface IAttentionService extends IService<Attention> {
	public List<Usermessage> getByUserid(Integer userid);
}
