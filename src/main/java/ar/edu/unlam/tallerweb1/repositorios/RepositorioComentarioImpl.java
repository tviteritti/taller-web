package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Comentario;
import ar.edu.unlam.tallerweb1.modelo.Consulta;

@Repository
public class RepositorioComentarioImpl implements RepositorioComentario{
	
	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioComentarioImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Comentario> listarComentariosPorConsulta(Long idConsulta) {
		
		List<Comentario> comentarios=  (List<Comentario>) sessionFactory.getCurrentSession()
									 .createCriteria(Comentario.class)
									 .createAlias("consulta", "cBuscada")
									 .add(Restrictions.eq("cBuscada.id", idConsulta))
									 .list();
		return comentarios;
	}
    
    
    

}
