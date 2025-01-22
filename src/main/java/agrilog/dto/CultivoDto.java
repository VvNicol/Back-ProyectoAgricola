package agrilog.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CultivoDto {

	private Long cultivo_id;

	private Long parcela_id;

	private String nombre;

	private String descripcion;

	private LocalDate fecha_vencimiento;

	private LocalDateTime fecha_registro;

	/**
	 * Constructores
	 */
	public CultivoDto() {

	}

	/**
	 * Getters y Setters
	 */

	/**
	 * @return the cultivo_id
	 */
	public Long getCultivo_id() {
		return cultivo_id;
	}

	/**
	 * @param cultivo_id the cultivo_id to set
	 */
	public void setCultivo_id(Long cultivo_id) {
		this.cultivo_id = cultivo_id;
	}

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
	 * @return the fecha_vencimiento
	 */
	public LocalDate getFecha_vencimiento() {
		return fecha_vencimiento;
	}

	/**
	 * @param fecha_vencimiento the fecha_vencimiento to set
	 */
	public void setFecha_vencimiento(LocalDate fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
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
