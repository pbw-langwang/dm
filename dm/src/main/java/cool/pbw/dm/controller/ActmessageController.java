package cool.pbw.dm.controller;


import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cool.pbw.dm.config.Props;
import cool.pbw.dm.entity.Actmessage;
import cool.pbw.dm.entity.QueryVo;
import cool.pbw.dm.service.IActmessageService;

/**
 * <p>
 * InnoDB free: 9216 kB 前端控制器
 * </p>
 *
 * @author pbw
 * @since 2021-04-13
 */
@RestController
@RequestMapping("/dm/actmessage")
public class ActmessageController {
	@Autowired
	IActmessageService actmessageService;
	@Autowired
	Props props;
	
	@RequestMapping("/actAll")
	public IPage<Actmessage> actAll(Page<Actmessage> page,Integer contenttype){
		QueryWrapper<Actmessage> queryWrapper = new QueryWrapper<Actmessage>();
		queryWrapper.select("actname,actimg,acturl,actintroduction,actvip,type,actid").orderByDesc("actid");
		return actmessageService.page(page,queryWrapper);
	}
	
	@RequestMapping("/All")
	public IPage<Actmessage> All(Page<Actmessage> page){
		return actmessageService.selectAll(page);
	}
	
	@RequestMapping("/Search")
	public IPage<Actmessage> Search(Page<Actmessage> page,String val){
		QueryWrapper<Actmessage> queryWrapper = new QueryWrapper<Actmessage>();
		queryWrapper.select("actname,actimg,acturl,actintroduction,actvip,type,actid").like("actname", val).orderByDesc("actid");
		return actmessageService.page(page,queryWrapper);
	}
	
	@RequestMapping("/getByid")
	public Actmessage getByid(Integer actid){
		QueryWrapper<Actmessage> queryWrapper = new QueryWrapper<Actmessage>();
		queryWrapper.select("actname,actimg,acturl,type,actintroduction,actvip").eq("actid", actid);
		return actmessageService.getOne(queryWrapper);
	}
	
	@RequestMapping("/fabu")
	public String fabu(Actmessage u,HttpSession session,MultipartFile imgFile) {
		if(imgFile!=null) {
			String originalFilename = imgFile.getOriginalFilename();
			String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
			String newName = UUID.randomUUID().toString().replace("-", "")+suffix;
			try {
				imgFile.transferTo(new File(props.getUploadPath()+newName));
				u.setActimg(newName);
				actmessageService.save(u);
				return "ok";
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "fail";
		}
		return "fail";
	}
	
	@RequestMapping("/del")
	public String del(Actmessage c) {
		QueryWrapper<Actmessage> qwQueryWrapper = new QueryWrapper<Actmessage>();
		qwQueryWrapper.eq("actid", c.getActid());
		actmessageService.remove(qwQueryWrapper);
		return "ok";
	}
	
	@RequestMapping("batchDel")
	public String batchDel(@RequestBody QueryVo vo) {
		if(vo.getDelIds()!=null) {
			for(Integer id:vo.getDelIds()) {
				actmessageService.removeById(id);
			}
		}
		return "ok";
	}
}

