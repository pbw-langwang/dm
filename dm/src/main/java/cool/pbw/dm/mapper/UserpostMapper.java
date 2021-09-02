package cool.pbw.dm.mapper;

import cool.pbw.dm.entity.Userpost;
import org.apache.ibatis.annotations.Select;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * InnoDB free: 9216 kB Mapper 接口
 * </p>
 *
 * @author pbw
 * @since 2021-04-29
 */
public interface UserpostMapper extends BaseMapper<Userpost> {
	@Select("select u.userimg,u.uservip,u.userid,p.* from usermessage u "
			+"left join userpost p on u.username=p.username ORDER BY postid DESC")
	public IPage<Userpost> selectByName(Page<?> page);
	
	@Select("select u.userimg,u.uservip,u.userid,p.* from usermessage u "
			+"left join userpost p on p.username = u.username where p.postid=${postid}")
	public Userpost selectByid(Integer postid);
	
	@Select("update userpost set postgood = 1 where postid = ${postid}")
	public void updateByid(Integer postid);
}
