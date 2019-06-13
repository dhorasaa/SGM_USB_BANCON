package co.edu.usbcali.banco.dto;

import java.math.BigDecimal;

public class UsuarioDTO {
	
	private String usuUsuario;
	private String clave;
	private BigDecimal identificacion;
	private String nombre;
	private String activo;	
	private Long tipoUsuario;
	
	public UsuarioDTO() {
		super();
	}
	
	public UsuarioDTO(String usuUsuario,String clave,BigDecimal identificacion,String nombre,String activo,Long tipoUsuario) {
		super();
		this.usuUsuario = usuUsuario;
		this.clave = clave;
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.activo = activo;		
		this.tipoUsuario = tipoUsuario;
	}

	public String getUsuUsuario() {
		return usuUsuario;
	}

	public void setUsuUsuario(String usuUsuario) {
		this.usuUsuario = usuUsuario;
	}
	
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public BigDecimal getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(BigDecimal identificacion) {
		this.identificacion = identificacion;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Long getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(Long tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}
