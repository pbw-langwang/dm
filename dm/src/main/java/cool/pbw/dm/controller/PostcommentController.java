package cool.pbw.dm.controller;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import cool.pbw.dm.entity.Postcomment;
import cool.pbw.dm.service.IPostcommentService;

/**
 * <p>
 * InnoDB free: 9216 kB 前端控制器
 * </p>
 *
 * @author pbw
 * @since 2021-05-03
 */
@RestController
@RequestMapping("/dm/postcomment")
public class PostcommentController {
	@Autowired
	IPostcommentService postcommentService;
	
	@RequestMapping("/getCommentByid")
	public List<Postcomment> getCommentByid(Postcomment comment){
		return postcommentService.getCommentByid(comment.getPostid());
	}
	
	@RequestMapping("/add")
	public String add(Postcomment comment) {
		comment.setTime(new Date());
		postcommentService.save(comment);
		return "";
	}
}

