package ar.edu.unlam.tallerweb1.modelo;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
public class Mascota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private byte[] historial_clinico; 
	
	@Temporal(TemporalType.DATE)
	private Date fecha_nacimiento;
	
	private String nombre;
	
	@ManyToOne
	private Usuario duenio;
	
	@ManyToOne
	private TipoAnimal tipo;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	public byte[] getHistorial_clinico() {
		return historial_clinico;
	}
	public void setHistorial_clinico(byte[] historial_clinico) {
		this.historial_clinico = historial_clinico;
	}
	public Usuario getDuenio() {
		return duenio;
	}
	public void setDuenio(Usuario duenio) {
		this.duenio = duenio;
	}
	public TipoAnimal getTipo() {
		return tipo;
	}
	public void setTipo(TipoAnimal tipo) {
		this.tipo = tipo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
