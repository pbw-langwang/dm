package cool.pbw.dm.service.impl;

import cool.pbw.dm.entity.Contentclass;
import cool.pbw.dm.mapper.ContentclassMapper;
import cool.pbw.dm.service.IContentclassService;
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
 * @since 2021-04-13
 */
@Service
public class ContentclassServiceImpl extends ServiceImpl<ContentclassMapper, Contentclass> implements IContentclassService {
	@Autowired
	ContentclassMapper contentclassMapper;
	@Override
	public List<Contentclass> getContent(){
		return contentclassMapper.getContent();
	}
}
