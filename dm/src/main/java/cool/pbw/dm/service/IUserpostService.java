package cool.pbw.dm.service;

import cool.pbw.dm.entity.Userpost;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * InnoDB free: 9216 kB 服务类
 * </p>
 *
 * @author pbw
 * @since 2021-04-29
 */
public interface IUserpostService extends IService<Userpost> {
	public IPage<Userpost> selectByName(Page<?> page);
	public Userpost selectByid(Integer postid);
	public void updateByid(Integer postid);
}
