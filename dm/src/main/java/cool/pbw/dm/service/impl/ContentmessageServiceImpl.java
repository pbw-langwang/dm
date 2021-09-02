package cool.pbw.dm.service.impl;

import cool.pbw.dm.entity.Contentmessage;
import cool.pbw.dm.mapper.ContentmessageMapper;
import cool.pbw.dm.service.IContentmessageService;

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
public class ContentmessageServiceImpl extends ServiceImpl<ContentmessageMapper, Contentmessage> implements IContentmessageService {
	@Autowired
	ContentmessageMapper contentmessageMapper;
	
	public IPage<Contentmessage> selectAll(Page<?> page){
		return contentmessageMapper.selectAll(page);
	}
}
