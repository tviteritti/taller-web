package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository
public class RepositorioHistoriaClinicaImpl implements RepositorioHistoriaClinica{
	
	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioHistoriaClinicaImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}


	@Override
	public List<HistoriaClinica> listarHistoriaClinicaPorMascotaYVeterinario(Mascota mascota, Usuario veterinario) {
		
		List<HistoriaClinica> mascotasHC = (List<HistoriaClinica>) sessionFactory.getCurrentSession()
				 .createCriteria(HistoriaClinica.class)
				 .createAlias("mascota", "mBuscada")
				 .createAlias("veterinario", "vBuscado")
				 .add(Restrictions.eq("mBuscada.id", mascota.getId()))
				 .add(Restrictions.eq("vBuscado.id",veterinario.getId()))
				 .add(Restrictions.eq("vBuscado.rol","veterinario"))
				 .add((Criterion) Projections.groupProperty("id"))
				 .list();
		return mascotasHC;
	}


	@Override
	public List<HistoriaClinica> obtenerHistoriaClinica() {
		List<HistoriaClinica> hc = (List<HistoriaClinica>) sessionFactory.getCurrentSession()
				 .createCriteria(HistoriaClinica.class)
				 .list();
		return hc;
	}


	@Override
	public void cargarHC(HistoriaClinica hc) {
		
		final Session session = this.sessionFactory.getCurrentSession();
		 session.save(hc);
		
	}
	
	@Override
	public void eliminarHC(HistoriaClinica hc) {
		
		sessionFactory.getCurrentSession().delete(hc);
		
	}
	
	
	
	
	
	 
	

}
