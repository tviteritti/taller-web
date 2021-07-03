package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;

public interface ServicioNotificaciones {
	
	void cargarNotificacion(Notificacion notificacion);
	List<Notificacion> listarNotificacionesPorUsuario(Long idUsuario);
	Integer cantidadNotificaciones(Long idUsuario);
	List<Notificacion> buscarNotificacion(Long idNotificacion);
	Notificacion obtenerNotificacion(Long idNotificacion);

}
