package ec.yavirac.yavigestion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class YavigestionApplication {

	public static void main(String[] args) {
		SpringApplication.run(YavigestionApplication.class, args);
	}

}
