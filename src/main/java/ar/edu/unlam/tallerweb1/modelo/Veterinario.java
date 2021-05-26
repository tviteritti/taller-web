package ar.edu.unlam.tallerweb1.modelo;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.mysql.cj.jdbc.Blob;

@Entity
public class Veterinario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private byte[] foto;
	private Date dias_que_atiende;
	private Time horario_que_atiende;
	
	private String nombre;
	private String apellido;
	private String descripcion;
	
	@ManyToOne
	private Zona zona;
	
	
	private String direccion;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Date getDias_que_atiende() {
		return dias_que_atiende;
	}

	public void setDias_que_atiende(Date dias_que_atiende) {
		this.dias_que_atiende = dias_que_atiende;
	}

	public Time getHorario_que_atiende() {
		return horario_que_atiende;
	}

	public void setHorario_que_atiende(Time horario_que_atiende) {
		this.horario_que_atiende = horario_que_atiende;
	}

   public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



}
