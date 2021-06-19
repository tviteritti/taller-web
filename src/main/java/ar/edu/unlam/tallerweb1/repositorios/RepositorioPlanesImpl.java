package ar.edu.unlam.tallerweb1.repositorios;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.ContratacionPlanes;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Planes;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("repositorioPlanes")
public class RepositorioPlanesImpl implements RepositorioPlanes{

	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioPlanesImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Planes> listarPlanes() {
		
		List<Planes> planes = (List<Planes>) sessionFactory.getCurrentSession()
				 .createCriteria(Planes.class)
				 .list();
		return planes;
	}
	
	@Override
	public List<ContratacionPlanes> listarContrataciones() {
		
		List<ContratacionPlanes> contrataciones = (List<ContratacionPlanes>) sessionFactory.getCurrentSession()
				 .createCriteria(ContratacionPlanes.class)
				 .list();
		return contrataciones;
	}

	@Override
	public void accederPlan(Long planId, Long duenioId) {
		final Session session = sessionFactory.getCurrentSession();
		Usuario duenio = new Usuario();
		
		duenio = (Usuario)sessionFactory.getCurrentSession()
				 .createCriteria(Usuario.class)
				 .add(Restrictions.eq( "id", duenioId))
				 .uniqueResult();
		
		Planes plan = new Planes();
		
		plan = (Planes)sessionFactory.getCurrentSession()
				 .createCriteria(Planes.class)
				 .add(Restrictions.eq( "id", planId))
				 .uniqueResult();
		
		ContratacionPlanes contratacion = new ContratacionPlanes();
		
		LocalDate fechaActual =  LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();		/*PASO LOCALDATE DATE*/
		Date dateDesde = Date.from(fechaActual.atStartOfDay(defaultZoneId).toInstant());
		
		
		LocalDate fechaHasta =  LocalDate.now();
		
		switch (plan.getDuracion()) {
		case 1:
		fechaHasta =  LocalDate.now().plusMonths(1);
			break;
		case 2:
		fechaHasta =  LocalDate.now().plusMonths(2);
			break;
		case 3:
		fechaHasta =  LocalDate.now().plusMonths(3);
			break;

		default:
			break;
		}
		
		Date dateHasta = Date.from(fechaHasta.atStartOfDay(defaultZoneId).toInstant());
		
		
		contratacion.setDuenio(duenio);
		contratacion.setPlan(plan);
		contratacion.setDesde(dateDesde);
		contratacion.setHasta(dateHasta);
		contratacion.setValor(plan.getPrecio());
		
		session.saveOrUpdate(contratacion);
		
	}

	@Override
	public void pagarPlan(Long contratacionId) {
		final Session session = sessionFactory.getCurrentSession();
		
		ContratacionPlanes contratacion = (ContratacionPlanes) sessionFactory.getCurrentSession()
			 .createCriteria(ContratacionPlanes.class)
			 .add(Restrictions.eq("id", contratacionId)).uniqueResult();
		
		LocalDate fechaActual =  LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();		/*PASO LOCALDATE DATE*/
		Date date = Date.from(fechaActual.atStartOfDay(defaultZoneId).toInstant());
		
		contratacion.setPago(contratacion.getValor());
		contratacion.setFechaPago(date);
		session.saveOrUpdate(contratacion);
		
	}

	@Override
	public Planes devolverPlanDeDuenio(Usuario duenio) {

		ContratacionPlanes contratacion = (ContratacionPlanes) sessionFactory.getCurrentSession()
				 .createCriteria(ContratacionPlanes.class)
				 .add(Restrictions.eq("duenio", duenio)).uniqueResult();
		Planes plan = contratacion.getPlan();
		
		return plan;
	}

	@Override
	public ContratacionPlanes devolverContratacionDeDuenio(Usuario duenio) {
		ContratacionPlanes contratacion = (ContratacionPlanes) sessionFactory.getCurrentSession()
				 .createCriteria(ContratacionPlanes.class)
				 .add(Restrictions.eq("duenio", duenio)).uniqueResult();
		return contratacion;
	}

	@Override
	public ContratacionPlanes devolverContratacionPorId(Long id) {
		
		ContratacionPlanes contratacion = (ContratacionPlanes) sessionFactory.getCurrentSession()
				 .createCriteria(ContratacionPlanes.class)
				 .add(Restrictions.eq("id", id)).uniqueResult();
		return contratacion;
	}

	@Override
	public void aumentarTurnosTomados(Long idContratacion) {
		final Session session = sessionFactory.getCurrentSession();
		ContratacionPlanes contratacion = (ContratacionPlanes) sessionFactory.getCurrentSession()
				 .createCriteria(ContratacionPlanes.class)
				 .add(Restrictions.eq("id", idContratacion)).uniqueResult();
		Integer turnosTomados = contratacion.getCantidadTurnosTomados();
		turnosTomados++;
		
		contratacion.setCantidadTurnosTomados(turnosTomados);
		session.saveOrUpdate(contratacion);
	}




	
	
	
	
	
	
	
	
	
	
	

}
