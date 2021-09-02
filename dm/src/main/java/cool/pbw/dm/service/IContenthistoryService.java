package cool.pbw.dm.service;

import cool.pbw.dm.entity.Contenthistory;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * InnoDB free: 9216 kB 服务类
 * </p>
 *
 * @author pbw
 * @since 2021-04-13
 */
public interface IContenthistoryService extends IService<Contenthistory> {
	public IPage<Contenthistory> selectByUsername(Page<?> page,String username);
	public Integer getNum(String username);
}
