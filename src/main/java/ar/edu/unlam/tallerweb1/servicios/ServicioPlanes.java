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
}
