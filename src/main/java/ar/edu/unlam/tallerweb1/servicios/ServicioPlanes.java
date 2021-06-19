package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Planes;

public interface ServicioPlanes {

	List<Planes> listarPlanes();
	void accederPlan(Long planId, Long duenioId);
}
