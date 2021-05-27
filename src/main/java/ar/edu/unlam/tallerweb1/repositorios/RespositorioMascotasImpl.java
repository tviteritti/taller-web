package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.modelo.Turno;

@Repository
public class RespositorioMascotasImpl implements RepositorioMascotas{
	
	private SessionFactory sessionFactory;

	@Override
	public List<Mascota> listarMascotas() {
		
		List<Mascota> mascotas = null;
		mascotas = (List<Mascota>) sessionFactory.getCurrentSession()
				 .createCriteria(Mascota.class)
				 .list();
		
		return mascotas;
	
	}

	@Override
	public List<TipoAnimal> listarTipoAnimal() {
		
		List<TipoAnimal> tipos = null;
		tipos = (List<TipoAnimal>) sessionFactory.getCurrentSession()
				 .createCriteria(TipoAnimal.class)
				 .list();
		
	
		return tipos;
	}
}
