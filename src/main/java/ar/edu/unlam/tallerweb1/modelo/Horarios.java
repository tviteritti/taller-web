package ar.edu.unlam.tallerweb1.modelo;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Horarios {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIME)
	private Date hora_inicio;
	@Temporal(TemporalType.TIME)
	private Date hora_fin;

	private Integer duracion_sesion;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getHora_inicio() {
		return hora_inicio;
	}
	public void setHora_inicio(Date hora_inicio) {
		this.hora_inicio = hora_inicio;
	}
	public Date getHora_fin() {
		return hora_fin;
	}
	public void setHora_fin(Date hora_fin) {
		this.hora_fin = hora_fin;
	}
	public Integer getDuracion_sesion() {
		return duracion_sesion;
	}
	public void setDuracion_sesion(Integer duracion_sesion) {
		this.duracion_sesion = duracion_sesion;
	}
	
}
