package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.modelo.Zona;

@Repository
public class RepositorioLocalidadImpl implements RepositorioLocalidad{
	
	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioLocalidadImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Localidad> obtenerLocalidades(String zona) {
		
		String buscarPorZona = zona; 
		
		List<Localidad> localidades = null;
		
		
		localidades = (List<Localidad>) sessionFactory.getCurrentSession()
						 .createCriteria(Localidad.class)
						 .createAlias("zona", "zonaBuscada")
						 .add(Restrictions.eq("zonaBuscada.descripcion", buscarPorZona))
						 .list();			 
	
		
		
		
		return localidades;
	}

	
    
    

}
