package agrilog.seguridad;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import agrilog.utilidades.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    
    private static final ThreadLocal<String> correoUsuario = new ThreadLocal<>();
    private static final ThreadLocal<String> rolUsuario = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest peticion, HttpServletResponse respuesta, Object handler) throws Exception {
        
        String token = peticion.getHeader("Authorization");
        
        if (token == null || !token.startsWith("Bearer ")) {
            respuesta.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token no proporcionado");
            return false;
        }

        token = token.substring(7);

        if (JwtUtil.validarToken(token)) {
            String correo = JwtUtil.obtenerCorreDesdeToken(token);
            String rol = JwtUtil.obtenerRolDesdeToken(token);
            
            correoUsuario.set(correo);
            rolUsuario.set(rol);
            
            return true;
        } else {
            respuesta.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido o expirado");
            return false;
        }
    }

    public static String obtenerCorreoUsuario() {
        return correoUsuario.get();
    }

    public static String obtenerRolUsuario() {
        return rolUsuario.get();
    }

    public static void limpiar() {
        correoUsuario.remove();
        rolUsuario.remove();
    }
}
