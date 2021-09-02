package cool.pbw.dm.service.impl;

import cool.pbw.dm.entity.Userpost;
import cool.pbw.dm.mapper.UserpostMapper;
import cool.pbw.dm.service.IUserpostService;

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
 * @since 2021-04-29
 */
@Service
public class UserpostServiceImpl extends ServiceImpl<UserpostMapper, Userpost> implements IUserpostService {
	@Autowired
	UserpostMapper userpostMapper;
	public IPage<Userpost> selectByName(Page<?> page){
		return userpostMapper.selectByName(page);
	}
	
	public Userpost selectByid(Integer postid){
		return userpostMapper.selectByid(postid);
	}
	
	public void updateByid(Integer postid) {
		userpostMapper.updateByid(postid);
	}
}
