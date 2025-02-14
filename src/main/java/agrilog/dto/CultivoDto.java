package agrilog.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CultivoDto {

	private Long cultivoId;
	private String nombreCultivo;
	private Integer cantidad;
	private String descripcion;
	private LocalDate fechaSiembra;
	private LocalDateTime fechaRegistro;
	private String nombreParcela;

	
	
	/**
	 * 
	 */
	public CultivoDto() {
		super();
	}

	public CultivoDto(Long cultivoId, String nombreCultivo, Integer cantidad, String descripcion, 
                      LocalDate fechaSiembra, LocalDateTime fechaRegistro, String nombreParcela) {
        this.cultivoId = cultivoId;
        this.nombreCultivo = nombreCultivo;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.fechaSiembra = fechaSiembra;
        this.fechaRegistro = fechaRegistro;
        this.nombreParcela = nombreParcela;
    }

	/**
	 * @return the cultivoId
	 */
	public Long getCultivoId() {
		return cultivoId;
	}

	/**
	 * @param cultivoId the cultivoId to set
	 */
	public void setCultivoId(Long cultivoId) {
		this.cultivoId = cultivoId;
	}

	/**
	 * @return the nombreCultivo
	 */
	public String getNombreCultivo() {
		return nombreCultivo;
	}

	/**
	 * @param nombreCultivo the nombreCultivo to set
	 */
	public void setNombreCultivo(String nombreCultivo) {
		this.nombreCultivo = nombreCultivo;
	}

	/**
	 * @return the cantidad
	 */
	public Integer getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
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
	 * @return the fechaSiembra
	 */
	public LocalDate getFechaSiembra() {
		return fechaSiembra;
	}

	/**
	 * @param fechaSiembra the fechaSiembra to set
	 */
	public void setFechaSiembra(LocalDate fechaSiembra) {
		this.fechaSiembra = fechaSiembra;
	}

	/**
	 * @return the fechaRegistro
	 */
	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * @param fechaRegistro the fechaRegistro to set
	 */
	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	/**
	 * @return the nombreParcela
	 */
	public String getNombreParcela() {
		return nombreParcela;
	}

	/**
	 * @param nombreParcela the nombreParcela to set
	 */
	public void setNombreParcela(String nombreParcela) {
		this.nombreParcela = nombreParcela;
	}

}
