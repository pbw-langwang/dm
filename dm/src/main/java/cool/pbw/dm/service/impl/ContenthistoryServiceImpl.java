package cool.pbw.dm.service.impl;

import cool.pbw.dm.entity.Contenthistory;
import cool.pbw.dm.mapper.ContenthistoryMapper;
import cool.pbw.dm.service.IContenthistoryService;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * InnoDB free: 9216 kB 服务实现类
 * </p>
 *
 * @author pbw
 * @since 2021-04-13
 */
@Service
public class ContenthistoryServiceImpl extends ServiceImpl<ContenthistoryMapper, Contenthistory> implements IContenthistoryService {
	@Autowired
	ContenthistoryMapper contenthistoryMapper;
	
	public IPage<Contenthistory> selectByUsername(Page<?> page,String username){
		return contenthistoryMapper.selectByUsername(page, username);
	}
	
	public Integer getNum(String username) {
		return contenthistoryMapper.getNum(username);
	}
}
