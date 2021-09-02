package cool.pbw.dm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfiger implements WebMvcConfigurer{
	static final String ORIGINS[] = new String[] {"GET","POST","PUT","DELETE"};
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")  //所有当前站点的请求地址，支持跨域访问
				.allowedOriginPatterns("*") //所有的外部域都可以跨域访问。如果是localhost则很难配置，因为在跨域请求的？？
				.allowCredentials(true)  //是否支持跨域用户凭证
				.allowedMethods(ORIGINS) //当前站点支持的跨域请求类型是什么
				.maxAge(3600);  //超时时长设置为1小时，时间单位是秒
	}
}
