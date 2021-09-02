package cool.pbw.dm.mapper;

import cool.pbw.dm.entity.Contentclass;

import java.util.List;

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
public interface ContentclassMapper extends BaseMapper<Contentclass> {
	@Select("SELECT content FROM contentclass ORDER BY classid ASC")
	public List<Contentclass> getContent();
}
