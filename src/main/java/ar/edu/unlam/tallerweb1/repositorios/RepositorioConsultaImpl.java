package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Consulta;
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
	public List<Consulta> listarConsultaPorDuenio(Long idDuenio) {
		
		List<Consulta> consultas = (List<Consulta>) sessionFactory.getCurrentSession()
				 .createCriteria(Consulta.class)
				 .createAlias("duenio", "dBuscado")
				 .add(Restrictions.eq("dBuscado.id", idDuenio))
				 .add(Restrictions.isNotNull("asunto"))
				 .add(Restrictions.isNotNull("descripcion"))
				 .list();
		return consultas;
	}

	@Override
	public List<Consulta> listarConsultas() {
		
		List<Consulta> consultas = (List<Consulta>) sessionFactory.getCurrentSession()
				 .createCriteria(Consulta.class)
				 .add(Restrictions.isNotNull("asunto"))
				 .add(Restrictions.isNotNull("descripcion"))
				 .list();
		return consultas;
	}

	@Override
	public void eliminarConsultasNulas(Consulta consulta) {
		final Session session = sessionFactory.getCurrentSession();
		 session.delete(consulta);
	
	}

	@Override
	public Consulta buscarConsulta(Long idConsulta) {
		
		Consulta consulta = (Consulta) sessionFactory.getCurrentSession()
				 .createCriteria(Consulta.class)
				 .add(Restrictions.eq("id",idConsulta))
				 .uniqueResult();
		return consulta;
		
	}
	
	

}
