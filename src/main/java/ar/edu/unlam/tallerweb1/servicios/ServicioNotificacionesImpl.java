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
	public List<Notificacion> listarNotificacionesPorUsuario(Long idUsuario) {
		
		return repositorioNotificaciones.listarNotificacionesPorUsuario(idUsuario);
	}


	@Override
	public Integer cantidadNotificaciones(Long idUsuario) {
		
		List<Notificacion> notificaciones = repositorioNotificaciones.listarNotificacionesPorUsuario(idUsuario);
		
		Integer totalNotificacionesUsuario = 0 ;
		
		for(Notificacion notificacion: notificaciones) {
			
			if(notificacion.getUsuario().getId().equals(idUsuario) && notificacion.getId()!=null) {
				
				totalNotificacionesUsuario++;
				
			}
			
		}
		
		return totalNotificacionesUsuario;
	}


	@Override
	public List<Notificacion> buscarNotificacion(Long idNotificacion) {
		
		return repositorioNotificaciones.buscarNotificacion(idNotificacion);
	}


	@Override
	public Notificacion obtenerNotificacion(Long idNotificacion) {
		
		return repositorioNotificaciones.obtenerNotificacion(idNotificacion);
	}
	
	
	
	

}
