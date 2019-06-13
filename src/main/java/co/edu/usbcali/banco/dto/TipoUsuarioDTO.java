package co.edu.usbcali.banco.dto;

public class TipoUsuarioDTO {
	
	private Long tiusid;
	private String nombre;
	private String activo;	
	
	public TipoUsuarioDTO() {
		super();
	}
	
	public TipoUsuarioDTO(long tiusid, String activo, String nombre) {
		super();
		this.tiusid = tiusid;
		this.nombre = nombre;
		this.activo = activo;
	}

	public Long getTiusid() {
		return tiusid;
	}

	public void setTiusid(Long tiusid) {
		this.tiusid = tiusid;
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
	
	
	

}
