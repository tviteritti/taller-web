package ar.edu.unlam.tallerweb1.repositorios;

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
		
		contratacion.setDuenio(duenio);
		contratacion.setPlan(plan);
		
		session.saveOrUpdate(contratacion);
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
