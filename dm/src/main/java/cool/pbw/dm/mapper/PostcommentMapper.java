package cool.pbw.dm.mapper;

import cool.pbw.dm.entity.Postcomment;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * InnoDB free: 9216 kB Mapper 接口
 * </p>
 *
 * @author pbw
 * @since 2021-05-03
 */
public interface PostcommentMapper extends BaseMapper<Postcomment> {
	@Select("SELECT c.*,u.username,u.userimg from postcomment c LEFT JOIN usermessage u "+
			"on c.username = u.username where c.postid=${postid}")
	public List<Postcomment> getCommentByid(Integer postid);
}
