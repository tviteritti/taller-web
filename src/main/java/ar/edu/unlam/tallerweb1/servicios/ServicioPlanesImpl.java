package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.ContratacionPlanes;
import ar.edu.unlam.tallerweb1.modelo.Planes;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
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
	public List<ContratacionPlanes> listarContrataciones() {
		return servicioPlanesDao.listarContrataciones();
	}

	@Override
	public void accederPlan(Long planId, Long duenioId) {
		servicioPlanesDao.accederPlan(planId, duenioId);
		
	}

	@Override
	public Boolean mostrarPlanesOContrataciones(Usuario duenio) {
		List<ContratacionPlanes> contrataciones = servicioPlanesDao.listarContrataciones();
		
		for (ContratacionPlanes contratacionPlanes : contrataciones) {
			if(contratacionPlanes.getDuenio().getId() == duenio.getId()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void pagarPlan(Long contratacionId) {
		servicioPlanesDao.pagarPlan(contratacionId);
		
	}

	@Override
	public Planes devolverPlanDeDuenio(Usuario duenio) {
		return servicioPlanesDao.devolverPlanDeDuenio(duenio);
	}

	@Override
	public ContratacionPlanes devolverContratacionDeDuenio(Usuario duenio) {
		return servicioPlanesDao.devolverContratacionDeDuenio(duenio);
	}

	@Override
	public void aumentarTurnosTomados(Long idContratacion) {
		servicioPlanesDao.aumentarTurnosTomados(idContratacion);
		
	}



}
