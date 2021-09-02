package cool.pbw.dm.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix="cool.pbw.dm")
//这里是前缀,得和cool.pbw.dm.uploadPath=E:\\E_practice2\\PBW\\uploadFile2\\uploadFile2\\保持一致
@Data
public class Props {
	private String uploadPath;//文件上传路径
}
