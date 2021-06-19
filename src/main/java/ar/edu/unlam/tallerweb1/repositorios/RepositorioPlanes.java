package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Planes;

public interface RepositorioPlanes {
	
	List<Planes> listarPlanes();
	void accederPlan(Long planId, Long duenioId);

}
