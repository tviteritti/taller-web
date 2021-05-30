package ar.edu.unlam.tallerweb1.repositorios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Horarios;

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
		Horarios h = new Horarios();
		final Session session = sessionFactory.getCurrentSession();
		if(duracion_sesion != "" && hora_fin!= "" && hora_inicio!= "") {
		Integer d_sesion = Integer.parseInt(duracion_sesion);
		SimpleDateFormat formato = new SimpleDateFormat("hh:mm");
		Date h_final;
		Date h_inicio;
		java.util.Date nfecha = formato.parse(hora_fin);
		java.util.Date nfecha2 = formato.parse(hora_inicio);
		h_final = new java.util.Date(nfecha.getTime());
		h_inicio = new java.util.Date(nfecha2.getTime());
		
		
		
		h.setDuracion_sesion(d_sesion);
		h.setHora_fin(h_final);
		h.setHora_inicio(h_inicio);
		
		}
		session.save(h);
		return h;
		
	}

}
