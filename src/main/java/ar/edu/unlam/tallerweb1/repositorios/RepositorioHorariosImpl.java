package ar.edu.unlam.tallerweb1.repositorios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Horarios;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("repositorioHorarios")
public class RepositorioHorariosImpl implements RepositorioHorarios{

	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioHorariosImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void registrarOMOdificarHorarios(Horarios horarios) {
		final Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(horarios);
		
	}

	@Override
	public Horarios registrarOMOdificarHorarios(String duracion_sesion, String hora_fin, String hora_inicio)  throws ParseException{
		
		if(duracion_sesion == "" && hora_fin== "" && hora_inicio== "") { 
			return null;
		}
		
		final Session session = sessionFactory.getCurrentSession();
		Query<Horarios> miQuery=session.createQuery("from Horarios", Horarios.class);
		List<Horarios> bd=miQuery.getResultList();

		for (Horarios bd2 : bd) {
			
				Integer d_sesion = Integer.parseInt(duracion_sesion);			/*DE STRING A INTEGER*/
				
				SimpleDateFormat formato = new SimpleDateFormat("hh:mm");
				Date h_final;
				Date h_inicio;
				java.util.Date nfecha = formato.parse(hora_fin);				/*DE STRING A DATE*/
				java.util.Date nfecha2 = formato.parse(hora_inicio);			/*DE STRING A DATE*/
				h_final = new java.util.Date(nfecha.getTime());
				h_inicio = new java.util.Date(nfecha2.getTime());
			
				if(bd2.getDuracion_sesion().equals(d_sesion) && bd2.getHora_fin().equals(h_final) && bd2.getHora_inicio().equals(h_inicio)) {
					return bd2;
			}
			}
		
		
		
		
		Horarios h = new Horarios();

			/*ACEPTA HORARIOS NULOS*/
			
			Integer d_sesion = Integer.parseInt(duracion_sesion);			/*DE STRING A INTEGER*/
			
			SimpleDateFormat formato = new SimpleDateFormat("hh:mm");
			Date h_final;
			Date h_inicio;
			java.util.Date nfecha = formato.parse(hora_fin);				/*DE STRING A DATE*/
			java.util.Date nfecha2 = formato.parse(hora_inicio);			/*DE STRING A DATE*/
			h_final = new java.util.Date(nfecha.getTime());
			h_inicio = new java.util.Date(nfecha2.getTime());
			
			
			
			h.setDuracion_sesion(d_sesion);
			h.setHora_fin(h_final);
			h.setHora_inicio(h_inicio);
		
		session.save(h);
		return h;
		
	}

}
