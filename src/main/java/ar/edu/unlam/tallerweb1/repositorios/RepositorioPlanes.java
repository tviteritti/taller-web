package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.ContratacionPlanes;
import ar.edu.unlam.tallerweb1.modelo.Planes;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioPlanes {
	
	List<Planes> listarPlanes();
	List<ContratacionPlanes> listarContrataciones();
	void accederPlan(Long planId, Long duenioId);
	void pagarPlan(Long contratacionId);
	Planes devolverPlanDeDuenio(Usuario duenio);
	ContratacionPlanes devolverContratacionDeDuenio(Usuario duenio);
	ContratacionPlanes devolverContratacionPorId(Long id);
	void aumentarTurnosTomados(Long idContratacion);
	void aumentarValorExtra(Long idContratacion, Double costo);
	
}
