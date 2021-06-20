package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.ContratacionPlanes;
import ar.edu.unlam.tallerweb1.modelo.Planes;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioPlanes {

	List<Planes> listarPlanes();
	List<ContratacionPlanes> listarContrataciones();
	Boolean mostrarPlanesOContrataciones(Usuario duenio);
	void accederPlan(Long planId, Long duenioId);
	void pagarPlan(Long contratacionId);
	void pagarPlanExtra(Long contratacionId);
	Planes devolverPlanDeDuenio(Usuario duenio);
	ContratacionPlanes devolverContratacionDeDuenio(Usuario duenio);
	void aumentarTurnosTomados(Long idContratacion);
	void aumentarValorExtra(Long idContratacion, Double costo);
}
