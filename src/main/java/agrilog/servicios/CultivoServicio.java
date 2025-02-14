package agrilog.servicios;

import java.time.LocalDateTime;
import java.util.List;

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

		ParcelaModelo parcela = parcelaRepositorio.findByNombreAndUsuarioId(cultivo.getParcelaId().getNombre(),
				usuario);

		if (parcela == null) {
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

	public void cultivoModificar(CultivoModelo cultivo, String correoUsuario) throws Exception {

		if (correoUsuario == null) {
			throw new IllegalArgumentException("Usuario no autenticado");
		}

		UsuarioModelo usuario = usuarioRepositorio.findByCorreo(correoUsuario);
		if (usuario == null) {
			throw new IllegalArgumentException("Usuario no encontrado");
		}

		CultivoModelo cultivoExistente = cultivoRepositorio.findById(cultivo.getCultivoId())
				.orElseThrow(() -> new IllegalArgumentException("Cultivo no encontrado"));

		if (cultivo.getParcelaId() != null && cultivo.getParcelaId().getNombre() != null) {
			ParcelaModelo parcela = parcelaRepositorio.findByNombreAndUsuarioId(cultivo.getParcelaId().getNombre(),
					usuario);

			if (parcela == null) {
				parcela = new ParcelaModelo();
				parcela.setUsuarioId(usuario);
				parcela.setNombre(cultivo.getParcelaId().getNombre());
				parcela.setFechaRegistro(LocalDateTime.now());
				parcela = parcelaRepositorio.save(parcela);
			}

			cultivoExistente.setParcelaId(parcela);
		}

		// Modificar solo los campos permitidos
		if (cultivo.getNombre() != null) {
			cultivoExistente.setNombre(cultivo.getNombre());
		}

		if (cultivo.getCantidad() > 0) {
			cultivoExistente.setCantidad(cultivo.getCantidad());
		}

		if (cultivo.getDescripcion() != null) {
			cultivoExistente.setDescripcion(cultivo.getDescripcion());
		}

		if (cultivo.getFechaSiembra() != null) {
			cultivoExistente.setFechaSiembra(cultivo.getFechaSiembra());
		}

		// Guardar los cambios
		cultivoRepositorio.save(cultivoExistente);

	}

	public void cultivoEliminar(Long cultivoId, String correoUsuario) throws Exception {
		if (correoUsuario == null) {
			throw new IllegalArgumentException("Usuario no autenticado");
		}

		UsuarioModelo usuario = usuarioRepositorio.findByCorreo(correoUsuario);
		if (usuario == null) {
			throw new IllegalArgumentException("Usuario no encontrado");
		}

		CultivoModelo cultivo = cultivoRepositorio.findById(cultivoId)
				.orElseThrow(() -> new IllegalArgumentException("Cultivo no encontrado"));

		if (!cultivo.getParcelaId().getUsuarioId().equals(usuario)) {
			throw new IllegalArgumentException("No tienes permisos para eliminar este cultivo");
		}

		cultivoRepositorio.delete(cultivo);
	}

	public List<CultivoModelo> obtenerCultivosPorUsuario(String correoUsuario) throws Exception {
	    if (correoUsuario == null) {
	        throw new IllegalArgumentException("Usuario no autenticado");
	    }
	    UsuarioModelo usuario = usuarioRepositorio.findByCorreo(correoUsuario);
	    if (usuario == null) {
	        throw new IllegalArgumentException("Usuario no encontrado");
	    }
	    return cultivoRepositorio.findAllByUsuarioId(usuario.getUsuarioId());
	}


}
