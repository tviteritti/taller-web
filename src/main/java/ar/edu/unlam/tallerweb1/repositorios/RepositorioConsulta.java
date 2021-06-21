package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Consulta;

public interface RepositorioConsulta {
	
	void cargarConsulta(Consulta consulta);
	List<Consulta> listarConsultaPorDuenio(Long idDuenio);
	List<Consulta> listarConsultas();

}
