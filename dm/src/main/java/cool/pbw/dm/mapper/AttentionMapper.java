package cool.pbw.dm.mapper;

import cool.pbw.dm.entity.Attention;
import cool.pbw.dm.entity.Usermessage;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * InnoDB free: 9216 kB Mapper 接口
 * </p>
 *
 * @author pbw
 * @since 2021-06-10
 */
public interface AttentionMapper extends BaseMapper<Attention> {
	@Select("select u.* from usermessage u LEFT JOIN attention a ON a.authorid = u.userid WHERE a.userid = ${userid}")
	public List<Usermessage> getByUserid(Integer userid);
}
