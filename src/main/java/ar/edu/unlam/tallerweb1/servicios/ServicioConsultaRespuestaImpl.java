package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.ConsultaRespuesta;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioConsulta;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioConsultaRespuesta;

@Service
@Transactional
public class ServicioConsultaRespuestaImpl implements ServicioConsultaRespuesta{
	
	private RepositorioConsultaRespuesta repositorioConsultaRespuesta;

	@Autowired
	public ServicioConsultaRespuestaImpl(RepositorioConsultaRespuesta repositorioConsultaRespuesta){
		this.repositorioConsultaRespuesta = repositorioConsultaRespuesta;
	}

	@Override
	public List<ConsultaRespuesta> listarConsultaRespuesta() {
		
		return repositorioConsultaRespuesta.listarConsultaRespuesta();
	}
	

}
