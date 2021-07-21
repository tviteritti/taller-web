package ar.edu.unlam.tallerweb1.repositorios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("repositorioMascotas")
public class RepositorioMascotasImpl implements RepositorioMascotas{
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioMascotasImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	
	@Override
	public List<Mascota> listarMascotas() {
		
	
		List<Mascota> mascotas = (List<Mascota>) sessionFactory.getCurrentSession()
				 .createCriteria(Mascota.class)
				 .list();
		return mascotas;
	
	}
	
	

	@Override
	public List<TipoAnimal> listarTipoAnimal() {
		
		final Session session = sessionFactory.getCurrentSession();
		Query<TipoAnimal> miQuery=session.createQuery("from TipoAnimal", TipoAnimal.class);
		List<TipoAnimal> tipoAnimal=miQuery.getResultList();
		return tipoAnimal;
	}

	@Override
	public void cargarMascota(Long id_tipo, Long id_duenio, String fecha_nac, String nombre) throws ParseException {
		final Session session = sessionFactory.getCurrentSession();
		
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(fecha_nac); 
		
		Usuario duenio = (Usuario) session.createCriteria(Usuario.class)
						.add(Restrictions.eq("id",id_duenio)).uniqueResult();
		
		TipoAnimal tipo = (TipoAnimal) session.createCriteria(TipoAnimal.class)
				.add(Restrictions.eq("id",id_tipo)).uniqueResult();
		
		Mascota mascota = new Mascota();
		mascota.setDuenio(duenio);
		mascota.setTipo(tipo);
		mascota.setNombre(nombre);
		mascota.setFecha_nacimiento(date1);
		
		session.save(mascota);
		
	}

	@Override
	public List<Mascota> listarMascotasPorDuenio(Long id_duenio) {
		
		return (List<Mascota>) sessionFactory.getCurrentSession()
				 .createCriteria(Mascota.class)
				 .createAlias("duenio", "duenioBuscado")
				 .add(Restrictions.eq("duenioBuscado.id", id_duenio))
				 .list();
	}

	@Override
	public TipoAnimal obtenerTipoAnimal(Long id) {
		TipoAnimal tipo = (TipoAnimal) sessionFactory.getCurrentSession()
				 .createCriteria(TipoAnimal.class)
				 .add(Restrictions.eq("id", id))
				 .uniqueResult();
		return tipo;

	}

	@Override
	public void modificarPerfilMascota(Long idMascota, String nombre, Date fechaNacimineto, String tipoAnimal) {
		
		Mascota mascota = (Mascota) sessionFactory.getCurrentSession()
				 .createCriteria(Mascota.class)
				 .add(Restrictions.eq("id", idMascota))
				 .uniqueResult();
		
		TipoAnimal tipo = new TipoAnimal();
		tipo.setDescripcion(tipoAnimal);
		
		sessionFactory.getCurrentSession().save(tipo);
		
		mascota.setNombre(nombre);
		mascota.setFecha_nacimiento(fechaNacimineto);
		mascota.setTipo(tipo);
		
	}


	@Override
	public Mascota buscarMascotaPorDuenio(Long idDuenio) {
		
		Mascota mascota = (Mascota) sessionFactory.getCurrentSession()
				 .createCriteria(Mascota.class)
				 .createAlias("duenio", "dBuscado")
				 .add(Restrictions.eq("dBuscado.id", idDuenio))
				 .uniqueResult();
		
		return mascota;
	}


	@Override
	public TipoAnimal obtenerTipoAnimal(String tipo) {
		
		TipoAnimal tipoAnimal = (TipoAnimal) sessionFactory.getCurrentSession()
				 .createCriteria(TipoAnimal.class)
				 .add(Restrictions.eq("descripcion", tipo))
				 .uniqueResult();
		
		return tipoAnimal;
		
	}


	@Override
	public void modificarPerfilMascota(Long idMascota, String nombre, Date fechaNacimineto, TipoAnimal tipoAnimal) {
		
		Mascota mascota = (Mascota) sessionFactory.getCurrentSession()
				 .createCriteria(Mascota.class)
				 .add(Restrictions.eq("id", idMascota))
				 .uniqueResult();
	
		mascota.setNombre(nombre);
		mascota.setFecha_nacimiento(fechaNacimineto);
		mascota.setTipo(tipoAnimal);
		
	}


	@Override
	public List<Mascota> listarMascotasPorDuenio(Usuario usuario) {
		
		return (List<Mascota>) sessionFactory.getCurrentSession()
				 .createCriteria(Mascota.class)
				 .add(Restrictions.eq("duenio", usuario))
				 .list();
	}

	
}
