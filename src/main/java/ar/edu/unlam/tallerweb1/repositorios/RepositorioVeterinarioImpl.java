package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;


@Repository("RepositorioVeterinario")
public class RepositorioVeterinarioImpl implements RepositorioVeterinario{
	
	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioVeterinarioImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}


	@Override
	@Transactional
	public List<Usuario> getVeterinarios() {
		final Session session = sessionFactory.getCurrentSession();
		Query<Usuario> miQuery=session.createQuery("from veterinario", Usuario.class);
		List<Usuario> veterinarios=miQuery.getResultList();
		return veterinarios;
	}

	@Override
	@Transactional
	public void registrarOMOdificarVeterinario(Usuario veterinario) {
		final Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(veterinario);
		
	}

	@Override
	@Transactional
	public Usuario getVeterinario(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		Usuario veterinario = session.get(Usuario.class, id);
		
		return veterinario;
	}

	@Override
	@Transactional
	public void eliminarVeterinario(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		
		Query consulta = session.createQuery("delete from Veterinario where id=:idVeterinario");
		
		consulta.setParameter("idVeterinario", id);
		
		consulta.executeUpdate();
		
	}

}
