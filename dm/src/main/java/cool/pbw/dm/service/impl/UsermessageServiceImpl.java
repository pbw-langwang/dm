package cool.pbw.dm.service.impl;

import cool.pbw.dm.entity.Usermessage;
import cool.pbw.dm.mapper.UsermessageMapper;
import cool.pbw.dm.service.IUsermessageService;
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
public class UsermessageServiceImpl extends ServiceImpl<UsermessageMapper, Usermessage> implements IUsermessageService {
	@Autowired
	UsermessageMapper usermessageMapper;
	
	public String update(Usermessage u,String username) {
		return usermessageMapper.update(u, username);
	}
}
