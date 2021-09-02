package cool.pbw.dm.mapper;

import cool.pbw.dm.entity.Contenthistory;

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
 * @since 2021-04-13
 */
public interface ContenthistoryMapper extends BaseMapper<Contenthistory> {
	@Select("select c.* from contenthistory c left join usermessage d on c.username=d.username where c.username='${username}' order by historyid DESC")
	public IPage<Contenthistory> selectByUsername(Page<?> page,String username);
	
	@Select("SELECT COUNT(historyid) FROM contenthistory WHERE username='${username}'")
	public Integer getNum(String username);
}
