package cool.pbw.dm.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cool.pbw.dm.config.Props;
import cool.pbw.dm.entity.Usermessage;
import cool.pbw.dm.service.IUsermessageService;
import cool.pbw.dm.service.impl.MailServiceImpl;

/**
 * <p>
 * InnoDB free: 9216 kB 前端控制器
 * </p>
 *
 * @author pbw
 * @since 2021-04-13
 */
@RestController
@RequestMapping("/dm/usermessage")
public class UsermessageController {
	@Autowired
	IUsermessageService usermessageService;
	@Autowired
	MailServiceImpl mailService;
	@Autowired
	Props props;
	
	@RequestMapping("/login")
	public Usermessage login(Usermessage user) {
		QueryWrapper<Usermessage> queryWrapper=new QueryWrapper<Usermessage>();
		queryWrapper.eq("username", user.getUsername()).eq("userpwd",user.getUserpwd());
		queryWrapper.select("userimg,uservip,userid,usertype");
		Usermessage usermessage = usermessageService.getOne(queryWrapper);
		if(usermessage != null) {
			return usermessage;
		}
		return null;
	}
	
	@RequestMapping("/sendMail")
	public String sendMail(Usermessage user,HttpSession session) {
		String codes = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer sb = new StringBuffer("");
		Random random = new Random();
		for(int i =0;i<6;i++) {
			int index = random.nextInt(codes.length());
			char c = codes.charAt(index);
			sb.append(c);
		}
		String content = sb.toString();
		session.setAttribute("vericode", content);
		mailService.sendSimpleMail(user.getUseremail(), "头条新闻注册验证码", content);
		return "";
	}
	
	@RequestMapping("/checkName")
	public String checkName(@RequestBody Usermessage user) {
		QueryWrapper<Usermessage> queryWrapper = new QueryWrapper<Usermessage>();
		queryWrapper.eq("username", user.getUsername());
		queryWrapper.select("username");
		Usermessage newsuser = usermessageService.getOne(queryWrapper);
		if(newsuser!=null) {
			return "exist";
		}
		return "ok";
	}
	
	@RequestMapping("/regist")
	public String regist(Usermessage user,HttpSession session,MultipartFile imgFile) {
		String vericode=(String) session.getAttribute("vericode");
//		System.out.println(vericode);
		if(user.getVericode().equals(vericode)) {
			if(imgFile!=null) {
				String originalFilename = imgFile.getOriginalFilename();
				String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
				String newName = UUID.randomUUID().toString().replace("-", "")+suffix;
				try {
					imgFile.transferTo(new File(props.getUploadPath()+newName));
					user.setUserimg(newName);
					user.setUserregtime(new Date());
					user.setUservip(0);
					user.setUsertype(0);
					usermessageService.save(user);
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
		}
		return "fail";
	}
	
	
	@RequestMapping("/update")
//	这个oldname可以不用传入，自接就是在formData中就行
	public String update(Usermessage user,String oldname,HttpSession session,MultipartFile imgFile) {
		String vericode=(String) session.getAttribute("vericode");
		if(user.getVericode().equals(vericode)) {
			if(imgFile!=null) {
				String originalFilename = imgFile.getOriginalFilename();
				String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
				String newName = UUID.randomUUID().toString().replace("-", "")+suffix;
				try {
					imgFile.transferTo(new File(props.getUploadPath()+newName));
					user.setUserimg(newName);
					usermessageService.update(user, oldname);
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
		}
		return "fail";
	}
}

