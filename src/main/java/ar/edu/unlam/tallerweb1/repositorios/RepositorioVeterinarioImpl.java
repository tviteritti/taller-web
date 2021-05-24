package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Veterinario;
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
	public Veterinario consultarVeterinario(Veterinario veterinario) {
		
		final Session session = sessionFactory.getCurrentSession();
		return (Veterinario) session.createCriteria(Veterinario.class)
				.add(Restrictions.eq("email", veterinario.getEmail()))
				.add(Restrictions.eq("password", veterinario.getPassword()))
				.uniqueResult();
		
	}


	@Override
	@Transactional
	public List<Veterinario> getVeterinarios() {
		final Session session = sessionFactory.getCurrentSession();
		Query<Veterinario> miQuery=session.createQuery("from veterinario", Veterinario.class);
		List<Veterinario> veterinarios=miQuery.getResultList();
		return veterinarios;
	}

	@Override
	@Transactional
	public void registrarOMOdificarVeterinario(Veterinario veterinario) {
		final Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(veterinario);
		
	}

	@Override
	@Transactional
	public Veterinario getVeterinario(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		Veterinario veterinario = session.get(Veterinario.class, id);
		
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
