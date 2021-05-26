package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.modelo.Turno;
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

	@Override
	public void cancelarTurno(Long idTurno) {
		
		Turno turnoACancelar = null;
		
		
		turnoACancelar = (Turno)sessionFactory.getCurrentSession()
				 .createCriteria(Turno.class)
				 .add(Restrictions.eq( "id", idTurno))
				 .uniqueResult();
		
		sessionFactory.getCurrentSession().delete(turnoACancelar);
		
	}

	@Override
	public List<Turno> obtenerTurnos(Veterinario veterinario) {
		
		List<Turno> turnosSolicitados = null;
		
		turnosSolicitados = (List<Turno>) sessionFactory.getCurrentSession()
				 .createCriteria(Turno.class)
				 .createAlias("veterinario", "vBuscado")
				 .add(Restrictions.eq("vBuscado.apellido", veterinario.getApellido()))
				 .list();
		
		return turnosSolicitados;
	}

	@Override
	public List<Turno> obtenerTurnos(String servicio) {
		
		List<Turno> turnosSolicitados = null;
		
		turnosSolicitados = (List<Turno>) sessionFactory.getCurrentSession()
				 .createCriteria(Turno.class)
				 .add(Restrictions.eq("servicio", servicio))
				 .list();
		
		return turnosSolicitados;
	}

	@Override
	public List<Turno> listarTurnos() {
		
		List<Turno> turnos = null;
		turnos = (List<Turno>) sessionFactory.getCurrentSession()
				 .createCriteria(Turno.class)
				 .list();
		
		return turnos;
	}
 

}
