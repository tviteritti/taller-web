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

// implelemtacion del repositorio de usuarios, la anotacion @Repository indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.dao
// para encontrar esta clase.
@Repository("repositorioUsuario")
public class RepositorioUsuarioImpl implements RepositorioUsuario {

	// Como todo repositorio maneja acciones de persistencia, normalmente estará inyectado el session factory de hibernate
	// el mismo está difinido en el archivo hibernateContext.xml
	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioUsuarioImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Usuario consultarUsuario(Usuario usuario) {

		// Se obtiene la sesion asociada a la transaccion iniciada en el servicio que invoca a este metodo y se crea un criterio
		// de busqueda de Usuario donde el email y password sean iguales a los del objeto recibido como parametro
		// uniqueResult da error si se encuentran más de un resultado en la busqueda.
		final Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.createCriteria(Usuario.class)
				.add(Restrictions.eq("email", usuario.getEmail()))
				.add(Restrictions.eq("password", usuario.getPassword()))
				.uniqueResult();
	}


	@Override
	@Transactional
	public List<Usuario> getUsuarios() {
		final Session session = sessionFactory.getCurrentSession();
		Query<Usuario> miQuery=session.createQuery("from Usuario", Usuario.class);
		List<Usuario> usuarios=miQuery.getResultList();
		return usuarios;
	}
	
	@Override
	public List<Usuario> getVeterinarios() {
		final Session session = sessionFactory.getCurrentSession();
		Query<Usuario> miQuery=session.createQuery("from Usuario where rol=:rolUsuario", Usuario.class);
		String rol="veterinario";
		miQuery.setParameter("rolUsuario", rol);
		List<Usuario> usuarios=miQuery.getResultList();
		return usuarios;
	}

	@Override
	@Transactional
	public void registrarOMOdificarUsuario(Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(usuario);
		
	}

	@Override
	@Transactional
	public Usuario getUsuario(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		Usuario usuario = session.get(Usuario.class, id);
		
		return usuario;
	}

	@Override
	@Transactional
	public void eliminarUsuario(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		
		Query consulta = session.createQuery("delete from Usuario where id=:idUsuario");
		
		consulta.setParameter("idUsuario", id);
		
		consulta.executeUpdate();
		
	}

	@Override
	public Boolean buscarUsuario(String usuario, String password) {
		
		final Session session = sessionFactory.getCurrentSession();
		Query<Usuario> miQuery=session.createQuery("from Usuario", Usuario.class);
		List<Usuario> usuarios=miQuery.getResultList();
		
		for (Usuario usuario2 : usuarios) {
			if(usuario2.getUser()!=null && usuario2.getPassword()!=null && usuario2.getUser().equals(usuario) && usuario2.getPassword().equals(password)) {
				return true;
			}
		}
		
		return false;
	}


	@Override
	public Boolean validarPassRePass(String pass, String repass) {
		if(pass.equals(repass)) {
			
			return true;
			
		}
		
		return false;
	}

	@Override
	public Usuario devolverUsuario(String usuario, String password) {
		final Session session = sessionFactory.getCurrentSession();
		Query<Usuario> miQuery=session.createQuery("from Usuario", Usuario.class);
		List<Usuario> usuarios=miQuery.getResultList();
		
		for (Usuario usuario2 : usuarios) {
			if(usuario2.getUser()!=null && usuario2.getPassword()!=null && usuario2.getUser().equals(usuario) && usuario2.getPassword().equals(password)) {
				return usuario2;
			}
		}
		return null;
	}
	

}
