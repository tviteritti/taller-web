package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlam.tallerweb1.modelo.Consulta;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDias;

public interface ServicioConsulta {
	
	void cargarConsulta(Consulta consulta);
	void eliminarConsultasNulas(Consulta consulta);
	void cargarNotificacion(Notificacion notificacion);
	void agregarComentario(Long idConsulta, String comentario, String userRespuesta);
	Consulta buscarConsulta(Long idConsulta);
	List<Consulta> listarConsultaPorDuenio(Long idDuenio);
	List<Consulta> listarConsultas();
	List<Notificacion> listarNotificacionesPorDuenio(Long idDuenio);

}
