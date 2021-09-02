package cool.pbw.dm.service;

import cool.pbw.dm.entity.Contentclass;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * InnoDB free: 9216 kB 服务类
 * </p>
 *
 * @author pbw
 * @since 2021-04-13
 */
public interface IContentclassService extends IService<Contentclass> {
	public List<Contentclass> getContent();
}
