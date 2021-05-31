package ar.edu.unlam.tallerweb1.repositorios;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Dias;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
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

	@Override
	public List<Usuario> obtenerVeterinariosPorZona(String zona) {
	String buscarPorZona = zona; 
		
		List<Usuario> veterinarios = null;
		
	
		veterinarios = (List<Usuario>) sessionFactory.getCurrentSession()
						 .createCriteria(Usuario.class)
						 .createAlias("zona", "zonaBuscada")
						 .add(Restrictions.eq("zonaBuscada.descripcion", buscarPorZona))
						 .list();			 
		
		return veterinarios;	
	}
	
	@Override
	public List<Turno> obtenerTurnos(Usuario veterinario) {
		
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
	public List<Direccion> obtenerLocalidades(String zona) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	
	
	
	
	@Override
	public List<Turno> listarTurnosSinTomar() {
		Boolean estado= false;
		List<Turno> turnos = null;
		turnos = (List<Turno>) sessionFactory.getCurrentSession()
				 .createCriteria(Turno.class)
				 .add(Restrictions.eq( "estado", estado))
				 .list();
		
		return turnos;
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
	public void generarTurno(Turno turno) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(turno);
		
	}

	@Override
	public void tomarTurno(Long id, Usuario duenio) {
		final Session session = sessionFactory.getCurrentSession();
		
		Turno turno = new Turno();
		turno = (Turno)sessionFactory.getCurrentSession()
				 .createCriteria(Turno.class)
				 .add(Restrictions.eq( "id", id))
				 .uniqueResult();
		
		turno.setDuenio(duenio);
		turno.setEstado(true);
		session.saveOrUpdate(turno);
		
		
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
	public Horarios devolverDiaMartes(Long id_dia) {
		Dias dia = new Dias();
		
		dia = (Dias)sessionFactory.getCurrentSession()
				 .createCriteria(Dias.class)
				 .add(Restrictions.eq( "id", id_dia))
				 .uniqueResult();
		
		
		return dia.getMartes();
	}
	
	@Override
	public Horarios devolverDiaMiercoles(Long id_dia) {
		Dias dia = new Dias();
		
		dia = (Dias)sessionFactory.getCurrentSession()
				 .createCriteria(Dias.class)
				 .add(Restrictions.eq( "id", id_dia))
				 .uniqueResult();
		
		
		return dia.getMiercoles();
	}
	
	@Override
	public Horarios devolverDiaJueves(Long id_dia) {
		Dias dia = new Dias();
		
		dia = (Dias)sessionFactory.getCurrentSession()
				 .createCriteria(Dias.class)
				 .add(Restrictions.eq( "id", id_dia))
				 .uniqueResult();
		
		
		return dia.getJueves();
	}
	
	@Override
	public Horarios devolverDiaViernes(Long id_dia) {
		Dias dia = new Dias();
		
		dia = (Dias)sessionFactory.getCurrentSession()
				 .createCriteria(Dias.class)
				 .add(Restrictions.eq( "id", id_dia))
				 .uniqueResult();
		
		
		return dia.getViernes();
	}
	
	@Override
	public Horarios devolverDiaSabado(Long id_dia) {
		Dias dia = new Dias();
		
		dia = (Dias)sessionFactory.getCurrentSession()
				 .createCriteria(Dias.class)
				 .add(Restrictions.eq( "id", id_dia))
				 .uniqueResult();
		
		
		return dia.getSabado();
	}
	
	@Override
	public Horarios devolverDiaDomingo(Long id_dia) {
		Dias dia = new Dias();
		
		dia = (Dias)sessionFactory.getCurrentSession()
				 .createCriteria(Dias.class)
				 .add(Restrictions.eq( "id", id_dia))
				 .uniqueResult();
		
		
		return dia.getDomingo();
	}
	


	
 

}
