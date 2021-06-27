package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioNotificaciones;

@Service
@Transactional
public class ServicioNotificacionesImpl implements ServicioNotificaciones {
	
	private RepositorioNotificaciones repositorioNotificaciones;

	@Autowired
	public  ServicioNotificacionesImpl(RepositorioNotificaciones repositorioNotificaciones){
		this.repositorioNotificaciones = repositorioNotificaciones;
	}
	
	
	@Override
	public void cargarNotificacion(Notificacion notificacion) {
		
		repositorioNotificaciones.cargarNotificacion(notificacion);
		
	}

	@Override
	public List<Notificacion> listarNotificacionesPorDuenio(Long idDuenio) {
		
		return repositorioNotificaciones.listarNotificacionesPorDuenio(idDuenio);
	}


	@Override
	public Integer cantidadNotificaciones(Long idUsuario) {
		
		List<Notificacion> notificaciones = repositorioNotificaciones.listarNotificacionesPorDuenio(idUsuario);
		
		Integer totalNotificacionesUsuario = 0 ;
		
		for(Notificacion notificacion: notificaciones) {
			
			if(notificacion.getDuenio().getId().equals(idUsuario) && notificacion.getId()!=null) {
				
				totalNotificacionesUsuario++;
				
			}
			
		}
		
		return totalNotificacionesUsuario;
	}


	@Override
	public Notificacion buscarNotificacion(Long idNotificacion) {
		
		return repositorioNotificaciones.buscarNotificacion(idNotificacion);
	}
	
	

}
