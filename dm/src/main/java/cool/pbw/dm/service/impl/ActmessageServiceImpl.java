package cool.pbw.dm.service.impl;

import cool.pbw.dm.entity.Actmessage;
import cool.pbw.dm.mapper.ActmessageMapper;
import cool.pbw.dm.service.IActmessageService;

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
public class ActmessageServiceImpl extends ServiceImpl<ActmessageMapper, Actmessage> implements IActmessageService {
	@Autowired
	ActmessageMapper actmessageMapper;
	
	public IPage<Actmessage> selectAll(Page<?> page){
		return actmessageMapper.selectAll(page);
	}
}
