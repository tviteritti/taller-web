package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;

@Repository
public class RepositorioNotificacionesImpl  implements RepositorioNotificaciones{
	
	
	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioNotificacionesImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

    @Override
	public void cargarNotificacion(Notificacion notificacion) {
		
		sessionFactory.getCurrentSession().save(notificacion);
		
	}

	@Override
	public List<Notificacion> listarNotificacionesPorDuenio(Long idDuenio) {
		
		List<Notificacion> notificaciones = (List<Notificacion>) sessionFactory.getCurrentSession()
				 .createCriteria(Notificacion.class)
				 .createAlias("duenio", "dBuscado")
				 .add(Restrictions.eq("dBuscado.id", idDuenio))
				 .list();
		
		return notificaciones;
	}
	
	@Override
	public Notificacion buscarNotificacion(Long idNotificacion) {
		
		Notificacion notificacion = (Notificacion) sessionFactory.getCurrentSession()
				 .createCriteria(Notificacion.class)
				 .add(Restrictions.eq("id", idNotificacion))
				 .list();
		return notificacion;
	}
	
	
	

}