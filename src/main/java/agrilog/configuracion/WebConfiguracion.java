package agrilog.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import agrilog.seguridad.JwtInterceptor;

@Configuration
public class WebConfiguracion implements WebMvcConfigurer {

	private final JwtInterceptor jwtInterceptor;
	
	public WebConfiguracion(JwtInterceptor jwtInterceptor) {
		this.jwtInterceptor = jwtInterceptor;
	}
	
	public void agregarInterceptor(InterceptorRegistry registro) {
		registro.addInterceptor(jwtInterceptor)
			.addPathPatterns("/**")
			.excludePathPatterns(
					"/inicio",					
					"/inicio/registrarse", 
					"/inicio/verificar",
					"/inicio/nueva-contrasenia",
					"/inicio/verificar-codigo",
					"/inicio/enviar-codigo",
					"/inicio/iniciar-sesion",
					"/inicio/registro");
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**")
				.allowedOrigins("http://localhost:4200")
				.allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowedHeaders("*");

	}

}
