package cool.pbw.dm.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cool.pbw.dm.entity.Attention;
import cool.pbw.dm.entity.Usermessage;
import cool.pbw.dm.service.IAttentionService;


/**
 * <p>
 * InnoDB free: 9216 kB 前端控制器
 * </p>
 *
 * @author pbw
 * @since 2021-06-10
 */
@RestController
@RequestMapping("/dm/attention")
public class AttentionController {
	@Autowired
	IAttentionService attentionService;
	
	@RequestMapping("/exist")
	public String exist(Attention c) {
		QueryWrapper<Attention> queryWrapper = new QueryWrapper<Attention>();
		queryWrapper.eq("authorid", c.getAuthorid()).eq("userid",c.getUserid());
		Attention one = attentionService.getOne(queryWrapper);
		if(one!=null){
			return "exist";
		}else {
			return "not";
		}
	}
	
	@RequestMapping("/add")
	public String add(Attention c) {
		attentionService.save(c);
		return "";
	}
	
	@RequestMapping("/del")
	public String del(Attention c) {
		QueryWrapper<Attention> queryWrapper = new QueryWrapper<Attention>();
		queryWrapper.eq("authorid", c.getAuthorid()).eq("userid", c.getUserid());
		attentionService.remove(queryWrapper);
		return "";
	}
	
	@RequestMapping("/attentionsList")
	public List<Usermessage> attentionsList(Integer userid) {
		return attentionService.getByUserid(userid);
	}
	
}

