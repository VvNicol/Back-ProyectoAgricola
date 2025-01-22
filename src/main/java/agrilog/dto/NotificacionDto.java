package agrilog.dto;

import java.sql.Date;

public class NotificacionDto {
	
	private Long notificacion_id;
	
	private Long cultivo_id;
	
	private String mensaje;
	
	private int cantidad;
	
	private Date fecha_mensaje;

	/**
	 * Constructores
	 */
	public NotificacionDto() {
		
	}

	/**
	 * Getters y Setters
	 */
	
	/**
	 * @return the notificacion_id
	 */
	public Long getNotificacion_id() {
		return notificacion_id;
	}

	/**
	 * @param notificacion_id the notificacion_id to set
	 */
	public void setNotificacion_id(Long notificacion_id) {
		this.notificacion_id = notificacion_id;
	}

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
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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
	 * @return the fecha_mensaje
	 */
	public Date getFecha_mensaje() {
		return fecha_mensaje;
	}

	/**
	 * @param fecha_mensaje the fecha_mensaje to set
	 */
	public void setFecha_mensaje(Date fecha_mensaje) {
		this.fecha_mensaje = fecha_mensaje;
	}
	
	
	
}
