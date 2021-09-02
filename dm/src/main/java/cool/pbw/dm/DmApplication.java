package cool.pbw.dm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cool.pbw.dm.mapper")
public class DmApplication {

	public static void main(String[] args) {
		SpringApplication.run(DmApplication.class, args);
	}

}
