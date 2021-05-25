package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Paciente extends Usuario  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/*private String nombre; si paciente y usuario tienen nombre es mejor que lo hereden de usuario
	private Integer edad; la edad se saca con la fechaNacimiento por que si no se tiene que cambiar todos los años*/
	private String fechaNacimiento;
	
	@ManyToOne
	private Duenio duenio;
	
	@ManyToOne
	private TipoAnimal tipo;
	
	@ManyToOne 
	private HistoriaClinica hc;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/*public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}*/
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public TipoAnimal getTipo() {
		return tipo;
	}
	public void setTipo(TipoAnimal tipo) {
		this.tipo = tipo;
	}
	public Duenio getDuenio() {
		return duenio;
	}
	public void setDuenio(Duenio duenio) {
		this.duenio = duenio;
	}
	public HistoriaClinica getHc() {
		return hc;
	}
	public void setHc(HistoriaClinica hc) {
		this.hc = hc;
	}
	
}
