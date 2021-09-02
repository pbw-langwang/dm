package cool.pbw.dm.mapper;

import cool.pbw.dm.entity.Contentmessage;

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
public interface ContentmessageMapper extends BaseMapper<Contentmessage> {
	@Select("select * from contentmessage order by contentid desc")
	public IPage<Contentmessage> selectAll(Page<?> page);
}
