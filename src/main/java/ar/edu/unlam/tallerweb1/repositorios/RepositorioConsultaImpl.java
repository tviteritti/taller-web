package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Consulta;
import ar.edu.unlam.tallerweb1.modelo.ConsultaRespuesta;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository
public class RepositorioConsultaImpl implements RepositorioConsulta{
	
	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioConsultaImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void cargarConsulta(Consulta consulta) {
		final Session session = sessionFactory.getCurrentSession();
		 session.save(consulta);

	}

	@Override
	public List<Consulta> listarConsultaPorUsuario(Long idUsuario) {
		
		List<Consulta> consultas = (List<Consulta>) sessionFactory.getCurrentSession()
				 .createCriteria(Consulta.class)
				 .createAlias("usuario", "uBuscado")
				 .add(Restrictions.eq("uBuscado.id", idUsuario))
				 .list();
		return consultas;
	}

	@Override
	public List<Consulta> listarConsultas() {
		
		List<Consulta> consultas = (List<Consulta>) sessionFactory.getCurrentSession()
				 .createCriteria(Consulta.class)
				 .list();
		return consultas;
	}

	@Override
	public void eliminarConsultasNulas(Consulta consulta) {
	
		sessionFactory.getCurrentSession().delete(consulta);
	
	}

	@Override
	public Consulta buscarConsulta(Long idConsulta) {
		
		Consulta consulta = (Consulta) sessionFactory.getCurrentSession()
				 .createCriteria(Consulta.class)
				 .add(Restrictions.eq("id",idConsulta))
				 .uniqueResult();
		return consulta;
		
	}

	@Override
	public void guardarRespuesta(Long idConsulta , Consulta respuesta, String userRespuesta) {
		
		Consulta consulta = buscarConsulta(idConsulta);
		consulta.setRespuesta(respuesta);
		consulta.setUserRespuesta(userRespuesta);
		consulta.setComentario(respuesta.getDescripcion());
		respuesta.setAsunto(consulta.getAsunto());

	}


}
