package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Localidad;

@Repository
public class RepositorioDireccionImpl implements RepositorioDireccion{

	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioDireccionImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	


}
