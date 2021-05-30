package ar.edu.unlam.tallerweb1.repositorios;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Dias;
import ar.edu.unlam.tallerweb1.modelo.Horarios;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Zona;

@Repository
public class RepositorioTurnoImpl implements RepositorioTurno{
	
	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioTurnoImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

//	@Override
//	public List<Veterinario> obtenerVeterinariosPorZona(String zona) {
//	String buscarPorZona = zona; 
//		
//		List<Veterinario> veterinarios = null;
//		
//	
//		veterinarios = (List<Veterinario>) sessionFactory.getCurrentSession()
//						 .createCriteria(Veterinario.class)
//						 .createAlias("zona", "zonaBuscada")
//						 .add(Restrictions.eq("zonaBuscada.descripcion", buscarPorZona))
//						 .list();			 
//		
//		return veterinarios;	
//	}

	@Override
	public void cancelarTurno(Long idTurno) {
		
		Turno turnoACancelar = null;
		
		
		turnoACancelar = (Turno)sessionFactory.getCurrentSession()
				 .createCriteria(Turno.class)
				 .add(Restrictions.eq( "id", idTurno))
				 .uniqueResult();
		
		sessionFactory.getCurrentSession().delete(turnoACancelar);
		
	}

//	@Override
//	public List<Turno> obtenerTurnos(Veterinario veterinario) {
//		
//		List<Turno> turnosSolicitados = null;
//		
//		turnosSolicitados = (List<Turno>) sessionFactory.getCurrentSession()
//				 .createCriteria(Turno.class)
//				 .createAlias("veterinario", "vBuscado")
//				 .add(Restrictions.eq("vBuscado.apellido", veterinario.getApellido()))
//				 .list();
//		
//		return turnosSolicitados;
//	}

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
	
	@Override
	public void generarTurnoPorIdDia(Long id_dia) {
		final Session session = sessionFactory.getCurrentSession();
		
		Dias dia = new Dias();
		
		dia = (Dias)sessionFactory.getCurrentSession()
				 .createCriteria(Dias.class)
				 .add(Restrictions.eq( "id", id_dia))
				 .uniqueResult();
		
		
		Usuario veterinario = dia.getVeterinario();
		Date hora_inicio = dia.getLunes().getHora_inicio();
		Boolean estado = false;
		
		Turno turno = new Turno();
		turno.setVeterinario(veterinario);
		turno.setHorario(hora_inicio);
		turno.setEstado(estado);
		session.saveOrUpdate(turno);
		
	}

	@Override
	public Usuario devolverVeterinarioDeunDia(Long id_dia) {
		Dias dia = new Dias();
		
		dia = (Dias)sessionFactory.getCurrentSession()
				 .createCriteria(Dias.class)
				 .add(Restrictions.eq( "id", id_dia))
				 .uniqueResult();
		
		
		return dia.getVeterinario();
	}
	
	@Override
	public Horarios devolverDialunes(Long id_dia) {
		Dias dia = new Dias();
		
		dia = (Dias)sessionFactory.getCurrentSession()
				 .createCriteria(Dias.class)
				 .add(Restrictions.eq( "id", id_dia))
				 .uniqueResult();
		
		
		return dia.getLunes();
	}

	@Override
	public void generarTurno(Turno turno) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(turno);
		
	}
	
 

}
