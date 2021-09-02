package cool.pbw.dm.controller;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cool.pbw.dm.entity.Contenthistory;
import cool.pbw.dm.service.IContenthistoryService;

/**
 * <p>
 * InnoDB free: 9216 kB 前端控制器
 * </p>
 *
 * @author pbw
 * @since 2021-04-13
 */
@RestController
@RequestMapping("/dm/contenthistory")
public class ContenthistoryController {
	@Autowired
	IContenthistoryService contenthistoryService;
	
	@RequestMapping("/getByUsername")
	public IPage<Contenthistory> getByUsername(Page<Contenthistory> page,String username){
		return contenthistoryService.selectByUsername(page,username);
	}
	
	@RequestMapping("/add")
	public String add(Contenthistory c) {
		c.setContentseetime(new Date());
		contenthistoryService.save(c);
		return "ok";
	}
	
	@RequestMapping("/getNum")
	public Integer getNum(String username) {
		return contenthistoryService.getNum(username);
	}
}

