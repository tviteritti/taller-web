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
}
