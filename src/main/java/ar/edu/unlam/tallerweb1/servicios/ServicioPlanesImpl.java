package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Planes;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioMascotas;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPlanes;

@Service("ServicioPlanes")
@Transactional
public class ServicioPlanesImpl implements ServicioPlanes{

	private RepositorioPlanes servicioPlanesDao;

	@Autowired
	public ServicioPlanesImpl(RepositorioPlanes servicioPlanesDao){
		this.servicioPlanesDao = servicioPlanesDao;
		
	}
	
	@Override
	public List<Planes> listarPlanes() {
		return servicioPlanesDao.listarPlanes();
	}

	@Override
	public void accederPlan(Long planId, Long duenioId) {
		servicioPlanesDao.accederPlan(planId, duenioId);
		
	}

}
