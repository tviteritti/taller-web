package ar.edu.unlam.tallerweb1.modelo;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Turno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Usuario veterinario;
	
	@ManyToOne
	private Usuario duenio;

	private Date fecha;
	private Time horario;
	private String servicio;
	private Boolean estado;
	
	
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Usuario getVeterinario() {
		return veterinario;
	}
	public void setVeterinario(Usuario veterinario) {
		this.veterinario = veterinario;
	}
	public Usuario getDuenio() {
		return duenio;
	}
	public void setDuenio(Usuario duenio) {
		this.duenio = duenio;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Time getHorario() {
		return horario;
	}
	public void setHorario(Time horario) {
		this.horario = horario;
	}
	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

}