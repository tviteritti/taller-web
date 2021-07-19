package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Consulta;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;


public interface RepositorioConsulta {
	
	void cargarConsulta(Consulta consulta);
	void eliminarConsultasNulas(Consulta consulta);
	void guardarRespuesta(Long idConsulta , Consulta respuesta, String userRespuesta);
	
	Consulta buscarConsulta(Long idConsulta);
	List<Consulta> listarConsultaPorUsuario(Long idUsuario);
	List<Consulta> listarConsultas();
	
}
