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
import cool.pbw.dm.entity.QueryVo;
import cool.pbw.dm.entity.Userpost;
import cool.pbw.dm.service.IUserpostService;


/**
 * <p>
 * InnoDB free: 9216 kB 前端控制器
 * </p>
 *
 * @author pbw
 * @since 2021-04-29
 */
@RestController
@RequestMapping("/dm/userpost")
public class UserpostController {
	@Autowired
	IUserpostService userService;
	@Autowired
	IUserpostService userpostService;
	@Autowired
	Props props;
	
	@RequestMapping("/listAll")
	public IPage<Userpost> getByUsername(Page<Userpost> page){
		return userpostService.selectByName(page);
	}
	
	@RequestMapping("/getInfo")
	public Userpost getInfo(Integer postid) {
		return userpostService.selectByid(postid);
	}
	
	@RequestMapping("/getByid")
	public IPage<Userpost> getByid(Page<Userpost> page,String username){
		QueryWrapper<Userpost> queryWrapper = new QueryWrapper<Userpost>();
		queryWrapper.select("postgood,postimg,postcontent,postid").eq("username", username).orderByDesc("postid");
		return userpostService.page(page,queryWrapper);
	}
	
	@RequestMapping("/getBygood")
	public IPage<Userpost> getBygood(Page<Userpost> page){
		QueryWrapper<Userpost> queryWrapper = new QueryWrapper<Userpost>();
		queryWrapper.select("postgood,postimg,postcontent,postid").eq("postgood", 0).orderByDesc("postid");
		return userpostService.page(page,queryWrapper);
	}
	
	@RequestMapping("/fabu")
	public String fabu(Userpost u,HttpSession session,MultipartFile imgFile) {
		if(imgFile!=null) {
			String originalFilename = imgFile.getOriginalFilename();
			String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
			String newName = UUID.randomUUID().toString().replace("-", "")+suffix;
			try {
				imgFile.transferTo(new File(props.getUploadPath()+newName));
				u.setPostimg(newName);
				u.setPostgood(0);
				userpostService.save(u);
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
	public String del(Userpost c) {
		QueryWrapper<Userpost> qwQueryWrapper = new QueryWrapper<Userpost>();
		qwQueryWrapper.eq("postid", c.getPostid());
		userpostService.remove(qwQueryWrapper);
		return "ok";
	}
	
	@RequestMapping("batchDel")
	public String batchDel(@RequestBody QueryVo vo) {
		if(vo.getDelIds()!=null) {
			for(Integer id:vo.getDelIds()) {
				userpostService.removeById(id);
			}
		}
		return "ok";
	}
	
	@RequestMapping("/pass")
	public void pass(Integer postid) {
		userpostService.updateByid(postid);
	}
	
	@RequestMapping("batchPass")
	public String batchPass(@RequestBody QueryVo vo) {
		if(vo.getDelIds()!=null) {
			for(Integer id:vo.getDelIds()) {
				userpostService.updateByid(id);
			}
		}
		return "ok";
	}
}

