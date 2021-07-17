package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String asunto;
	private String descripcion;
	private String userRespuesta;
	
	@ManyToOne
	private Usuario usuario;
	
	@OneToOne
	private Consulta respuesta;
	
	private String tipoConsulta; 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public String getUserRespuesta() {
		return userRespuesta;
	}

	public void setUserRespuesta(String userRespuesta) {
		this.userRespuesta = userRespuesta;
	}

	public Consulta getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(Consulta respuesta) {
		this.respuesta = respuesta;
	}

	public String getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	
}
