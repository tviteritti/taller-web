package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlam.tallerweb1.modelo.Consulta;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDias;

public interface ServicioConsulta {
	
	void cargarConsulta(Consulta consulta);
	void eliminarConsultasNulas(Consulta consulta);
	void guardarRespuesta(Long idConsulta , Consulta respuesta, String userRespuesta);
	Consulta buscarConsulta(Long idConsulta);
	List<Consulta> listarConsultaPorUsuario(Long idUsuario);
	List<Consulta> listarConsultas();
}
