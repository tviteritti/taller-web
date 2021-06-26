package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Consulta;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;


public interface RepositorioConsulta {
	
	void cargarConsulta(Consulta consulta);
	void eliminarConsultasNulas(Consulta consulta);
	void agregarComentario(Long idConsulta , String comentario, String userRespuesta);
	void cargarNotificacion(Notificacion notificacion);
	Consulta buscarConsulta(Long idConsulta);
	List<Consulta> listarConsultaPorDuenio(Long idDuenio);
	List<Consulta> listarConsultas();
	List<Notificacion> listarNotificacionesPorDuenio(Long idDuenio);

}
