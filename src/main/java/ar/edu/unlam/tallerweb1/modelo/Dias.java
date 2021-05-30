package ar.edu.unlam.tallerweb1.modelo;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Dias {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Horarios lunes;
	@ManyToOne
	private Horarios martes;
	@ManyToOne
	private Horarios miercoles;
	@ManyToOne
	private Horarios jueves;
	@ManyToOne
	private Horarios viernes;
	@ManyToOne
	private Horarios sabado;
	@ManyToOne
	private Horarios domingo;
	
	@ManyToOne
	private Usuario veterinario;
	

	public Usuario getVeterinario() {
		return veterinario;
	}

	public void setVeterinario(Usuario veterinario) {
		this.veterinario = veterinario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Horarios getLunes() {
		return lunes;
	}

	public void setLunes(Horarios lunes) {
		this.lunes = lunes;
	}

	public Horarios getMartes() {
		return martes;
	}

	public void setMartes(Horarios martes) {
		this.martes = martes;
	}

	public Horarios getMiercoles() {
		return miercoles;
	}

	public void setMiercoles(Horarios miercoles) {
		this.miercoles = miercoles;
	}

	public Horarios getJueves() {
		return jueves;
	}

	public void setJueves(Horarios jueves) {
		this.jueves = jueves;
	}

	public Horarios getViernes() {
		return viernes;
	}

	public void setViernes(Horarios viernes) {
		this.viernes = viernes;
	}

	public Horarios getSabado() {
		return sabado;
	}

	public void setSabado(Horarios sabado) {
		this.sabado = sabado;
	}

	public Horarios getDomingo() {
		return domingo;
	}

	public void setDomingo(Horarios domingo) {
		this.domingo = domingo;
	}

	public Usuario getUsuario() {
		return veterinario;
	}

	public void setUsuario(Usuario usuario) {
		this.veterinario = usuario;
	}
}
