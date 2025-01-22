package agrilog.dto;

import java.time.LocalDateTime;

public class ParcelaDto {
	
	private Long parcela_id;
	
	private Long usuario_id;
	
	private String nombre;
	
	private String descripcion;
	
	private int cantidad;
	
	private LocalDateTime fecha_registro;

	/**
	 * Constructores
	 */
	public ParcelaDto() {
		
	}

	/**
	 * Getters y Setters
	 */
	
	/**
	 * @return the parcela_id
	 */
	public Long getParcela_id() {
		return parcela_id;
	}

	/**
	 * @param parcela_id the parcela_id to set
	 */
	public void setParcela_id(Long parcela_id) {
		this.parcela_id = parcela_id;
	}

	/**
	 * @return the usuario_id
	 */
	public Long getUsuario_id() {
		return usuario_id;
	}

	/**
	 * @param usuario_id the usuario_id to set
	 */
	public void setUsuario_id(Long usuario_id) {
		this.usuario_id = usuario_id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the fecha_registro
	 */
	public LocalDateTime getFecha_registro() {
		return fecha_registro;
	}

	/**
	 * @param fecha_registro the fecha_registro to set
	 */
	public void setFecha_registro(LocalDateTime fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	
	
	
}
