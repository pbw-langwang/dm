package cool.pbw.dm.mapper;

import cool.pbw.dm.entity.Actmessage;

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
public interface ActmessageMapper extends BaseMapper<Actmessage> {
	@Select("select * from actmessage order by actid desc")
	public IPage<Actmessage> selectAll(Page<?> page);
}
