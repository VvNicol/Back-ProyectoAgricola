/**
 * 
 */
package agrilog.controladores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Inicio de la aplicacion spring
 */

@EntityScan(basePackages = "agrilog.modelos")
@EnableJpaRepositories(basePackages = "agrilog.repositorios")
@SpringBootApplication(scanBasePackages = "agrilog")
public class Inicio {

	public static void main(String[] args) {
		SpringApplication.run(Inicio.class, args);
	}

}
