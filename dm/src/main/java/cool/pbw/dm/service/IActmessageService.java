package cool.pbw.dm.service;

import cool.pbw.dm.entity.Actmessage;

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
public interface IActmessageService extends IService<Actmessage> {
	public IPage<Actmessage> selectAll(Page<?> page);
}
