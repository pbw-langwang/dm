package cool.pbw.dm.service.impl;


import cool.pbw.dm.entity.Slideshow;
import cool.pbw.dm.mapper.SlideshowMapper;
import cool.pbw.dm.service.ISlideshowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * InnoDB free: 9216 kB 服务实现类
 * </p>
 *
 * @author pbw
 * @since 2021-04-13
 */
@Service
public class SlideshowServiceImpl extends ServiceImpl<SlideshowMapper, Slideshow> implements ISlideshowService {
	@Autowired
	SlideshowMapper slideshowMapper;
	@Override
	public List<Slideshow> getSlide(Integer index){
		return slideshowMapper.getSlide(index);
	}
}
