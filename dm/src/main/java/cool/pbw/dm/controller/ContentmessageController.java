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
import cool.pbw.dm.entity.Contentmessage;
import cool.pbw.dm.entity.QueryVo;
import cool.pbw.dm.service.IContentmessageService;

/**
 * <p>
 * InnoDB free: 9216 kB 前端控制器
 * </p>
 *
 * @author pbw
 * @since 2021-04-13
 */
@RestController
@RequestMapping("/dm/contentmessage")
public class ContentmessageController {
	@Autowired
	IContentmessageService contentmessageService;
	@Autowired
	Props props;
	
	@RequestMapping("/listAll")
	public IPage<Contentmessage> listAll(Page<Contentmessage> page,Integer contenttype){
		QueryWrapper<Contentmessage> queryWrapper = new QueryWrapper<Contentmessage>();
		queryWrapper.select("contentid,contentimg,contenturl,contentname,contentintroduction,contentvip").eq("contenttype", contenttype).orderByDesc("contentid");
		return contentmessageService.page(page,queryWrapper);
	}
	
	@RequestMapping("/All")
	public IPage<Contentmessage> All(Page<Contentmessage> page){
		return contentmessageService.selectAll(page);
	}
	
	@RequestMapping("/Search")
	public IPage<Contentmessage> listAll(Page<Contentmessage> page,String val){
		QueryWrapper<Contentmessage> queryWrapper = new QueryWrapper<Contentmessage>();
		queryWrapper.select("contentid,contentimg,contenturl,contentname,contentintroduction,contentvip").like("contentname", val).orderByDesc("contentid");
		return contentmessageService.page(page,queryWrapper);
	}
	
	@RequestMapping("/getByid")
	public Contentmessage getByid(Integer contentid){
		QueryWrapper<Contentmessage> queryWrapper = new QueryWrapper<Contentmessage>();
		queryWrapper.select("contentname,contentimg,contenturl,contentintroduction,contenttype,contentvip").eq("contentid", contentid);
		return contentmessageService.getOne(queryWrapper);
	}
	
	@RequestMapping("/fabu")
	public String fabu(Contentmessage u,HttpSession session,MultipartFile imgFile) {
		if(imgFile!=null) {
			String originalFilename = imgFile.getOriginalFilename();
			String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
			String newName = UUID.randomUUID().toString().replace("-", "")+suffix;
			try {
				imgFile.transferTo(new File(props.getUploadPath()+newName));
				u.setContentimg(newName);
				u.setContentshow("0");
				contentmessageService.save(u);
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
	public String del(Contentmessage c) {
		QueryWrapper<Contentmessage> qwQueryWrapper = new QueryWrapper<Contentmessage>();
		qwQueryWrapper.eq("contentid", c.getContentid());
		contentmessageService.remove(qwQueryWrapper);
		return "ok";
	}
	
	@RequestMapping("batchDel")
	public String batchDel(@RequestBody QueryVo vo) {
		if(vo.getDelIds()!=null) {
			for(Integer id:vo.getDelIds()) {
				contentmessageService.removeById(id);
			}
		}
		return "ok";
	}
}

