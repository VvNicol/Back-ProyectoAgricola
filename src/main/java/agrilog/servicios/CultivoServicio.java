package agrilog.servicios;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agrilog.modelos.CultivoModelo;
import agrilog.modelos.ParcelaModelo;
import agrilog.modelos.UsuarioModelo;
import agrilog.repositorios.CultivoRepositorio;
import agrilog.repositorios.ParcelaRepositorio;
import agrilog.repositorios.UsuarioRepositorio;

@Service
public class CultivoServicio implements CultivoInterfaz {
	
	@Autowired
	private CultivoRepositorio cultivoRepositorio;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private ParcelaRepositorio parcelaRepositorio;
	
	@Override
	public CultivoModelo registrarCultivo(CultivoModelo cultivo, String correoUsuario) throws Exception {
		
		if (correoUsuario == null) {
			throw new IllegalArgumentException("Usuario no autenticado");
		}
		
		UsuarioModelo usuario = usuarioRepositorio.findByCorreo(correoUsuario);
		
		if (usuario == null) {
			throw new IllegalArgumentException("Usuario no encontrado");
		}
		
		ParcelaModelo parcela = parcelaRepositorio.findByNombreAndUsuarioId(cultivo.getParcelaId().getNombre(), usuario);
		
		if(parcela == null) {
			parcela = new ParcelaModelo();
			parcela.setUsuarioId(usuario);
			parcela.setNombre(cultivo.getParcelaId().getNombre());
			parcela.setFechaRegistro(LocalDateTime.now());
			parcela = parcelaRepositorio.save(parcela);
		}
		
		cultivo.setParcelaId(parcela);
		cultivo.setFechaRegistro(LocalDateTime.now());
		
		CultivoModelo cultivoGuardar = cultivoRepositorio.save(cultivo);
		
		return cultivoGuardar;
	}


}
