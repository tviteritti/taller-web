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
	private Mascota mascota;
	
	private String fecha;
	private String horario;
	private String servicio;
	private String estado;
	
//	private Date fecha;
//	private Time horario;

	
//	public void setFecha(Date fecha) {
//		this.fecha = fecha;
//	}
//	public void setHorario(Time horario) {
//		this.horario = horario;
//	}
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
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
//	public Paciente getPaciente() {
//		return paciente;
//	}
//	public void setPaciente(Paciente paciente) {
//		this.paciente = paciente;
//	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
//	public Date getFecha() {
//		return fecha;
//	}
//	public Time getHorario() {
//		return horario;
//	}
//	public Duenio getDuenio() {
//		return duenio;
//	}
//	public void setDuenio(Duenio duenio) {
//		this.duenio = duenio;
//	}
	public Mascota getMascota() {
		return mascota;
	}
	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}
	
	
	
	
	
	







}