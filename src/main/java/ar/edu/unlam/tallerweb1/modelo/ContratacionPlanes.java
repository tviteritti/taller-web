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
public class ContratacionPlanes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Usuario duenio;
	@ManyToOne
	private Planes plan;
	
	@Temporal(TemporalType.DATE)
	private Date desde;
	@Temporal(TemporalType.DATE)
	private Date hasta;
	
	private Double valor;
	private Double pago;
	@Temporal(TemporalType.DATE)
	private Date fechaPago;
	
	private Double valorExtra;
	private Double pagoExtra;
	@Temporal(TemporalType.DATE)
	private Date fechaPagoExtra;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Usuario getDuenio() {
		return duenio;
	}
	public void setDuenio(Usuario duenio) {
		this.duenio = duenio;
	}
	public Planes getPlan() {
		return plan;
	}
	public void setPlan(Planes plan) {
		this.plan = plan;
	}
	public Date getDesde() {
		return desde;
	}
	public void setDesde(Date desde) {
		this.desde = desde;
	}
	public Date getHasta() {
		return hasta;
	}
	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Double getPago() {
		return pago;
	}
	public void setPago(Double pago) {
		this.pago = pago;
	}
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	public Double getValorExtra() {
		return valorExtra;
	}
	public void setValorExtra(Double valorExtra) {
		this.valorExtra = valorExtra;
	}
	public Double getPagoExtra() {
		return pagoExtra;
	}
	public void setPagoExtra(Double pagoExtra) {
		this.pagoExtra = pagoExtra;
	}
	public Date getFechaPagoExtra() {
		return fechaPagoExtra;
	}
	public void setFechaPagoExtra(Date fechaPagoExtra) {
		this.fechaPagoExtra = fechaPagoExtra;
	}
	
	
}
