package cool.pbw.dm.mapper;

import cool.pbw.dm.entity.Usermessage;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * InnoDB free: 9216 kB Mapper 接口
 * </p>
 *
 * @author pbw
 * @since 2021-04-13
 */
public interface UsermessageMapper extends BaseMapper<Usermessage> {
	@Select("update usermessage set username='${u.username}',useremail='${u.useremail}',userpwd=${u.userpwd},userimg='${u.userimg}' "+
			"where username = '${username}'")
	public String update(Usermessage u,String username);
}
