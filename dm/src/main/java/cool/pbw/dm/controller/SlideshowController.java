package cool.pbw.dm.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cool.pbw.dm.entity.Slideshow;
import cool.pbw.dm.service.ISlideshowService;

/**
 * <p>
 * InnoDB free: 9216 kB 前端控制器
 * </p>
 *
 * @author pbw
 * @since 2021-04-13
 */
@RestController
@RequestMapping("/dm/slideshow")
public class SlideshowController {
	@Autowired
	ISlideshowService slideshowService;
	
	@RequestMapping("/getslide")
	public List<Slideshow> getslide(Integer index) {
		return slideshowService.getSlide(index);
	}
}

