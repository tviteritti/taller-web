package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Calificacion;
import ar.edu.unlam.tallerweb1.modelo.Dias;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Voto;
import ar.edu.unlam.tallerweb1.modelo.Zona;

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
	public List<Usuario> listarVeterinarios() {
		
		List<Usuario> veterinarios = (List<Usuario>)sessionFactory.getCurrentSession()
				 .createCriteria(Usuario.class)
				 .add(Restrictions.eq( "rol", "veterinario"))
				 .list();
		
		return  veterinarios;
	
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
		/*final Session session = sessionFactory.getCurrentSession();
		Usuario usuario = session.get(Usuario.class, id);*/
		Usuario usuario = (Usuario)sessionFactory.getCurrentSession()
				 .createCriteria(Usuario.class)
				 .add(Restrictions.eq( "id", id))
				 .uniqueResult();
		
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
	public List<Especialidad> getEspecialidades() {
		final Session session = sessionFactory.getCurrentSession();
		Query<Especialidad> miQuery=session.createQuery("from Especialidad", Especialidad.class);
		List<Especialidad> especialidad=miQuery.getResultList();
		return especialidad;
		
	}
	
	@Override
	public List<Zona> getZonas() {
		final Session session = sessionFactory.getCurrentSession();
		Query<Zona> miQuery=session.createQuery("from Zona", Zona.class);
		List<Zona> zona=miQuery.getResultList();
		return zona;
		
	}

	@Override
	public void ingresarEspecialidad(Long id, Long id_especialidad) {
		final Session session = sessionFactory.getCurrentSession();
		Usuario veterinario = new Usuario();
		
		veterinario = (Usuario)sessionFactory.getCurrentSession()
				 .createCriteria(Usuario.class)
				 .add(Restrictions.eq( "id", id))
				 .uniqueResult();
		
		Especialidad especialidad = new Especialidad();
		
		especialidad = (Especialidad)sessionFactory.getCurrentSession()
				 .createCriteria(Especialidad.class)
				 .add(Restrictions.eq( "id", id_especialidad))
				 .uniqueResult();
		
		veterinario.setEspecialdad(especialidad);
		session.saveOrUpdate(veterinario);
		
	}

	@Override
	public Usuario getDuenio(Long id) {
		Usuario duenio = (Usuario)sessionFactory.getCurrentSession()
				 .createCriteria(Usuario.class)
				 .add(Restrictions.eq("id", id))
				 .add(Restrictions.eq("rol", "duenio"))
				 .uniqueResult();
		
		return duenio;
		
	}

	@Override
	public Usuario getVeterinario(Long id) {
		Usuario veterinario = (Usuario)sessionFactory.getCurrentSession()
				 .createCriteria(Usuario.class)
				 .add(Restrictions.eq("id", id))
				 .add(Restrictions.eq("rol", "veterinario"))
				 .uniqueResult();
		
		return veterinario;
	}

	@Override
	public void ingresarDireccion(Long id, String calle, String piso, String departamento, String numero, Long id_zona) {
		final Session session = sessionFactory.getCurrentSession();
		Usuario veterinario = (Usuario)sessionFactory.getCurrentSession()
				 .createCriteria(Usuario.class)
				 .add(Restrictions.eq("id", id))
				 .uniqueResult();
		
		Zona zona = (Zona)sessionFactory.getCurrentSession()
				 .createCriteria(Zona.class)
				 .add(Restrictions.eq("id", id_zona))
				 .uniqueResult();
		
		Direccion dire = new Direccion();
		
		dire.setZona(zona);
		dire.setCalle(calle);
		dire.setDepartamento(departamento);
		dire.setNumero(numero);
		dire.setPiso(piso);
		
		session.saveOrUpdate(dire);
		
		veterinario.setDireccion(dire);
	
		session.saveOrUpdate(veterinario);
	}

	@Override
	public Especialidad getEspecialidad(Long id) {
		
		Especialidad especialidad = (Especialidad)sessionFactory.getCurrentSession()
				 .createCriteria(Especialidad.class)
				 .add(Restrictions.eq("id", id))
				 .uniqueResult();
		
		return especialidad;
	}

	@Override
	public Zona getZona(Long id) {
		
		Zona zona = (Zona)sessionFactory.getCurrentSession()
				 .createCriteria(Zona.class)
				 .add(Restrictions.eq("id", id))
				 .uniqueResult();
		
		
		return zona;
	}


	@Override
	public void modificarPerfil(Long idUsuario, String nombre, String apellido, Long idDireccion, String calle, String nro,
			Long idLocalidad, Integer codPostal, String localidad, String telefono, String email, String descripcion) {
		
		Usuario usuario = (Usuario)sessionFactory.getCurrentSession()
				 .createCriteria(Usuario.class)
				 .add(Restrictions.eq("id", idUsuario))
				 .uniqueResult();
		
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setTelefono(telefono);
		usuario.setEmail(email);
		usuario.setDescripcion(descripcion);
		
		Localidad modificarLocalidad = (Localidad)sessionFactory.getCurrentSession()
				 .createCriteria(Localidad.class)
				 .add(Restrictions.eq("id", idLocalidad))
				 .uniqueResult();
		
		modificarLocalidad.setCp(codPostal);
		modificarLocalidad.setDescripcion(localidad);
		
		Direccion modificarDireccion = (Direccion)sessionFactory.getCurrentSession()
				 .createCriteria(Direccion.class)
				 .add(Restrictions.eq("id", idDireccion))
				 .uniqueResult();
		
	
		modificarDireccion.setCalle(calle);
		modificarDireccion.setNumero(nro);
		
	}

	@Override
	public Calificacion devolverCalificarVeterinario(Long id_veterinario) {
		
		Calificacion cal = (Calificacion)sessionFactory.getCurrentSession()
				 .createCriteria(Calificacion.class)
				 .add(Restrictions.eq("veterinario.id", id_veterinario))
				 .uniqueResult();
		return cal;
		
	}

	@Override
	public void registrarCalificaion(Calificacion cal) {
		final Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(cal);
		
	}

	@Override
	public void voto(Long id_vetrinario, Long id_duenio) {
		final Session session = sessionFactory.getCurrentSession();
		

		Usuario vet = (Usuario)sessionFactory.getCurrentSession()
				 .createCriteria(Usuario.class)
				 .add(Restrictions.eq("id", id_vetrinario))
				 .uniqueResult();
		

		Usuario due = (Usuario)sessionFactory.getCurrentSession()
				 .createCriteria(Usuario.class)
				 .add(Restrictions.eq("id", id_duenio))
				 .uniqueResult();
		
		Voto v = new Voto();
		v.setDuenio(due);
		v.setVeterinario(vet);
		
		session.save(v);
		
	}

	@Override
	public List<Voto> getVotos(Long id_duenio) {
		List<Voto> votos = (List<Voto>)sessionFactory.getCurrentSession()
				 .createCriteria(Voto.class)
				 .add(Restrictions.eq( "duenio.id", id_duenio))
				 .list();
		
		return  votos;
	}

	@Override
	public Voto getVoto(Long id_veterinario) {
		Voto voto = (Voto)sessionFactory.getCurrentSession()
				 .createCriteria(Voto.class)
				 .add(Restrictions.eq( "veterinario.id", id_veterinario))
				 .uniqueResult();
		
		return  voto;
	}

	@Override
	public List<Voto> getVotos() {
		
		List<Voto> votos = (List<Voto>)sessionFactory.getCurrentSession()			
				 .createCriteria(Voto.class)
				 .list();
		
		return  votos;
	}

	@Override
	public List<Calificacion> getCalificaciones() {
		List<Calificacion> calificacion = (List<Calificacion>)sessionFactory.getCurrentSession()			
				 .createCriteria(Calificacion.class)
				 .list();
		
		return  calificacion;
	}

	@Override
	public void cargarZona(Zona zona) {
		sessionFactory.getCurrentSession().save(zona);
		
	}

	@Override
	public void cargarEspecialidad(Especialidad especialidad) {
		sessionFactory.getCurrentSession().save(especialidad);
		
	}

	

}
