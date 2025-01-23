/**
 * 
 */
package agrilog.controladores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Inicio de la aplicacion spring
 */
@SpringBootApplication
@EntityScan(basePackages = "agrilog.modelos")
public class Inicio {

	public static void main(String[] args) {
		SpringApplication.run(Inicio.class, args);
	}

}
