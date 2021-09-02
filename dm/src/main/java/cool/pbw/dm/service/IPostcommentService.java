package cool.pbw.dm.service;

import cool.pbw.dm.entity.Postcomment;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * InnoDB free: 9216 kB 服务类
 * </p>
 *
 * @author pbw
 * @since 2021-05-03
 */
public interface IPostcommentService extends IService<Postcomment> {
	public List<Postcomment> getCommentByid(Integer postid);
}
