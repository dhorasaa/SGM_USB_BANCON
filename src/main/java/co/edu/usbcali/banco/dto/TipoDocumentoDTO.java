package co.edu.usbcali.banco.dto;

public class TipoDocumentoDTO {
	
	private Long tdocId;
	private String nombre;
	private String activo;
		
	public TipoDocumentoDTO() {
		super();
	}
	
	public TipoDocumentoDTO(long tdocId, String activo, String nombre) {
		super();
		this.tdocId = tdocId;
		this.nombre = nombre;	
		this.activo = activo;		
	}

	public Long getTdocId() {
		return tdocId;
	}

	public void setTdocId(Long tdocId) {
		this.tdocId = tdocId;
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
