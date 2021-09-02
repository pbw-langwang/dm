package cool.pbw.dm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cool.pbw.dm.entity.Contentclass;
import cool.pbw.dm.service.IContentclassService;

/**
 * <p>
 * InnoDB free: 9216 kB 前端控制器
 * </p>
 *
 * @author pbw
 * @since 2021-04-13
 */
@RestController
@RequestMapping("/dm/contentclass")
public class ContentclassController {
	@Autowired
	RedisTemplate redisTemplate;
	
	@Autowired
	IContentclassService contentclassService;
	
	@RequestMapping("/getcontent")
	public List<Contentclass> getcontent() {
		ListOperations listOps = redisTemplate.opsForList();
		Long size = listOps.size("classList");
		if(size != null && size > 0) {
			return listOps.range("classList", 0, -1);
		}else {
			List<Contentclass> contentclass = contentclassService.list();
			listOps.rightPushAll("classList", contentclass);
			return contentclass;
		}
	}
}

