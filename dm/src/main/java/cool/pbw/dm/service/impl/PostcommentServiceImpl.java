package cool.pbw.dm.service.impl;

import cool.pbw.dm.entity.Postcomment;
import cool.pbw.dm.mapper.PostcommentMapper;
import cool.pbw.dm.service.IPostcommentService;
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
 * @since 2021-05-03
 */
@Service
public class PostcommentServiceImpl extends ServiceImpl<PostcommentMapper, Postcomment> implements IPostcommentService {
	@Autowired
	PostcommentMapper commentMapper;
	@Override
	public List<Postcomment> getCommentByid(Integer postid){
		return commentMapper.getCommentByid(postid);
	}
}
