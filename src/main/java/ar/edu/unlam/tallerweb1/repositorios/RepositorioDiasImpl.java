package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Dias;
import ar.edu.unlam.tallerweb1.modelo.Horarios;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("repositorioDias")
public class RepositorioDiasImpl implements RepositorioDias{

	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioDiasImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void registrarOModificarDiasLunes(Horarios horarios, Long id_dia) {
		final Session session = sessionFactory.getCurrentSession();
		Dias dia = null;
		dia = (Dias)sessionFactory.getCurrentSession()
				 .createCriteria(Dias.class)
				 .add(Restrictions.eq( "id", id_dia))
				 .uniqueResult();
		
		dia.setLunes(horarios);

		session.saveOrUpdate(dia);
		
	}

	@Override
	public void registrarOModificarDiasMartes(Horarios horarios, Long id_dia) {
		final Session session = sessionFactory.getCurrentSession();
		Dias dia = null;
		dia = (Dias)sessionFactory.getCurrentSession()
				 .createCriteria(Dias.class)
				 .add(Restrictions.eq( "id", id_dia))
				 .uniqueResult();
		
		dia.setMartes(horarios);

		session.saveOrUpdate(dia);
		
	}

	@Override
	public void registrarOModificarDiasMiercoles(Horarios horarios, Long id_dia) {
		final Session session = sessionFactory.getCurrentSession();
		Dias dia = null;
		dia = (Dias)sessionFactory.getCurrentSession()
				 .createCriteria(Dias.class)
				 .add(Restrictions.eq( "id", id_dia))
				 .uniqueResult();
		
		dia.setMiercoles(horarios);

		session.saveOrUpdate(dia);
		
	}

	@Override
	public void registrarOModificarDiasJueves(Horarios horarios, Long id_dia) {
		final Session session = sessionFactory.getCurrentSession();
		Dias dia = null;
		dia = (Dias)sessionFactory.getCurrentSession()
				 .createCriteria(Dias.class)
				 .add(Restrictions.eq( "id", id_dia))
				 .uniqueResult();
		
		dia.setJueves(horarios);

		session.saveOrUpdate(dia);
		
	}

	@Override
	public void registrarOModificarDiasViernes(Horarios horarios, Long id_dia) {
		final Session session = sessionFactory.getCurrentSession();
		Dias dia = null;
		dia = (Dias)sessionFactory.getCurrentSession()
				 .createCriteria(Dias.class)
				 .add(Restrictions.eq( "id", id_dia))
				 .uniqueResult();
		
		dia.setViernes(horarios);

		session.saveOrUpdate(dia);
		
	}

	@Override
	public void registrarOModificarDiasSabado(Horarios horarios, Long id_dia) {
		final Session session = sessionFactory.getCurrentSession();
		Dias dia = null;
		dia = (Dias)sessionFactory.getCurrentSession()
				 .createCriteria(Dias.class)
				 .add(Restrictions.eq( "id", id_dia))
				 .uniqueResult();
		
		dia.setSabado(horarios);

		session.saveOrUpdate(dia);
		
	}

	@Override
	public void registrarOModificarDiasDomingo(Horarios horarios, Long id_dia) {
		final Session session = sessionFactory.getCurrentSession();
		Dias dia = null;
		dia = (Dias)sessionFactory.getCurrentSession()
				 .createCriteria(Dias.class)
				 .add(Restrictions.eq( "id", id_dia))
				 .uniqueResult();
		
		dia.setDomingo(horarios);

		session.saveOrUpdate(dia);
	}

	@Override
	public void registrarOModificarDiasVeterinario(Usuario veterinario, Long id_dia) {
		final Session session = sessionFactory.getCurrentSession();
		Dias dia = null;
		dia = (Dias)sessionFactory.getCurrentSession()
				 .createCriteria(Dias.class)
				 .add(Restrictions.eq( "id", id_dia))
				 .uniqueResult();
		
		dia.setVeterinario(veterinario);

		session.saveOrUpdate(dia);
	}

	@Override
	public void registrarOModificarDias(Dias dia) {
		final Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(dia);
		
	}

}
