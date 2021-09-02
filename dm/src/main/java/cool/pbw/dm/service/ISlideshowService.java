package cool.pbw.dm.service;

import cool.pbw.dm.entity.Slideshow;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * InnoDB free: 9216 kB 服务类
 * </p>
 *
 * @author pbw
 * @since 2021-04-13
 */
public interface ISlideshowService extends IService<Slideshow> {
	public List<Slideshow> getSlide(Integer index);
}
