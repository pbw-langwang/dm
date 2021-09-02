package cool.pbw.dm.mapper;

import cool.pbw.dm.entity.Slideshow;

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
public interface SlideshowMapper extends BaseMapper<Slideshow> {
	@Select("SELECT slideshowimg FROM slideshow where slideshowtype = ${index}")
	public List<Slideshow> getSlide(Integer index);
}
