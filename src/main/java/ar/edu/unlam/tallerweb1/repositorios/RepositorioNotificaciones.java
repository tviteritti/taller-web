package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;

public interface RepositorioNotificaciones {
	
	void cargarNotificacion(Notificacion notificacion);
	List<Notificacion> listarNotificacionesPorUsuario(Long idUsuario);
	Notificacion buscarNotificacion(Long idNotificacion);

}
