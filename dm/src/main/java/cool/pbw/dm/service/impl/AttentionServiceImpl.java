package cool.pbw.dm.service.impl;

import cool.pbw.dm.entity.Attention;
import cool.pbw.dm.entity.Usermessage;
import cool.pbw.dm.mapper.AttentionMapper;
import cool.pbw.dm.service.IAttentionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * InnoDB free: 9216 kB 服务实现类
 * </p>
 *
 * @author pbw
 * @since 2021-06-10
 */
@Service
public class AttentionServiceImpl extends ServiceImpl<AttentionMapper, Attention> implements IAttentionService {
	@Autowired
	AttentionMapper attentionMapper;
	
	public List<Usermessage> getByUserid(Integer userid) {
		return attentionMapper.getByUserid(userid);
	}
}
