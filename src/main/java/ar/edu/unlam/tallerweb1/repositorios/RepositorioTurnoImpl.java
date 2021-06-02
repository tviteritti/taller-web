package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository
public class RepositorioTurnoImpl implements RepositorioTurno{
	
	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioTurnoImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Usuario> obtenerVeterinariosPorZona(String zona) {
	String buscarPorZona = zona; 
		
		List<Usuario> veterinarios = null;
		
	
		veterinarios = (List<Usuario>) sessionFactory.getCurrentSession()
						 .createCriteria(Usuario.class)
						 .createAlias("zona", "zonaBuscada")
						 .add(Restrictions.eq("zonaBuscada.descripcion", buscarPorZona))
						 .add(Restrictions.eq("rol", "veterinario"))
						 .list();			 
		
		return veterinarios;	
	}

	@Override
	public void cancelarTurno(Long idTurno) {
		
		Turno turnoACancelar = null;
		
		
		turnoACancelar = (Turno)sessionFactory.getCurrentSession()
				 .createCriteria(Turno.class)
				 .add(Restrictions.eq( "id", idTurno))
				 .uniqueResult();
		
		turnoACancelar.setMascota(null);
		turnoACancelar.setEstado("disponible");
		
	}

	@Override
	public List<Turno> obtenerTurnosPorVeterinario(Usuario veterinario) {
		
		List<Turno> turnos = null;
		
		turnos = (List<Turno>) sessionFactory.getCurrentSession()
				 .createCriteria(Turno.class)
				 .createAlias("veterinario", "vBuscado")
				 .add(Restrictions.eq("vBuscado.id", veterinario.getId()))
				 .add(Restrictions.eq("vBuscado.rol", "veterinario"))
				 .list();
		
		return turnos;
	}

	@Override
	public List<Turno> obtenerTurnosPorEspecialidad(String servicio) {
		
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

	@Override
	public void asignarTurno(Long idTurno, Mascota mascota) {
		
		    	 Turno turnoBuscado = (Turno) sessionFactory.getCurrentSession()
				 .createCriteria(Turno.class)
				 .add(Restrictions.eq("id", idTurno))
				 .uniqueResult();
		    	 
		    	 turnoBuscado.setMascota(mascota);
		    	 turnoBuscado.setEstado("no disponible");
		    	
	}
 
	

}
