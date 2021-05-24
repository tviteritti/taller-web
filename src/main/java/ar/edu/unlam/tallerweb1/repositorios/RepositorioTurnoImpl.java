package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.modelo.Veterinario;
import ar.edu.unlam.tallerweb1.modelo.Zona;

@Repository
public class RepositorioTurnoImpl implements RepositorioTurno{
	
	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioTurnoImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Veterinario> obtenerVeterinariosPorZona(String zona) {
	String buscarPorZona = zona; 
		
		List<Veterinario> veterinarios = null;
		
		switch(buscarPorZona) {
		
		case "norte":
			veterinarios = (List<Veterinario>) sessionFactory.getCurrentSession()
						 .createCriteria(Veterinario.class)
						 .createAlias("zona", "zonaBuscada")
						 .add(Restrictions.eq("zonaBuscada.descripcion", buscarPorZona))
						 .list();			 
			break;
		case "sur":
			veterinarios = (List<Veterinario>) sessionFactory.getCurrentSession()
			 .createCriteria(Veterinario.class)
			 .createAlias("zona", "zonaBuscada")
			 .add(Restrictions.eq("zonaBuscada.descripcion", buscarPorZona))
			 .list();
			
			break;
			
		case "oeste":
			veterinarios = (List<Veterinario>) sessionFactory.getCurrentSession()
			 .createCriteria(Veterinario.class)
			 .createAlias("zona", "zonaBuscada")
			 .add(Restrictions.eq("zonaBuscada.descripcion", buscarPorZona))
			 .list();
			
			break;	
		
		
		}
		return veterinarios;
		
	}

	
    
    

}
